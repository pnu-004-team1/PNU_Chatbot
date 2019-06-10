package com.pnu.demo.chatbot.academicCalendar;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mongodb.*;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class AcademicCalendarInfoManager {
    private Elements termE;
    private Elements textE;
    private JSONArray calendarArray;
    private String result;
    private String mongoDBIP = "164.125.69.186";
    private int mongoDBPort = 27018;
    private DateFormat df = new SimpleDateFormat("yyyy.MM.dd");

    private void getInfo(String event, int begin, int end) {
        MongoClient  mongoClient = new MongoClient(new ServerAddress(mongoDBIP, mongoDBPort));
        DBCollection collection = null;
        if(mongoClient != null) {
            DB db = mongoClient.getDB("AcademicalCalendar");
            collection = db.getCollection("Collection");
        }
        int i = 0;

        if(mongoClient != null) {
            getEvent(event, begin, end);

            for (Element term : this.termE) {
                BasicDBObject document = new BasicDBObject();
                document.put("termE", term.text());
                document.put("textE", this.textE.get(i).text());
            }
            return;
        }
        if(!checkTimeStamp(collection)) {
            getEvent(event, begin, end);

            for (Element term : this.termE) {
                BasicDBObject document = new BasicDBObject();
                document.put("termE", term.text());
                document.put("textE", this.textE.get(i).text());

                collection.insert(document);
                i++;
            }
        }
        else
            getDB(event, begin, end);
/*      확인용 코드
        DBCursor cursorDocBuilder = collection.find();
        while (cursorDocBuilder.hasNext()) {
            System.out.println(cursorDocBuilder.next());
        }
        System.out.println("-----------db 확인용--------------------");
 */
    }

    private boolean checkTimeStamp(DBCollection collection) {
        DBCursor timeStamp = collection.find();
        Calendar currentDate = Calendar.getInstance();
        Calendar updateDate = Calendar.getInstance();

        if(!timeStamp.hasNext()) {
            insertTimeStamp(currentDate, collection);
            return false;
        }

        try {
            updateDate.setTime(this.df.parse(timeStamp.next().get("TimeStamp").toString()));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("parsing error in checkTimeStamp");
        }

        long diffDays = (currentDate.getTimeInMillis() - updateDate.getTimeInMillis()) / ( 24*60*60*1000);
        if(diffDays > 30) {
            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                collection.remove(cursor.next());
            }

            insertTimeStamp(currentDate, collection);
            return false;
        }

        return true;
    }

    private void insertTimeStamp(Calendar currentDate, DBCollection collection) {
        String date = df.format(currentDate.getTime());
        BasicDBObject insertQuery = new BasicDBObject();
        insertQuery.put("TimeStamp", date);

        collection.insert(insertQuery);
    }

    private void getDB(String event, int begin, int end) {
        MongoClient  mongoClient = new MongoClient(new ServerAddress(mongoDBIP, mongoDBPort));
        DB db = mongoClient.getDB("AcademicalCalendar");
        DBCollection collection = db.getCollection("Collection");

        Calendar beginDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        Calendar date = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        beginDate.add(Calendar.DATE, begin);
        endDate.add(Calendar.DATE, end);

        this.result = new String();
        this.calendarArray = new JSONArray();

        DBCursor cursor = collection.find();
        if(cursor.hasNext())
            cursor.next();
        try {
            while (cursor.hasNext()) {
                String term = cursor.next().get("termE").toString();
                date.setTime(this.df.parse(term));
                date2.setTime(this.df.parse(term.substring(12)));
                String text = cursor.curr().get("textE").toString();

                if ((date2.equals(beginDate) || date2.after(beginDate)) && date.before(endDate) && isCheck(event, text)) {
                    JSONObject calendarObject = new JSONObject();
                    calendarObject.put("날짜", term);
                    calendarObject.put("학사", text);
                    this.calendarArray.add(calendarObject);

                    this.result += term + "   " + text + "\n";
                }
            }
        } catch (ParseException e) {
        e.printStackTrace();
        System.out.println("parsing error in getDB");
        }
    }

    public String getStringResult(String event) {
        int beginDate = 0, endDate = 365, targetDate;
        Calendar today = Calendar.getInstance();
        Calendar compDate = Calendar.getInstance();
        today.setTime(new Date());
        compDate.setTime(new Date());

        if(event == null)
            event = "";

        if(event.contains("올해")) {
            event = event.replaceFirst("올해", "");
            event = event.replace(" ","");
            endDate = today.getActualMaximum(today.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) + 1;
        }
        else if(event.contains("달")) {
            if(event.split("달").length > 1)
                event = event.split("달")[1];
            else
                event = "";
            endDate = today.getActualMaximum(today.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH);
        }
        else if(event.contains("월")) {
            targetDate = Integer.parseInt(event.split("월")[0]);

            if(event.split("월").length > 1)
                event = event.split("월")[1];
            else
                event = "";
            compDate.add(Calendar.MONTH,targetDate - today.get(Calendar.MONTH) - 1);
            beginDate = compDate.get(Calendar.DAY_OF_YEAR) - compDate.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_YEAR);
            endDate = beginDate + compDate.getActualMaximum(Calendar.DAY_OF_MONTH) - 1;
        }

        if(event.equals("휴일")) {
            getInfo("개교", beginDate, endDate);
            getInfo("휴가", beginDate, endDate);
        }
        else
            getInfo(event, beginDate, endDate);

        return this.result;
    }

    public JSONArray getJSONResult(String event)
    {
        getStringResult(event);
        return calendarArray;
    }

    private boolean isCheck(String event, String academicEvent) {
        return academicEvent.contains(event);
    }

    private void getEvent(String event, int begin, int end) {
        Element text;
        this.result = new String();

        Calendar beginDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        Calendar date = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        beginDate.add(Calendar.DATE, begin);
        endDate.add(Calendar.DATE, end);

        this.calendarArray = new JSONArray();

        try {
            int i = 0;
            connecting();

            for(Element term : this.termE) {
                date.setTime(this.df.parse(term.text()));
                date2.setTime(this.df.parse(term.text().substring(12)));
                text = this.textE.get(i);

                if((date2.equals(beginDate) || date2.after(beginDate)) && date.before(endDate) && isCheck(event, text.text())) {
                    JSONObject calendarObject = new JSONObject();
                    calendarObject.put("날짜", term);
                    calendarObject.put("학사", text);
                    this.calendarArray.add(calendarObject);

                    this.result += term.text() + "   " + text.text() + "\n";
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO error in getEvent");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("parsing error in getEvent");
        }
    }

    private void connecting() throws IOException {
        Document document = parsing();
        this.termE = document.select("th");
        Document document1 = parsing();
        this.textE = document1.select("td");
        this.termE.remove(0);		//delete useless information
        this.termE.remove(0);
    }

    private Document parsing() throws IOException {
        Connection.Response response = Jsoup.connect("https://www.pusan.ac.kr/kor/CMS/Haksailjung/PopupView.do")
                .method(Connection.Method.GET)
                .execute();
        return response.parse();
    }
}

