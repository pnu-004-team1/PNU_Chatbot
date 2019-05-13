
package com.pnu.demo.chatbot.faqList;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface faqMongoDBRepository extends MongoRepository<faqList, String> {
    faqList findBy_id(ObjectId _id);
}

