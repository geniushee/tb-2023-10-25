package com.ll.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner scanner; // 입력을 받기 위한 scanner 생성
    private int quotesid; // 명언 id넘버 부여를 위한 객체
    private List<Quote> quotations; // 명언 저장소
    private Rq Rq; // Rq 정규식을 이용한 queryString 분해

    public App() {
        scanner = new Scanner(System.in);
        quotesid = 0;
        quotations = new ArrayList<>();

        initTestData();

    }


    private void initTestData() {
        for (int i = 0; i < 10; i++) {
            write("명언" + i+1, "작가" + i+1);
        }
    }

    public void run() {
        System.out.println("=== 명언 앱 ===");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            Rq = new Rq(cmd);
            String action = Rq.getAction();

            switch (action) {
                case "종료":
                    return;
                case "등록":
                    post();
                    break;
                case "목록":
                    list();
                    break;
                case "삭제":
                    remove(Rq);
                    break;
                case "수정":
                    modify(Rq);
                    break;
            }
        }
    }

//        System.out.printf("입력하신 명령: %s\n", cmd);

    private void post() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();
//                System.out.printf("명언 : %s, 작가 : %s\n", content, authorName);
        write(content, authorName);

        System.out.println(quotesid + "번 명언이 등록되었습니다.");
    }

    private void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("--------------------------");
        for (Quote Q : quotations) {
            System.out.printf("%d / %s / %s\n", Q.getId(), Q.getAuthor(), Q.getContent());
        }
    }

    private void remove(Rq rq) {
        // cmd를 받았을 때 처리하는 방법 1.replace  2.substring
        int id = rq.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        int index = getIndexOfQuotationsById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        }
        quotations.remove(index);
        System.out.println(id + "번 명언이 삭제되었습니다.");

    }

    private void modify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);
        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }
        int index = getIndexOfQuotationsById(id);

        Quote quote = quotations.get(index);

        System.out.print("명언(기존) : " + quote.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가(기존) : " + quote.getAuthor());
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        quote.setContent(content);
        quote.setAuthor(authorName);

        System.out.println(id + "번 명언이 수정되었습니다.");
    }

    private int getIndexOfQuotationsById(int id) {
        for (int i = 0; i < quotations.size(); i++) {
            Quote quotation = quotations.get(i);

            if (quotation.getId() == id) {
                return i;
            }
        }
        return -1;
    }

    void write(String content, String author){
        quotesid++;
        int id = quotesid;
        Quote quotation = new Quote(id, content, author);
        quotations.add(quotation);
    }

    // Rq를 이용하여 리팩토링 함.
//    int getParamAsInt(String cmd, String paramName, int DefaultValue) {
//        String[] cmdBits = cmd.split("\\?", 2);
//        String action = cmdBits[0];
//        String queryString = cmdBits[1];
//        String[] queryStringBits = queryString.split("&");
//
//        for (int i = 0; i < queryString.length(); i++) {
//            String queryParamStr = queryStringBits[i];
//
//            String[] queryParamBits = queryParamStr.split("=", 2);
//            String _paramName = queryParamBits[0];
//            String paramValue = queryParamBits[1];
//
//            if(_paramName.equals(paramName)) {
//                try {
//                    return Integer.parseInt(paramValue);
//                }
//                catch(NumberFormatException e){
//                    return DefaultValue;
//                }
//            }
//        }
//        return DefaultValue;
//    }
}

