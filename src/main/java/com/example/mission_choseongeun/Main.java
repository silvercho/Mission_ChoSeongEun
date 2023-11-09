package com.example.mission_choseongeun;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {
//        App app = new App(); << App 클래스의 새로운 객체를 생성하고 그 객체를 app 변수에 할당함.
//        app.run(); << app 변수에 할당된 App 클래스의 객체를 사용해서 run 메서드를 호출함.

        new App().run();

        JSONParser parser = new JSONParser();

        try {
            // "data.json" 파일을 읽어 JSON 객체로 파싱
            Object obj;
            obj = parser.parse(new FileReader("data.json"));

            // JSON 객체로 변환
            JSONObject jsonObject = (JSONObject) obj;

            // 이제 jsonObject를 사용하여 JSON 데이터에 액세스할 수 있음
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
