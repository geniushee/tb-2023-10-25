package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    void run() {
        System.out.println("=== 명언 앱 ===");
        List<Quote> quotations = new ArrayList<>();
        int quotesid = 0;

        while (true) {
            System.out.print("명령) ");

            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine();
            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {

                System.out.print("명언 : ");
                String content = scanner.nextLine();
                System.out.print("작가 : ");
                String authorName = scanner.nextLine();
//                System.out.printf("명언 : %s, 작가 : %s\n", content, authorName);

                quotesid++; // 등록시 번호 증가
                quotations.add(new Quote(quotesid, content, authorName));

                System.out.println(quotesid + "번 명언이 등록되었습니다.");
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("--------------------------");
                for (Quote Q : quotations) {
                    System.out.printf("%d / %s / %s\n", Q.no, Q.author, Q.content);
                }
            }


            System.out.printf("입력하신 명령: %s\n", cmd);
        }

    }
}
