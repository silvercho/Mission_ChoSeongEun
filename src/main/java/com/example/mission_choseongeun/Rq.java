package com.example.mission_choseongeun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rq {
    String cmd;
    String action;
    String queryString;
    Map<String,String> paramsMap; //Map은 리스트나 배열과 비슷하지만 순차적으로 요소 값을 구하지 않고 key를 통해 값을 얻는다.

    Rq(String cmd) { // 문자열 cmd를 입력받아 Rq 클래스의 객체를 초기화
        paramsMap = new HashMap<>();

        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?" ,2);
        action = cmdBits[0].trim(); //cmdBits 배열의 첫번째 부분을 action 멤버 변수에 저장합니다.

        if (cmdBits.length ==1){
            return;
        }
        queryString = cmdBits[1].trim();

        String[] queryStringBits = queryString.split("&"); //문자열을 & 문자를 기준으로 나누어 각각의 쿼리 매개변수를 분리

        for (int i =0; i < queryStringBits.length; i++){ //반복문 (length는 문자열의 길이)
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=",2);

            String paramName = queryParamStrBits[0]; // 매개변수 이름을 가져와 paramName에 저장
            String paramValue = queryParamStrBits[1]; // 가져와 paramValue에 저장.

           paramsMap.put(paramName,paramValue);
            // ArrayLists 에 각각의 매개변수 이름과 값을 추가합니다.
        }
    }

    String getAction(){
        return action;
    }
    public int getParamAsInt(String paramName, int defaultValue) {
        String paramValue = paramsMap.get(paramName);

        if (paramValue != null){
            try {
                return Integer.parseInt(paramValue);
            } catch (NumberFormatException e){
            }
        }
        return  defaultValue;
    }
}
