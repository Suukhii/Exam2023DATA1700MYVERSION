package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    MemberRepository rep;
    Logger logger = LoggerFactory.getLogger(MemberController.class);


    @Autowired
    private HttpSession session;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/saveMember")
    public void saveMember(Member inMember) {
        rep.saveMember(inMember);
    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            session.setAttribute("loggedIn", true);
            return "Login successful.";
        } else {
            return "Invalid credentials.";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("loggedIn");
        return "Logged out successfully.";
    }

    @GetMapping("/deleteUnderageMembers")
    public String deleteUnderageMembers() {
        // Check if user is logged in
        if (session.getAttribute("loggedIn") != null) {
            // Delete underage members
            rep.deleteUnderageMembers();
            session.removeAttribute("loggedIn"); // Logout after operation
            return "Underage members deleted successfully.";
        } else {
            return "You need to login first to perform this operation.";
        }
    }


    // Endpoint to retrieve all members sorted alphabetically by last name
    @GetMapping("/getAll")
    public List<Member> getAllMembers() {
        List<Member> members = rep.getAllMembersSorted();
        logger.info("Retrieved all members: {}", members);
        return members;



}}





