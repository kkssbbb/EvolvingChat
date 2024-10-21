import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class LogTest {
    private static final Logger logger = LogManager.getLogger(LogTest.class);

    @Test
    public void test() {
        System.out.println("Hello World");

        logger.info("프로그램 시작");
        logger.debug("이것은 디버그 메시지입니다");
        logger.warn("경고: 뭔가 이상해요!");
        logger.error("오류가 발생했습니다", new RuntimeException("테스트 예외"));
        logger.info("프로그램 종료");
    }


}
