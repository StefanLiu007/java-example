import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by Stefan on 2016/9/10.
 */
public class GetPostTest {
    public static String sendGet(String url, String param){
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.1; SV1)");
            conn.connect();
            Map<String ,List<String>> map = conn.getHeaderFields();
            for (String key : map.keySet()){
                System.out.println(key + "--->"+ map.get(key));
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"))) {
                String line;
                while ((line = in.readLine()) != null){
                    result += "\n" +line;
                }
            }
        } catch (Exception e) {
            System.out.println("发送get请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }

    public static String sendPost(String url, String param){
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.1; SV1)");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(param);
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null){
                result += "\n"+line;
            }
        } catch (Exception e) {
            System.out.println("发送post请求出现异常！"+ e);
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args){
        String s = GetPostTest.sendGet("www.baidu.com",null);
        System.out.println(s);
        String s1 = GetPostTest.sendPost("www.baidu.com",null);
    }
}
