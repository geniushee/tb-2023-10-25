package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner; // 입력을 받기 위한 scanner 생성
    int quotesid; // 명언 id넘버 부여를 위한 객체
    List<Quote> quotations; // 명언 저장소

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
            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) post();
            else if (cmd.equals("목록")) list();
            else if (cmd.startsWith("삭제?")) remove(cmd);
        }
//        System.out.printf("입력하신 명령: %s\n", cmd);
    }

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

    void remove(String cmd){
        // cmd를 받았을 때 처리하는 방법 1.replace  2.substring
        String idStr = cmd.replace("삭제?id=", "");
        int id = Integer.parseInt(idStr);

        System.out.println(idStr + "번 명언을 삭제합니다.");
    }
}

