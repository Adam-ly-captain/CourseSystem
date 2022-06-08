import edu.fjnu501.crms.domain.User;
import edu.fjnu501.crms.utils.MD5Password;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void test1() {
        User user = new User();
        user.setAccount("t1");
        user.setPassword("123");
        String s = MD5Password.MD5Pwd(user);
        System.out.println(s);
    }

}
