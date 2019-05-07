package com.pnu.demo.chatbot.counselingConnection;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ServerAddress;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class CounselingConnection {
    private static LocalTime beginTime = LocalTime.of(9, 0);
    private static LocalTime endTime = LocalTime.of(17, 0);
    private String mongoDBIP = "164.125.69.186";
    private int mongoDBPort = 27018;
    int waitingStaff;

    public String connect() {
        if(!checkWorkTime())
            return "지금은 근무시간이 아닙니다.\n근무시간은 " + beginTime.toString() + " - " + endTime.toString() + " 입니다.";
        else if(!checkWaitStaff())
            return "현재 상담 가능 인원이 없어 연결이 지연됩니다.\n연결 예상 시간은 " + estimatedTime().toString() + " 입니다.";
        return "접수";
    }

    private boolean checkWorkTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(beginTime) && currentTime.isBefore(endTime);
    }

    private boolean checkWaitStaff() {
        MongoClient mongoClient = new MongoClient(new ServerAddress(mongoDBIP, mongoDBPort));
        MongoDatabase dataBase = mongoClient.getDatabase("Counseling");
        FindIterable<Document> dataBaseRecords = dataBase.getCollection("collectionName").find();
        MongoCursor<Document> iterator = dataBaseRecords.iterator();

        waitingStaff = iterator.next().getInteger("WaitingStaff");

        return waitingStaff > 0;
    }

    private LocalTime estimatedTime() {
        LocalTime estimatedTime = LocalTime.of(11, 0);
        int i = 0;

        while(i >= waitingStaff) {
            estimatedTime = estimatedTime.plusMinutes(15);
            i++;
        }
        
        return estimatedTime;
    }
}
