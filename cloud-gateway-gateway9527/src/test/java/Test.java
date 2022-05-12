import java.time.ZonedDateTime;

/**
 * @author sonyan
 * @create 2022/4/19 13:37
 */
public class Test {

    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now(); //默认时区
        System.out.println(now);
    }
}
