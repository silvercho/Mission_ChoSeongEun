package com.example.mission_choseongeun;

import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private int nextQuoteNumber =1; // 명언 번호 변수

    public App(){
        scanner = new Scanner(System.in);
    }

    public void run(){
        System.out.println("== 명언 앱 ==");

        while (true){
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")){
                break;
            } else if (cmd.equals("등록")){
                System.out.print("명언 : ");
                String content = scanner.nextLine();
                System.out.print("작가 : ");
                String authorName = scanner.nextLine();

                System.out.println(nextQuoteNumber + "번 명언이 등록 되었습니다.");

            }
        }
    }
}