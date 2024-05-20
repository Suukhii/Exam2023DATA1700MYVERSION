package com.example.demo;

public class Member {

    private Long id;
    private String membership;
    private String firstname;
    private String lastname;
    private int age;
    private String telf;
    private String email;

    public Member() {
    }

    public Member(Long id, String membership, String firstname, String lastname, int age, String telf, String email) {
        this.id = id;
        this.membership = membership;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.telf = telf;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
