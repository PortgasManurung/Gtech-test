package com.ecommerce.gtech.models.entity;

public class Mail {
    private String to;
    private String subject;
    private String link;

    public Mail() {
    }

    public Mail(String to, String subject, String link) {
        this.to = to;
        this.subject = subject;
        this.link = link;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Mail{" +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + link + '\'' +
                '}';
    }

}
