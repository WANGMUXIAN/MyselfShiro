package test;

	import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

	public class ClientTcpReceive {

	    public static void main(String[] args) {
	        int length = 0;
	        byte[] inputByte = null;
	        Socket socket = null;
	        DataInputStream din = null;
	        FileOutputStream fout = null;
	        try {
	          try {
	            socket = new Socket();
	            socket.connect(new InetSocketAddress("127.0.0.1", 33456),10 * 1000);
	            din = new DataInputStream(socket.getInputStream());
	            fout = new FileOutputStream(new File("C:/Users/lyf/Desktop/1/"+din.readUTF()));
	            inputByte = new byte[1024];
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
	            } catch (Exception e) {

	            } finally{
	                if (din != null)
	                    din.close();
	                if (fout != null)
	                    fout.close();
	                if (socket != null)
	                    socket.close();
	        }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
