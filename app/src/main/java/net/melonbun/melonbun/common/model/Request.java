package net.melonbun.melonbun.common.model;

import java.util.List;

public class Request {

    private String id;
    private String title;
    private String body;
    private String date;
    private String status;
    private Price price;
    private List<String> tags;

    public Request(String id, String title, String body, String date, String status, Price price, List<String> tags) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.status = status;
        this.price = price;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Price getPrice() {
        return price;
    }

    public List<String> getTags() {
        return tags;
    }
}
