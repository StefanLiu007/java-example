import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Stefan on 2016/9/10.
 */
public class URLDecoderTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String keyWord = URLDecoder.decode("%E7%96%AF%E7%8B%82java","utf-8");
        System.out.println(keyWord);
        String urlStr = URLEncoder.encode("疯狂的android讲义","GBK");
        System.out.println(urlStr);
    }
}
