package com.pnu.demo.chatbot.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberRepository extends MongoRepository<Member, String> {
    Member findByUsername(String username);
}
