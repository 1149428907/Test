package mysql.util;

import com.test.until.StringUtil;

public class CodeUtil {

    //驼峰式命名,不要第一个
    public static String toCamelCaseForClassName(String str) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        String[] str_arr = str.split("_");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str_arr.length; i++) {
            if (i != 0) {
                sb.append(captureName(str_arr[i]));
            }
        }
        return sb.toString();
    }
	   /**
     * PlCodeGeneration.removeStr()<BR>
     * <P>Author :  张天德 </P>  
     * <P>Date : 2016年11月29日上午10:08:39</P>
     * <P>Desc : 所有字段转换为小写 </P>
     * @param str
     * @return
     */
    public static String removeStr(String str) {
        return str.toLowerCase().trim();
    }

    /**
     * PlCodeGeneration.toCamelCase()<BR>
     * <P>Author :  张天德 </P>  
     * <P>Date : 2016年11月29日上午10:07:28</P>
     * <P>Desc : 驼峰命名 </P>
     * @param str_
     * @param isFirstToUpper
     * @return
     */
    public static String toCamelCase(String str_, boolean isFirstToUpper) {
        String[] str_arr = str_.split("_");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str_arr.length; i++) {
            if (i == 0) {
                if (isFirstToUpper) {
                    sb.append(captureName(str_arr[0]));
                } else {
                    sb.append(str_arr[0]);
                }
            } else {
                sb.append(captureName(str_arr[i]));
            }
        }
        return sb.toString();
    }

    /**
     * PlCodeGeneration.captureName()<BR>
     * <P>Author :  张天德 </P>  
     * <P>Date : 2016年11月29日上午10:08:12</P>
     * <P>Desc : 首字母大写 </P>
     * @param name
     * @return
     */
    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);

    }
    
    public static String oralceToMysql(String type,String Length,String Precision){
    	String reStr="";
    	int len=0;
    	if(!StringUtil.isEmpty(Length)){
    		Length=Length.replaceAll(",", "");
    		len=Integer.parseInt(Length);
    	}
    	
    	type=type.toUpperCase().split("\\(")[0];
    	switch(type){
    	case "NUMBER":
    		if(StringUtil.isEmpty(Precision)){
    			if(len>10){
    				reStr="bigint";
    			}else{
    				reStr="int";
    			}
    		}else{
    		   reStr="double";
    		}
    		
    		;break;
    	case "VARCHAR":
    	case "VARCHAR2":
    		if(len>512){
    			reStr="text";
    		}else{
    			reStr="varchar("+len+")";
    		}
    		;break;
    	case "DATE":reStr="datetime";break;
    	default:
    		reStr="";
    		;
    	
    	}
    	return reStr;
    }
    
    public static String oralceToClass(String Type,String Length,String Precision){
    	String reStr="";
    	int len=0;
    	if(!StringUtil.isEmpty(Length)){
    		Length = Length.replaceAll(",", "");
    		len=Integer.parseInt(Length);
    	}
    	
    	Type=Type.toUpperCase().split("\\(")[0];
    	switch(Type){
    	case "NUMBER":
    		if(StringUtil.isEmpty(Precision)){
    			if(len>10){
    				reStr="long";
    			}else{
    				reStr="int";
    			}
    		}else{
    		   reStr="double";
    		}
    		
    		;break;
    	case "VARCHAR":
    	case "VARCHAR2":
    		reStr="String";
    		;break;
    	case "DATE":reStr="Timestamp";break;
    	default:
    		reStr="";
    		;
    	
    	}
    	return reStr;
    }
}
