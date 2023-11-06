package com.example.mission_choseongeun;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(){
        scanner = new Scanner(System.in);
    }

    public void run(){
        System.out.println("== 명언 앱 ==");

        while (true){
            System.out.println("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")){
                break;
            }
        }
    }
}