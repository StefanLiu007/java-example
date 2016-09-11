import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Stefan on 2016/9/10.
 */
public class InetAdressTest {
    public static void main(String[] args) throws IOException {
        InetAddress ip = InetAddress.getByName("www.baidu.com");
        System.out.println("baidu是否可达："+ip.isReachable(2000));
        System.out.println(ip.getHostAddress());
        InetAddress ip1 = InetAddress.getByAddress(new byte[]{127,0,0,1});
        System.out.println(ip1.isReachable(5000)+"本机是否可到达");
        System.out.println(ip1.getCanonicalHostName());//获取ip1实例对应的全限定域名
    }
}
