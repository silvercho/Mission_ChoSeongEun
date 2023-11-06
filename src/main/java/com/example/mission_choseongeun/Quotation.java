package com.example.mission_choseongeun;

public class Quotation {
    int id;
    String content;
    String authorName;

    public Quotation(int id, String content, String authorName) { //생성자 만들기 alt+insert
        this.id = id;
        this.content = content;
        this.authorName = authorName;
    }
}
