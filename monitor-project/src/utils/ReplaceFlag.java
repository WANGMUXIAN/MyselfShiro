package utils;

public class ReplaceFlag {

	public static String replace(String sp,String sp1,String sp2){
		String  sTemp  =  sp;
		String  str  ="";
		if(sp==null||sp.equals("")){
			return  str;
			}
		try
		{
			int iIndex=sTemp.indexOf(sp1);
			int iStar  =  0;
			while(iIndex!=-1)
			{
				String  s1=  sTemp.substring(iStar,iIndex);
				if(iIndex<sTemp.length())
				{
					sTemp=sTemp.substring(iIndex+1,sTemp.length());
					s1=s1+sp2;
					str=str+s1;
					iIndex=sTemp.indexOf(sp1);
					}
				}
			str=str+sTemp;
			}catch(Exception  e)
		{  
				
		}
		return  str;
		}
}
