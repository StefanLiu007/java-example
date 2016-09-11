import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Stefan on 2016/9/10.
 */
public class DownUtil {
    private String path;

    private String targetFile;

    private int threadNum;

    private int fileSizes;

    private DownThread[] threads;
    public DownUtil(String path,String targetFile,int threadNum){
        this.path = path;
        this.threadNum = threadNum;
        this.targetFile = targetFile;
        threads = new DownThread[threadNum];

    }
    public void download() throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5*1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept","image/gif,image/jpeg,image/pjpeg,image/pjpeg,"
        +"application/x-shockwave-flash,application/xaml+xml,"
        +"application/vnd.ms-xpsdocument,application/x-ms-xbap,"
        +"application/x-ms-application, application/vnd.ms-excel,"
        +"application/vnd.ms-powerpoint, application/msword, */*");
        conn.setRequestProperty("Accept-Language","zh-CN");
        conn.setRequestProperty("Charset","UTF-8");
        conn.setRequestProperty("Connection","Keep-Alive");

        fileSizes = conn.getContentLength();
        conn.disconnect();
        int currentPartSize = fileSizes/threadNum +1;
        RandomAccessFile file = new RandomAccessFile(targetFile,"rw");
        file.setLength(fileSizes);
        file.close();
        for (int i = 0;i<threadNum;i++){
            int startPos = i* currentPartSize;
            RandomAccessFile currentPart = new RandomAccessFile(targetFile,"rw");
            currentPart.seek(startPos);
            threads[i] = new DownThread(startPos,currentPartSize,currentPart);
            threads[i].start();

        }
    }
    public double getCompleteRate(){
        int sumSize = 0;
        for (int i = 0;i< threadNum;i++){
            sumSize += threads[i].length;
        }
        return sumSize/fileSizes;
    }

    private class DownThread extends Thread{
        private int startPos;

        private int currentPartSize;

        private RandomAccessFile currentPart;

        public int length;
        public DownThread(int startPos,int currentPartSize,RandomAccessFile currentPart){
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }
        @Override
        public void run(){
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5*1000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept","image/gif,image/jpeg,image/pjpeg,image/pjpeg,"
                        +"application/x-shockwave-flash,application/xaml+xml,"
                        +"application/vnd.ms-xpsdocument,application/x-ms-xbap,"
                        +"application/x-ms-application, application/vnd.ms-excel,"
                        +"application/vnd.ms-powerpoint, application/msword, */*");
                connection.setRequestProperty("Accept-Language","zh-CN");
                connection.setRequestProperty("Charset","UTF-8");
                InputStream inputStream = connection.getInputStream();
                inputStream.skip(this.startPos);
                byte[] buffer = new byte[1024];
                int hasRead = 0;
                while (length < currentPartSize && (hasRead = inputStream.read(buffer)) != -1){
                    currentPart.write(buffer,0,hasRead);
                    length += hasRead;
                }
                currentPart.close();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        final DownUtil downUtil = new DownUtil("http://www.crazyit.org/"+"attachments/month_1403/1403202355ff6cc9a4fbf6f14a.png","ios.png",4);
        downUtil.download();
        new Thread(()->{
            while (downUtil.getCompleteRate() <1){
                System.out.println("已完成：" +downUtil.getCompleteRate());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
