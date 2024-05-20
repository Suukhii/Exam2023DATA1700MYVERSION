package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @Autowired // Establishes database connection
    private JdbcTemplate db; // JDBC Template class for database access


    // Method to save a ticket into the database
    public void saveMember(Member inMember){
        String sql = "INSERT INTO Member (membership, firstname, lastname, age, telf, email) VALUES(?,?,?,?,?,?)";
        db.update(sql, inMember.getMembership(), inMember.getFirstname(), inMember.getLastname(), inMember.getAge(), inMember.getTelf(), inMember.getEmail());
    }


    public List<Member> getAllMembersSorted() {
        String sql = "SELECT * FROM Member ORDER BY lastname";
        return db.query(sql, new BeanPropertyRowMapper<>(Member.class));
    }

    public void deleteUnderageMembers() {
        db.update("DELETE FROM Member WHERE age < 18");
    }



}
