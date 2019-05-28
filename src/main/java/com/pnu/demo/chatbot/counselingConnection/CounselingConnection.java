package com.pnu.demo.chatbot.counselingConnection;

import com.mongodb.*;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class CounselingConnection {
    private LocalTime beginTime = LocalTime.of(9, 0);
    private LocalTime endTime = LocalTime.of(17, 0);
    private String mongoDBIP = "164.125.69.186";
    private int mongoDBPort = 27018;
    private int waitingStaff;
    private MongoClient mongoClient;

    private DBCollection getDbCollection() {
        this.mongoClient = new MongoClient(new ServerAddress(mongoDBIP, mongoDBPort));
        DB db = mongoClient.getDB("CounselingConnection");
        return db.getCollection("Collection");
    }

     public void dropDB() {
        DBCollection collection = getDbCollection();
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            collection.remove(cursor.next());
        }
    }

    public void getStart() {                            //db 시작 시 추가
        DBCollection collection = getDbCollection();

        DBCursor cursorDocBuilder = collection.find();
        if(!cursorDocBuilder.hasNext()) {
            BasicDBObject document = new BasicDBObject();
            document.put("WaitStaff", 1);
            collection.insert(document);

            BasicDBObject document2 = new BasicDBObject();
            document2.put("WaitCounseling", 0);
            collection.insert(document2);

            BasicDBObject document3 = new BasicDBObject();
            document3.put("MaxStaff", 1);
            collection.insert(document3);
        }
/*      확인용 코드
        DBCursor cursorDocBuilder2 = collection.find();
        while (cursorDocBuilder2.hasNext()) {
            System.out.println(cursorDocBuilder2.next());
        }
        System.out.println("-----------db 확인용--------------------");
*/
    }

    public String getStringResult(int id, String counseling) {      //질문 접수
        DBCollection collection = getDbCollection();
        String result = new String();

        if(!checkWorkTime()) {
            result += "지금은 근무시간이 아닙니다.\n근무시간은 " + beginTime.toString() + " - " + endTime.toString() + " 입니다.";
        } else if(!checkWaitStaff(collection)) {
            result += "현재 상담 가능 인원이 없어 연결이 지연됩니다.\n연결 예상 시간은 " + estimatedTime(collection).toString() + " 입니다.";
        } else if(!checkCounseling(id, collection)) {
            return "이전에 접수 된 상담이 완료되지 않았습니다.";
        } else{
            result += "접수";
        }

        counselingCheck(collection);
        insertCounseling(id, counseling, collection);

        return result;
    }

    public JSONArray getJSONResult() {                            //질문 리스트 받기
        DBCollection collection = getDbCollection();
        JSONArray counselingArray = new JSONArray();

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("AnswerCheck", 0);
        DBCursor cursor = collection.find(whereQuery);
        while (cursor.hasNext()) {
            JSONObject counselingObject = new JSONObject();
            DBObject currentObject = cursor.next();
            counselingObject.put("id", currentObject.get("CustomerID").toString());
            counselingObject.put("question", currentObject.get("Counseling").toString());
            counselingArray.add(counselingObject);
        }

        return counselingArray;
    }

    public String insertAnswer(int id, String answer) {             //답변 완료
        DBCollection collection = getDbCollection();

        BasicDBObject updateQuery = new BasicDBObject().append("$set", new BasicDBObject().append("AnswerCheck", 1));
        BasicDBObject searchQuery = new BasicDBObject().append("CustomerID", id);
        collection.update(searchQuery, updateQuery);

        BasicDBObject document = new BasicDBObject();
        document.put("CustomerID", id);
        document.put("Counseling", answer);
        document.put("AnswerCheck", 2);
        collection.insert(document);

        int wCounseling = getWCounseling(collection);

        BasicDBObject updateQuery2 = new BasicDBObject();
        updateQuery2.put("WaitCounseling", wCounseling - 1);
        BasicDBObject searchQuery2 = new BasicDBObject().append("WaitCounseling", wCounseling);
        collection.update(searchQuery2, updateQuery2);

        return "답변 완료";
    }

    private int getWCounseling(DBCollection collection) {
        DBCursor waitCounseling = collection.find();
        waitCounseling.next();
        return Integer.parseInt(waitCounseling.next().get("WaitCounseling").toString());
    }

    public String getAnswer(int id) {                               //답변 받기
        DBCollection collection = getDbCollection();

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("CustomerID", id);
        whereQuery.put("AnswerCheck", 0);
        DBCursor cursor = collection.find(whereQuery);
        if(cursor.hasNext())
            return "아직 답변이 완료되지 않았습니다.";

        BasicDBObject whereQuery2 = new BasicDBObject();
        whereQuery.put("CustomerID", id);
        whereQuery2.put("AnswerCheck", 2);
        DBCursor cursor2 = collection.find(whereQuery2);
        if(cursor2.hasNext()) {
            return cursor2.next().get("Counseling").toString();
        }

        return "상담이 접수되지 않았습니다.";
    }

    private void insertCounseling(int id, String counseling, DBCollection collection) {
        BasicDBObject document = new BasicDBObject();
        document.put("CustomerID", id);
        document.put("Counseling", counseling);
        document.put("AnswerCheck", 0);
        collection.insert(document);
    }

    private void staffConnect(DBCollection collection) {
        DBCursor waitStaff = collection.find();
        waitStaff.next();
        int wStaff = Integer.parseInt(waitStaff.next().get("WaitStaff").toString());

        if(wStaff > 0) {
            BasicDBObject updateQuery = new BasicDBObject();
            updateQuery.put("WaitStaff", "" + (wStaff - 1));
            BasicDBObject searchQuery = new BasicDBObject().append("WaitStaff", ""+wStaff);
            collection.update(searchQuery, updateQuery);
        }
    }

    private void counselingCheck(DBCollection collection) {
        int wCounseling = getWCounseling(collection);

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.put("WaitCounseling", wCounseling + 1);
        BasicDBObject searchQuery = new BasicDBObject().append("WaitCounseling", wCounseling);
        collection.update(searchQuery, updateQuery);

    }

    private boolean checkCounseling(int id, DBCollection collection) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("AnswerCheck", 0);
        whereQuery.put("CustomerID", id);
        DBCursor cursor = collection.find(whereQuery);

        return cursor.hasNext();
    }

    private boolean checkWorkTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(beginTime) && currentTime.isBefore(endTime);
    }
    private boolean checkWaitStaff(DBCollection collection) {
        DBCursor waitStaff = collection.find();
        this.waitingStaff =  Integer.parseInt(waitStaff.next().get("WaitCounseling").toString());

        return waitingStaff > 0;
    }

    private LocalTime estimatedTime(DBCollection collection) {
        LocalTime estimatedTime = LocalTime.now();
        DBCursor waitCounseling = collection.find();
        waitCounseling.next();
        waitCounseling.next();
        int wCounseling = Integer.parseInt(waitCounseling.next().get("WaitCounseling").toString());
        int mStaff = Integer.parseInt(waitCounseling.next().get("MaxStaff").toString());

        estimatedTime = estimatedTime.plusMinutes(wCounseling/mStaff*15);

        return estimatedTime;
    }
}
