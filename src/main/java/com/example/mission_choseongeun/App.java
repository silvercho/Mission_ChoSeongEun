package com.example.mission_choseongeun;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class App {
    Scanner scanner;
    int lastQuotationId;
    List<Quotation> quotations;

    public App() {
        scanner = new Scanner(System.in);
        lastQuotationId = 0;

        //데이터 읽어오기
        List<Quotation> loadedQuotes = loadQuotesFromFile("quotes.dat");
        quotations = (loadedQuotes != null) ? loadedQuotes : new ArrayList<>();
        if (loadedQuotes !=null){
            lastQuotationId = quotations.get(quotations.size() -1).getId();
        }
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            Rq rq = new Rq(cmd); //Rq 클래스

            switch (rq.getAction()) {
                case "종료":
                    return;
                case "등록":
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionRemove(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
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

        System.out.printf("%d번 명언이 등록 되었습니다. \n", lastQuotationId);
        // 명언을 등록 후 파일저장
        saveQuotesToFile("quotes.dat", quotations);
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        if (quotations.isEmpty())
            System.out.println("등록된 명언이 없습니다.");

        for (int i = quotations.size() - 1; i >= 0; i--) {
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.id, quotation.authorName, quotation.content);
        }
    }

    void actionRemove(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해 주세요.");
            return;
        }
        int index = getIndexOfQuotationById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        quotations.remove(index);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);

        // 명언을 등록 후 파일저장
        saveQuotesToFile("quotes.dat", quotations);
    }
    int getIndexOfQuotationById(int id) {
        for (int i = 0; i < quotations.size(); i++) {
            Quotation quotation = quotations.get(i);

            if (quotation.id == id) {
                return i;
            }
        }

        return -1;
    }

    void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해 주세요.");
            return;
        }
        int index = getIndexOfQuotationById(id);

        if (index == -1){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }
        Quotation quotation = quotations.get(index);

        System.out.print("명언 : ");
        String newContent = scanner.nextLine();
        System.out.print("작가 : ");
        String newAuthorName = scanner.nextLine();

        // 명언 및 작가 정보 수정
        quotation.content = newContent;
        quotation.authorName = newAuthorName;

        System.out.printf("%d번 명언을 수정했습니다.\n", id);
        // 명언을 등록 후 파일저장
        saveQuotesToFile("quotes.dat", quotations);
    }

    public void saveQuotesToFile(String fileName, List<Quotation> quotes) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(quotes);
            System.out.println("저장");
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("문제 발생");
        }
    }
    public List<Quotation> loadQuotesFromFile(String fileName){
        List<Quotation> quotes = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            quotes = (List<Quotation>) ois.readObject();
            System.out.println("불러오기.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("문제 발생");
        }
        return quotes;
    }
}

