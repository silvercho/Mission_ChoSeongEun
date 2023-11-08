package com.example.mission_choseongeun;


import java.io.Serializable;

public class Quotation implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String content;
    String authorName;

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Quotation(int id, String content, String authorName) { //생성자 만들기 alt+insert
        this.id = id;
        this.content = content;
        this.authorName = authorName;
    }
    @Override
    public String toString(){
        return "Quotation{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
