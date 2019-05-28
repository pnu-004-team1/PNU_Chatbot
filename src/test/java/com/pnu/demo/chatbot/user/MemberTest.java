package com.pnu.demo.chatbot.user;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {
    Member member1 = new Member();
    Member member2;

    String username;
    String password;
    String name;
    boolean active;

    @Test
    public void testGetter() {
        Member member2 = new Member("jul", "4321", "July");
        username = member2.getUsername();
        password = member2.getPassword();
        name = member2.getName();
        active = member2.isActive();

        String result = username + ", " + password + ", " + name + ", " + String.valueOf(active);
        String expectedResult = "jul, 4321, July, false";
        assertEquals(result, expectedResult);
    }

    @Test
    public void testSetter() {
        String ErrorMsg = "";
        try {
            member1.setUsername("tom");
            member1.setPassword("1234");
            member1.setName("Tomas");
            member1.setActive(true);
        } catch (Exception e) {
            ErrorMsg = "cannot set any field";
        }

        assertEquals(ErrorMsg, "");
    }

    @Test
    public void testToString() {
        Member member2 = new Member("jul", "4321", "July");

        String result = member2.toString();
        String expectedResult = "Member [username=jul, password=4321, name=July, active=false]";

        assertEquals(result, expectedResult);
    }
}