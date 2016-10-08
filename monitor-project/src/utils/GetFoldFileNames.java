package utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GetFoldFileNames {
    public static String[]  getFileName(String s) throws Exception {
    	List<String> list = new ArrayList<String>();    	
    	String filepath[] = ParseXML.ReadXML(); //解析配置文件，确定读取索要读取文件夹的路径
        String path = filepath[0];
        File f = new File(path + s);
        //List<String> list = new ArrayList<String>();
        if (!f.exists()) {
            return null;
        }        
        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                continue;
            } else {
                list.add(fs.getName().toUpperCase());
            }
        }
        final int size =list.size();
        String[] FileNames = list.toArray(new String[size]);
    	return FileNames;
    }
    public static void main(String[] args) {
        try {
        	Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
    		int year = c.get(Calendar.YEAR);
    		int month = c.get(Calendar.MONTH)+1;
    		int date = c.get(Calendar.DATE);
    		int daynum = CountDay.countDay(year, month, date);
    		String s = "IGS/daily/" + year + "/" + 207;	
			String[] arr = getFileName(s);
			for(int i=0;i<arr.length;i++){
				System.out.println(arr[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}