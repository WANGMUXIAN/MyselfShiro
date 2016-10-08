package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTcpListener implements Runnable {

    @Override
    public void run() {

    }
    public static void main(String[] args) {
        try {
            final ServerSocket server = new ServerSocket(33456);
            //final ServerSocket server2 = new ServerSocket(33455);
            Thread th = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        try {
                           System.out.println("开始监听。。。");
                           Socket socket = server.accept();
                           System.out.println("有链接");
                           sendFile(socket);
                           
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }

            });
            th.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void receiveFile(Socket socket) throws IOException {
        byte[] inputByte = null;
        int length = 0;
        DataInputStream din = null;
        FileOutputStream fout = null;
        try {
            din = new DataInputStream(socket.getInputStream());
            
            fout = new FileOutputStream(new File("C:/Users/lyf/Desktop/1/"+din.readUTF()));
            inputByte = new byte[1024];
            System.out.println("开始接收数据...");
            while (true) {
                if (din != null) {
                    length = din.read(inputByte, 0, inputByte.length);
                }
                if (length == -1) {
                    break;
                }
                System.out.println(length);
                fout.write(inputByte, 0, length);
                fout.flush();
            }
            System.out.println("完成接收");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fout != null)
                fout.close();
            if (din != null)
                din.close();
            if (socket != null)
                socket.close();
        }
    }
    public static void sendFile(Socket socket) throws IOException {
        byte[] sendByte = null;
        int length = 0;
        DataOutputStream dout = null;
        FileInputStream fin = null;
        try {
        	dout = new DataOutputStream(socket.getOutputStream());            
        	File file = new File("C:/Users/lyf/Desktop/1.txt");
            fin = new FileInputStream(file);
            sendByte = new byte[1024];
            dout.writeUTF(file.getName());
            System.out.println("开始发送数据...");
            while((length = fin.read(sendByte, 0, sendByte.length))>0){
                dout.write(sendByte,0,length);
                dout.flush();
            }
            System.out.println("完成接收");
        }catch (Exception e) {
        	e.printStackTrace();
        } finally{
            if (dout != null)
                dout.close();
            if (fin != null)
                fin.close();
            if (socket != null)
                socket.close();
        }
    }
}
