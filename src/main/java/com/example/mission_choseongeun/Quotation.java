package com.example.mission_choseongeun;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Quotation implements Serializable {

    private static final long serialVersionUID = 1L;
    int id;
    String content;
    String authorName;

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
