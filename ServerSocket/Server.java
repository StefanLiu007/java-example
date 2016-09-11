package ServerSocket;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Stefan on 2016/9/10.
 */
public class Server {
    public  static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(30000);
        while (true){
            Socket s = ss.accept();
            PrintStream ps = new PrintStream(s.getOutputStream());
            ps.println("你好，你好啊");
            ps.close();
            s.close();
        }
    }
}
