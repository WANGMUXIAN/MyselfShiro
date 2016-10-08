package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadOtherFile {

	public static void raedFile(String sourceFilePath,String encode){
		File file = new File(sourceFilePath);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			StringBuilder strBuilder = new StringBuilder();
			String sLine = null;
			try {
				while((sLine=br.readLine())!=null){
					strBuilder.append(sLine);
					strBuilder.append("\r\n");
				}
				br.close();
				System.out.println(strBuilder.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static String[] getFileNames(String path){
		File dirFile =new File(path);
		if(dirFile.isDirectory()){
			File[] files = dirFile.listFiles();
			String[] fileNames = new String[files.length];
			for(int i=0;i<files.length;i++){
				fileNames[i] = files[i].getAbsolutePath();
			}
			return fileNames;
		}
		else{
			return null;
		}
	}
	public static void main(String[] args){
		//ReadOtherFile.raedFile("////192.168.7.10//public//home//test1//public//1.txt", "utf-8");
		ReadOtherFile.raedFile("//127.0.0.1/C:/Users/lyf/Desktop/1.txt", "utf-8");
	}
}
