package com.example.mission_choseongeun;

import java.util.ArrayList;
import java.util.List;

public class Rq {
    String cmd;
    String action;
    String queryString;
    List<String> paramNames;
    List<String> paramValues;

    Rq(String cmd) { // 문자열 cmd를 입력받아 Rq 클래스의 객체를 초기화
        paramNames = new ArrayList<>();
        paramValues = new ArrayList<>();

        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?" ,2);
        action = cmdBits[0].trim(); //cmdBits 배열의 첫번째 부분을 action 멤버 변수에 저장합니다.
        queryString = cmdBits[1].trim(); //  trim 메서드를 사용하여 문자열 양쪽의 공백을 제거합니다.

        String[] queryStringBits = queryString.split("&"); //문자열을 & 문자를 기준으로 나누어 각각의 쿼리 매개변수를 분리

        for (int i =0; i < queryStringBits.length; i++){ //반복문 (length는 문자열의 길이)
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=",2);

            String paramName = queryParamStrBits[0]; // 매개변수 이름을 가져와 paramName에 저장
            String paramValue = queryParamStrBits[1]; // 가져와 paramValue에 저장.

            paramNames.add(paramName);
            paramValues.add(paramValue);
            // ArrayLists 에 각각의 매개변수 이름과 값을 추가합니다.
        }
    }

}
