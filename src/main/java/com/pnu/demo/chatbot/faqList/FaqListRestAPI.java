package com.pnu.demo.chatbot.faqList;

import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/faqList")
public class FaqListRestAPI {
    @Autowired()
    private faqMongoDBRepository FAQMongoDBRepository;
    private MongoTemplate mongoTemplate;
    private faqList FAQList;


    public static List sortByValue(final Map map) {

        List<String> list = new ArrayList();

        list.addAll(map.keySet());

        Collections.sort(list,new Comparator() {
            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                return ((Comparable) v2).compareTo(v1);
            }
        });
        //Collections.reverse(list); // 주석시 오름차순
        return list;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public JSONObject getFAQList() {

        List<faqList> tmp = FAQMongoDBRepository.findAll();

        // 모든 결과 리스트 형태로 저장
        List<String> tmpStr = new ArrayList<>();
        for(faqList temp : tmp) {
            tmpStr.add(temp.getCmd());
            //System.out.println(temp.getCmd());
        }

        // 띄어쓰기 마다 단어 분리하여 저장
        List<String> tmpStr2 = new ArrayList<>();
        for(String temp : tmpStr) {
            String[] words = temp.split(" ");
            for(String tmp2 : words) {
                tmpStr2.add(tmp2);
                System.out.println(tmp2);
            }
        }

        // 맵 형태로 중복된 명령어 갯수 저장
        Map<String, Integer> counter = new HashMap<String, Integer>();

        for (String temp : tmpStr2) {
            Integer previousValue = counter.get(temp);

            counter.put(temp, previousValue == null ? 1 : previousValue+1 );
        }

        Iterator it = sortByValue(counter).iterator();

        JSONObject jsonObject = new JSONObject();

        for(int i = 0; i < 3; ++i) {
            String temp = (String) it.next();
            System.out.println(temp + " = " + counter.get(temp));
            jsonObject.put(temp,counter.get(temp).toString());
            System.out.println(jsonObject);
        }




        return jsonObject;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public faqList createFAQList(@Valid @RequestBody faqList FAQList) {
        FAQList.set_id(ObjectId.get());
        FAQMongoDBRepository.save(FAQList);
        return FAQList;
    }

}

