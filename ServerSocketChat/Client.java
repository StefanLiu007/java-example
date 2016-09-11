package ServerSocketChat;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Stefan on 2016/9/11.
 */
public class Client {
    private static final int SERVER_PORT = 30000;
    private Socket socket;
    private PrintStream ps;
    private BufferedReader brServer;
    private BufferedReader keyIn;
    public void init(){
        keyIn = new BufferedReader(new InputStreamReader(System.in));
        try {
            socket = new Socket("127.0.0.1",SERVER_PORT);
            ps = new PrintStream(socket.getOutputStream());
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String tip = "";
            while (true){
                String userName = JOptionPane.showInputDialog(tip + "输入用户名");
                ps.println(CrazyitProtocol.USER_ROUND + userName + CrazyitProtocol.USER_ROUND);
                String result = brServer.readLine();
                if (result.equals(CrazyitProtocol.NAME_REP)){
                    tip = "用户名重复，请重新";
                    continue;
                }
                if (result.equals(CrazyitProtocol.LOGIN_SUCCESS)){
                    break;
                }
            }
        } catch (IOException e) {
            System.out.print("网络异常！请重新登陆");
            closeRs();
            System.exit(1);
            e.printStackTrace();
        }
        new ClientThread(brServer).start();
    }
    public void readAndSend(){
        String line = null;
        try {
            while ((line = keyIn.readLine()) != null);
            if (line.indexOf(":")>0 && line.startsWith("//")){
                line = line.substring(2);
                ps.println(CrazyitProtocol.PRIVATE_ROUND+line.split(":")[0] +CrazyitProtocol.PRIVATE_ROUND);
            }else {
                ps.println(CrazyitProtocol.MEG_ROUND+ line+CrazyitProtocol.MEG_ROUND);
            }
        } catch (IOException e) {
            closeRs();
            System.exit(1);
            e.printStackTrace();
        }
    }
    private void closeRs(){
        if (keyIn != null){
            ps.close();
        }
        if (brServer != null){
            ps.close();
        }
        if (ps != null){
            ps.close();
        }
        if (socket != null){
            try {
                keyIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        Client client = new Client();
        client.init();
        client.readAndSend();
    }
    public class ClientThread extends Thread{
        BufferedReader br = null;
        public ClientThread(BufferedReader br){
            this.br = br;
        }
        @Override
        public void run() {
           String line = null;
            try {
                while((line = br.readLine()) != null){
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (br != null){
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
