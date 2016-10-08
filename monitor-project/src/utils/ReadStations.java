package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
//根据站点列表所在的文件命，读出站点列表
public class ReadStations {
	public static String getPath(String fileName){
		String filePath = ReadStations.class.getClassLoader().getResource(fileName).getPath();		
		return filePath;
	}
	public static String[] readFile(String filePath){
    	LinkedList<String> list = new LinkedList<String>();
        try {
                File file=new File(filePath);
                if(file.isFile() && file.exists()){         //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file));    
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;  
                    while((lineTxt = bufferedReader.readLine()) != null){
                        list.add(lineTxt.substring(0, 4));                      
                    }
                    read.close();
        }else{
            String message = "找不到指定文件";
            list.add(message);
        }
        } catch (Exception e) {
        	String message = "读取文件内容出错";
            list.add(message);
        }
        final int size =list.size();
        String[] Stations = (String[]) list.toArray(new String[size]);
        return Stations;
    }
	
	/*public static void main(String[] args){
		try {
			String filepath = getPath("IGS_FNL.CLU");
			String[] arr = readFile(filepath);
			for(int i=0;i<arr.length;i++){
				System.out.println(arr[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}

