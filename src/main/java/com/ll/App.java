package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner; // 입력을 받기 위한 scanner 생성
    int quotesid; // 명언 id넘버 부여를 위한 객체
    List<Quote> quotations; // 명언 저장소
    Rq Rq; // Rq 정규식을 이용한 queryString 분해

    App() {
        scanner = new Scanner(System.in);
        quotesid = 0;
        quotations = new ArrayList<>();
    }

    void run() {
        System.out.println("=== 명언 앱 ===");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            Rq = new Rq(cmd);
            String action = Rq.getAction();

            switch (action) {
                case "종료":
                    break;
                case "등록":
                    post();
                case "목록":
                    list();
                case "삭제":
                    remove();
                case "수정":
                    modify();
            }
        }
    }

//        System.out.printf("입력하신 명령: %s\n", cmd);

    void post() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();
//                System.out.printf("명언 : %s, 작가 : %s\n", content, authorName);

        quotesid++; // 등록시 번호 증가
        quotations.add(new Quote(quotesid, content, authorName));

        System.out.println(quotesid + "번 명언이 등록되었습니다.");
    }

    void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("--------------------------");
        for (Quote Q : quotations) {
            System.out.printf("%d / %s / %s\n", Q.no, Q.author, Q.content);
        }
    }

    void remove() {
        // cmd를 받았을 때 처리하는 방법 1.replace  2.substring
        int id = Rq.getParamAsInt("id", 0);
        for (int i = quotations.size() - 1; i >= 0; i--) {
            int q_id = quotations.get(i).no;
            if (q_id == id) {
                quotations.remove(i);
                System.out.println((i + 1) + "번 명언이 삭제되었습니다.");
                return;
            }
        }


    }

//        System.out.println(idStr +"번 명언을 삭제합니다.");

    void modify() {

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

