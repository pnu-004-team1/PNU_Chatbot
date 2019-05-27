package com.pnu.demo.chatbot.counselingConnection;

import com.mongodb.*;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class CounselingConnection {
    private static LocalTime beginTime = LocalTime.of(9, 0);
    private static LocalTime endTime = LocalTime.of(17, 0);
    private String mongoDBIP = "164.125.69.186";
    private int mongoDBPort = 27018;
    private int waitingStaff;

    public String getStringResult() {
        MongoClient  mongoClient = new MongoClient(new ServerAddress(mongoDBIP, mongoDBPort));
        DB db = mongoClient.getDB("CounselingConnection");
        DBCollection collection = db.getCollection("Collection");

        if(!checkWorkTime()) {
            return "지금은 근무시간이 아닙니다.\n근무시간은 " + beginTime.toString() + " - " + endTime.toString() + " 입니다.";
        } else if(!checkWaitStaff(collection)) {
            counselingCheck(collection);
            return "현재 상담 가능 인원이 없어 연결이 지연됩니다.\n연결 예상 시간은 " + estimatedTime(collection).toString() + " 입니다.";
        }

        staffConnect(collection);
        counselingCheck(collection);

        return "접수";
    }

    private void staffConnect(DBCollection collection) {
        DBObject waitStaff = collection.findOne("WaitStaff");
        int wStaff = Integer.parseInt(waitStaff.get("WaitStaff").toString());

        if(wStaff > 0) {
            BasicDBObject updateQuery = new BasicDBObject();
            updateQuery.put("WaitStaff", "" + (wStaff - 1));
            BasicDBObject searchQuery = new BasicDBObject().append("WaitStaff", waitStaff.get("WaitStaff"));
            collection.update(searchQuery, updateQuery);
        }
    }

    private void counselingCheck(DBCollection collection) {
        DBObject waitCounseling = collection.findOne("WaitCounseling");
        int wCounseling = Integer.parseInt(waitCounseling.get("WaitCounseling").toString());

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.put("WaitCounseling", "" + (wCounseling + 1));
        BasicDBObject searchQuery = new BasicDBObject().append("WaitCounseling", waitCounseling.get("WaitCounseling"));
        collection.update(searchQuery, updateQuery);
    }

    private boolean checkWorkTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(beginTime) && currentTime.isBefore(endTime);
    }
    private boolean checkWaitStaff(DBCollection collection) {
        DBObject waitStaff = collection.findOne("WaitingStaff");
        this.waitingStaff =  Integer.parseInt(waitStaff.get("WaitingStaff").toString());

        return waitingStaff > 0;
    }

    private LocalTime estimatedTime(DBCollection collection) {
        LocalTime estimatedTime = LocalTime.now();
        DBObject maxStaff = collection.findOne("MaxStaff");
        DBObject waitCounseling = collection.findOne("WaitCounseling");
        int mStaff = Integer.parseInt(maxStaff.get("MaxStaff").toString());
        int wCounseling = Integer.parseInt(waitCounseling.get("WaitCounseling").toString());

        estimatedTime = estimatedTime.plusMinutes(wCounseling/mStaff*15);

        return estimatedTime;
    }
}
