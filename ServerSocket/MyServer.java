package ServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Stefan on 2016/9/10.
 */
public class MyServer {
    public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(30000);
        while (true){
            Socket s = ss.accept();
            socketList.add(s);
            new Thread(new ServerThread(s)).start();
        }
    }
    public static class ServerThread implements Runnable{
        Socket s = null;
        BufferedReader br = null;
        public ServerThread(Socket s) throws IOException {
            this.s = s;
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        }
        @Override
        public void run() {
            String content = null;
            while((content = readFromClient()) != null){
                for (Socket s : socketList){
                    try {
                        PrintStream ps = new PrintStream(s.getOutputStream());
                        ps.println(content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        private String readFromClient(){
            try {
                return br.readLine();
            } catch (IOException e) {
                MyServer.socketList.remove(s);
                e.printStackTrace();
            }
            return null;
        }
    }

}
