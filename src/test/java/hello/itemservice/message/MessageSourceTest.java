package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        System.out.println("result = " + result);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode() {
        //String result = ms.getMessage("no_code", null, null);
        //System.out.println("result = " + result);
        //assertThat(result).isEqualTo("안녕");
        // NoSuchMessageException
        Assertions.assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);

    }

    @Test
    void notFoundMessageCodeDefault() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        System.out.println("result = " + result);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argMessage() {
        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        System.out.println("result = " + result);
        assertThat(result).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLang() {
        String result = ms.getMessage("hello", null, null);
        String result2 = ms.getMessage("hello", null, Locale.KOREA);
        System.out.println("result = " + result);
        System.out.println("result2 = " + result2);

        assertThat(result).isEqualTo("안녕");
        assertThat(result2).isEqualTo("안녕");
    }

    @Test
    void enLang() {
        String result = ms.getMessage("hello", null, Locale.ENGLISH);

        assertThat(result).isEqualTo("hello");
    }
}
