package ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Stefan on 2016/9/10.
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1",30000);
        new Thread(new ClientThread(s)).start();
        PrintStream ps = new PrintStream(s.getOutputStream());
        String line = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while ((line = br.readLine()) != null){
            ps.println(line);
        }
    }
    private static class ClientThread implements Runnable{
        private Socket s;
        BufferedReader br = null;
        public ClientThread(Socket s) throws IOException {
            this.s = s;
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        }
        @Override
        public void run() {
            String content = null;
            try {
                while ((content = br.readLine() )!= null){
                    System.out.println(content);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
