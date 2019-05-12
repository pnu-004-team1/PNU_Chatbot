package com.pnu.demo.chatbot.academicCalendar;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mongodb.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class AcademicCalendarParsing {
    private Elements termE;
    private Elements textE;
    private String result = new String();
    private String mongoDBIP = "164.125.69.186";
    private int mongoDBPort = 27018;

    public void insertDB() {
        MongoClient  mongoClient = new MongoClient(new ServerAddress(mongoDBIP, mongoDBPort));
        DB db = mongoClient.getDB("AcademicalCalendar");
        DBCollection collection = db.getCollection("Collection");
        int i = 0;

        getEvent("",0,365);

        for(Element term : this.termE){
            BasicDBObject document = new BasicDBObject();
            document.put("termE", term.text());
            document.put("textE", this.textE.get(i).text());

            collection.insert(document);
            i++;
        }

/*      확인용 코드
        DBCursor cursorDocBuilder = collection.find();
        while (cursorDocBuilder.hasNext()) {
            System.out.println(cursorDocBuilder.next());
        }
*/
    }

    public String getResult(String event) {
        int beginDate = 0, endDate = 365, targetDate;
        Calendar today = Calendar.getInstance();
        Calendar compDate = Calendar.getInstance();
        today.setTime(new Date());
        compDate.setTime(new Date());

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
            getEvent("개교", beginDate, endDate);
            getEvent("휴가", beginDate, endDate);
        }
        else
            getEvent(event, beginDate, endDate);

        return this.result;
    }

    private boolean isCheck(String event, String academicEvent) {
        if(academicEvent.contains(event))
            return true;
        return false;
    }

    private void getEvent(String event, int begin, int end) {
        Element text;

        Calendar beginDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        Calendar date = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        beginDate.setTime(new Date());
        beginDate.add(Calendar.DATE, begin);
        endDate.setTime(new Date());
        endDate.add(Calendar.DATE, end);

        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");

        try {
            int i = 0;
            connecting();

            for(Element term : this.termE) {
                date.setTime(df.parse(term.text()));
                date2.setTime(df.parse(term.text().substring(12)));
                text = this.textE.get(i);

                if((date2.equals(beginDate) || date2.after(beginDate)) && date.before(endDate) && isCheck(event, text.text())) {
                    this.result += term.text() + "   " + text.text() + "\n";
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("aa");
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

