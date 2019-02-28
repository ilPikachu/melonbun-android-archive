package net.melonbun.melonbun.request;

public class Request {
    private String requestTitle;
    private String requestDate;
    //TODO: Image Binary class

    public Request(String requestTitle, String requestDate) {
        this.requestTitle = requestTitle;
        this.requestDate = requestDate;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public String getRequestDate() {
        return requestDate;
    }
}
