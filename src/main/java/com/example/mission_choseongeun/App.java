package com.example.mission_choseongeun;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner;
    int lastQuotationId;
    List<Quotation> quotations;

    public App() {
        scanner = new Scanner(System.in);
        lastQuotationId =0;
        quotations = new ArrayList<>();
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제?")) { // "삭제?id="로 시작하는 명령을 처리
                actionRemove(cmd);
            } else if (cmd.startsWith("수정?")) {
                actionModify(cmd);
            }
        }
    }
    void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        lastQuotationId++; //번호는 추가될 때 마다  계속 증가한다.
        int id = lastQuotationId;

        Quotation quotation = new Quotation(id, content, authorName);
        quotations.add(quotation);

        System.out.printf("%d번 명언이 등록 되었습니다. \n" ,lastQuotationId);
    }
    void actionList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        if (quotations.isEmpty())
            System.out.println("등록된 명언이 없습니다.");

        for (int i = quotations.size() -1; i >=0; i--) {
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.id, quotation.authorName, quotation.content);
        }
    }

    void actionRemove(String cmd) {
        int id = getParamAsInt (cmd, "id",0);

        if(id == 0){
            System.out.println("id를 정확히 입력해 주세요.");
            return;
        }
        System.out.printf("%d번 명언을 삭제합니다.\n", id);
    }

    void actionModify(String cmd) {
        int id = getParamAsInt(cmd, "id" ,0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해 주세요.");
            return; // 함수를 끝낸다.
        }
        System.out.printf("%d번 명언을 수정합니다. \n",id );
    }
    int getParamAsInt(String cmd, String paramName, int defaultValue){
        String[] cmdBits = cmd.split("\\?" ,2); //'?' 문자를 기준으로 최대 2개의 부분으로 나눈다. split 메서드는 문자열을 나누는 메서드이다.
        String queryString = cmdBits[1]; // cmd 문자열의 두번째 부분(인덱스1)을 가져와서 queryString 문자열에 저장.

        String[] queryStringBits = queryString.split("&"); // queryString 을 '&'기준으로 나누어 각가의 쿼리 매개변수를 분리합니다.

        for(int i=0; i<queryStringBits.length; i++){ //for반복문을 통해 각 쿼리 매개변수를 처리합니다.
            String queryParamStr = queryStringBits[i];

            String[] queryParamStrBits = queryParamStr.split("=",2); // 각 매개변수를 '=' 기준으로 2개로 나눔. 이를 통해 매개변수 이름과 값을 분리합니다.
            String _paramName = queryParamStrBits[0]; // 매개변수 이름을 가져와 '_paramName'변수에 저장.
            String paramValue = queryParamStrBits[1];// 매개변수 이름을 가져와 'paramValue'변수에 저장.

            if (_paramName.equals(paramName)){ //현재 매개변수의 이름이 함수로 전달된 paramName과 일치하는 경우, 해당 매개변수의 값을 추출
                try { // 매개변수 값을 정수로 변환하려 시도, 성공하면 정수 값을 반환
                    return Integer.parseInt(paramValue);
                } catch (NumberFormatException e){ // 변환이 실패하면(NumberFormatException 예외 발생), 기본값인 defaultValue를 반환
                    return defaultValue;
                }
            }
        }
        return  defaultValue;  //모든 쿼리 매개변수를 확인했는데도 원하는 매개변수(paramName)를 찾지 못한 경우, 기본값인 defaultValue를 반환
    }
}

