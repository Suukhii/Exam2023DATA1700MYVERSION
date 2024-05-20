// Function to display members in a table
function showMembersTable(members) {
    let out = "<table class='table table-striped' style='text-align: center'><tr>" +
        "<th><strong>Membership</strong></th>" +
        "<th><strong>Firstname</strong></th>" +
        "<th><strong>Lastname</strong></th>" +
        "<th><strong>Age</strong></th>" +
        "<th><strong>Phone Number</strong></th>" +
        "<th><strong>Email</strong></th>" +
        "</tr>";

    for (const member of members) {
        out += "<tr>";
        out += "<td>" + member.membership + "</td>" +
            "<td>" + member.firstname + "</td>" +
            "<td>" + member.lastname + "</td>" +
            "<td>" + member.age + "</td>" +
            "<td>" + member.telf + "</td>" +
            "<td>" + member.email + "</td>";
        out += "</tr>";
    }
    out += "</table>";

    $("#memberList").html(out);
}

// Function to get all members and display them
function getAllMembers() {
    $.get("/getAll", function (data) {
        showMembersTable(data);
    });
}

// Function to validate phone number
function validateTelefonNr() {
    const telf = $("#telf").val();
    const regexp = /^\d{8}$/;
    const ok = regexp.test(telf);

    if (!ok) {
        $("#wrongTlf").html("You have typed an invalid phone number");
        return false;
    } else {
        $("#wrongTlf").html("");
        return true;
    }
}

// Function to validate email
function validateEmail() {
    const email = $("#email").val();
    const regexp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const ok = regexp.test(email);

    if (!ok) {
        $("#wrongEmail").html("You have typed an invalid email");
        return false;
    } else {
        $("#wrongEmail").html("");
        return true;
    }
}

// Function to handle form submission
function sendForm() {
    // Perform form validation
    if (validateEmail() && validateTelefonNr()) {
        const member = {
            membership: $("#membership").val(),
            firstname: $("#firstname").val(),
            lastname: $("#lastname").val(),
            age: $("#age").val(),
            telf: $("#telf").val(),
            email: $("#email").val()
        };

        // Send the form data to the server
        $.post("/saveMember", member)
            .done(function() {
                // Refresh the member list after a successful save
                getAllMembers();
            })
            .fail(function (jqXHR) {
                const json = $.parseJSON(jqXHR.responseText);
                $("#fail").html(json.message); // Correctly access the message property
            });
    }
}

// Initialize member display on page load
$(function() {
    getAllMembers();
});


/// Function to handle login
function login() {
    const username = $("#username").val();
    const password = $("#password").val();

    // Send the login credentials to the server
    $.post("/login", {username: username, password: password})
        .done(function(response) {
            // Display the login result
            alert(response);
            // If login successful, show the button to delete underage members
            if (response === "Login successful.") {
                $("#deleteUnderageButton").show();
            }
        })
        .fail(function(jqXHR) {
            // Display error message if login fails
            alert("Login failed: " + jqXHR.responseText);
        });
}


// Function to handle logout
function logout() {
    // Send request to logout
    $.get("/logout")
        .done(function(response) {
            // Display the result of logout
            alert(response);
            // Hide the delete underage members button and show the login form
            $("#deleteUnderageButton").hide();
            $("#loginForm").show();
        })
        .fail(function(jqXHR) {
            // Display error message if logout fails
            alert("Logout failed: " + jqXHR.responseText);
        });
}



// Function to handle click on delete underage members button
function deleteUnderageMembers() {
    // Send request to delete underage members
    $.get("/deleteUnderageMembers")
        .done(function(response) {
            // Display the result of the operation
            alert(response);
            // Refresh the member list after successful deletion
            getAllMembers();
        })
        .fail(function(jqXHR) {
            // Display error message if operation fails
            alert("Operation failed: " + jqXHR.responseText);
        });
}
