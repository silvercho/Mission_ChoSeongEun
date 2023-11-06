package com.example.mission_choseongeun;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private int nextQuoteNumber = 1; // 명언 번호 변수
    private List<String> quotes = new ArrayList<>();

    public App() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine();
                System.out.print("작가 : ");
                String authorName = scanner.nextLine();

                String newQuote = nextQuoteNumber + " / " + authorName + " / " + content; //"번호 / 작가 / 명언"
                quotes.add(newQuote);

                System.out.println(nextQuoteNumber + "번 명언이 등록 되었습니다.");
                nextQuoteNumber++; //번호는 추가될 때 마다  계속 증가한다.
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (String quote : quotes) {
                    System.out.println(quote);
                }
            } else if (cmd.startsWith("삭제?id=")) {          // "삭제?id="로 시작하는 명령을 처리
                String idStr = cmd.substring(7);
                try {
                    int quoteId = Integer.parseInt(idStr);
                    if (quoteId >= 1 && quoteId <= quotes.size()) {
                        quotes.remove(quoteId - 1);
                        System.out.printf("%d번 명언이 삭제되었습니다.\n" ,quoteId);
                    } else {
                        System.out.println("잘못된 명언 번호 입니다.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("잘못된 명언 번호 형식입니다. ");
                }
            }
        }
    }
}

