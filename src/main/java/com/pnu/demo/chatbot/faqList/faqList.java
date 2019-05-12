package com.pnu.demo.chatbot.faqList;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "faqList")
public class faqList {
    @Id
    private ObjectId _id;

    private String cmd;

    public faqList() {}

    public faqList(ObjectId _id, String cmd) {
        this._id = _id;
        this.cmd = cmd;
    }

    public String get_id() { return _id.toHexString(); }

    public void set_id(ObjectId _id) { this._id = _id; }

    public String getCmd() { return cmd; }

    public void setCmd(String cmd) { this.cmd = cmd; }

}
