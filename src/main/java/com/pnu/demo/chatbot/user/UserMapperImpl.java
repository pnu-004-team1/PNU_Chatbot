package com.pnu.demo.chatbot.user;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperImpl implements UserMapper {

    @Autowired
    MemberRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private MongoClient mongoClient;

    public Member readMember(String username) {
        Member _member = new Member();

        MongoDatabase database = mongoClient.getDatabase("chatbot");
        MongoCollection<Document> collection = database.getCollection("member");

        Document document = collection.find(Filters.eq("username", username)).first();

        if (document != null) {
            Criteria criteria = Criteria.where("username").is(username);
            Query query = new Query(criteria);
            _member = mongoTemplate.findOne(query, Member.class, "member");
        }

        return _member;
    }

    public List<GrantedAuthority> readAuthority(String username) {
        Member _member = new Member();

        MongoDatabase database = mongoClient.getDatabase("chatbot");
        MongoCollection<Document> collection = database.getCollection("member");

        Document document = collection.find(Filters.eq("username", username)).first();

        List<GrantedAuthority> authorities = null;

        if (document != null) {
            Criteria criteria = Criteria.where("username").is(username);
            Query query = new Query(criteria);
            _member = mongoTemplate.findOne(query, Member.class, "member");
            authorities = _member.getAuthorities();
        }

        return authorities;
    }

    public void createMember(Member member) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String username = member.getUsername();
        String password = passwordEncoder.encode(member.getPassword());
        String name = member.getName();

        Member _member = new Member(username, password, name);
        repository.save(_member);
    }

    public void createAuthority(Member member) {

    }

    public void deleteMember(String username) {

    }

    public void deleteAuthority(String username) {

    }
}
