package com.app.soccerveteranv.vo;

/**
 * Created by sungbo on 2016-04-26.
 */
public class Content {

    int id;
    String vedioid;
    String username;
    String content;

    public Content(int id, String vedioid, String username, String content) {
        this.id = id;
        this.vedioid = vedioid;
        this.username = username;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVedioid() {
        return vedioid;
    }

    public void setVedioid(String vedioid) {
        this.vedioid = vedioid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
