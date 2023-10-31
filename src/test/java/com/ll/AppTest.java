package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    @DisplayName("testutil genScanner 테스트")
    void t1(){
        Scanner scanner = testUtil.genScanner("""                
                등록
                이것은 명언이다.
                이것은 작가이다.
                이건 나오나?""".stripIndent());
        String cmd = scanner.nextLine();
        String content = scanner.nextLine();
        String authorname = scanner.nextLine();

        assertThat(cmd).isEqualTo("등록");
        assertThat(content).isEqualTo("이것은 명언이다.");
        assertThat(authorname).isEqualTo("이것은 작가이다.");
    }

    @Test
    @DisplayName("testutil.setOutToByteArray() 테스트")
    void t2(){
        ByteArrayOutputStream byteArrayOutputStream = testUtil.setOutToByteArray();

        System.out.println("2 / 작가 / 명언");

        String out = byteArrayOutputStream.toString().trim();

        assertThat(out).isEqualTo("2 / 작가 / 명언");

        testUtil.clearSetOutToByteArray(byteArrayOutputStream);
        System.out.println("이제는 화면에 출력됩니다.");
    }
}
