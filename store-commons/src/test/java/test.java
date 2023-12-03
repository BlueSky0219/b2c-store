import com.sky.constants.UserConstants;
import com.sky.utils.MD5Util;

/**
 * @author bluesky
 * @create 2022-11-25-15:18
 */
public class test {

    public static void main(String[] args) {
        String encode = MD5Util.encode("123456"+ UserConstants.USER_SLAT);
        System.out.println("encode = " + encode);
    }
}
