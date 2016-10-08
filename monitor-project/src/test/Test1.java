package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test1 {
	
	//filaname为远程文件地址，localfilename为本地保存地址
	public static void readRomateFile(String romatefilename,String localfilename){
		URL urlfile;
		BufferedReader in;
		PrintWriter out;
		StringBuffer sb = new StringBuffer();;
		String inputLine;
		try {
			urlfile = new URL(romatefilename);
			HttpURLConnection urlcon = (HttpURLConnection) urlfile.openConnection();
			urlcon.setConnectTimeout(5000);
			urlcon.setReadTimeout(5000);
			urlcon.connect();
			in = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
			
			while((inputLine=in.readLine())!=null){
				sb.append(inputLine+"\r\n");      //\r\n为记事本中换行符
			}
			sb.setLength(sb.length()-2);   //因为多加了一个换行符，所以截掉
			System.out.println(sb.toString());
			in.close();
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(localfilename)));
			out.print(sb.toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		Test1.readRomateFile("http://192.168.7.10:5/public/home/test1/Downloads/1.txt", "C:/Users/lyf/Desktop/2.txt");
	}
}
