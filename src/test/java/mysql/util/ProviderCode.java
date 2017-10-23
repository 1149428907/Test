package mysql.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.slf4j.LoggerFactory;

import com.test.until.FileUtil;
import com.test.until.StringUtil;

/**
 * @author td
 * @desc beetl 版本代码生成  mysql
 * @time 20161129
 */
public class ProviderCode {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ProviderCode.class);
    private static String root = System.getProperty("user.dir") + File.separator + "src/test/java/mysql/";
    private static String projectName="mht"; 


    /**
     * @author td
     * @desc 获取文件夹下文件
     * @return
     * @throws Exception 
     */
    public static void fileList() throws Exception {
    	
    	Scanner xx = new Scanner(System.in);
        logger.info("请输入文件存放路径：");
        String filePath = xx.nextLine();
        logger.info("请输入文件存放路径：");
        String zipfilePath = xx.nextLine();
        logger.info("输入正确，你输入的路径是：" + filePath+"---生成文件保存路径：{}",zipfilePath);
        if(StringUtil.isEmpty(zipfilePath)||StringUtil.isEmpty(filePath)){
        	logger.info("路径不能为空");
        	return;
        }
        File file = new File(filePath);
        File[] tempList = file.listFiles();
        if(null==tempList){
        	logger.info("请检查路径是否正确，或者路径下没有文件");
        	return;
        }
        FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

        //清空初始化
        FileUtil.createDir(root + File.separator + "file"); // 生成代码前,先清空之前生成的代码

        int fileSize = 0;
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile() && tempList[i].toString().toUpperCase().contains("CSV")) {
                //循环批量产生代码
                String tableName = tempList[i].getName().toLowerCase().replace(".csv", "");
                providerFile(tempList[i].toString(), tableName, gt);
                logger.info("生成表：" + tableName + "成功");
                fileSize++;
            }
        }

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdfd.format(new Date());
        FileZip.zip(root + File.separator + "file", zipfilePath + File.separator +  "fileZip"+dateStr+".zip");
        FileZip.delFolder(root + File.separator + "file"); // 生成代码前,先清空之前生成的代码
        logger.info("生成完毕共生成：{}张表;生成的文件路径为：{}", fileSize,zipfilePath +  "/fileZip"+dateStr+".zip");
    }

    /**
     * @author td
     * @desc 单个文件的产生
     * @throws Exception 
     */
    private static void providerFile(String filePath, String fileName, GroupTemplate gt) throws Exception {
        //FileZip.delAllFile(root + File.separator + "file/"); // 生成代码前,先清空之前生成的代码

        Map < String, Object > param = new HashMap < String, Object > (); // 创建数据模型
        //fileName 的组成结构  表描述666表名称
        String tableDesc = fileName.split("666")[0]; //表描述
        String tableName = fileName.split("666")[1]; //表名称
        String className = CodeUtil.toCamelCaseForClassName(tableName); //类名称
        String createUser = "default"; //创建人
        String prefix = tableName.toLowerCase().split("_")[1]; //前缀
        List < Map < String, Object >> list = CSVUtil.readCsv(filePath);
        int count = list.size();
        String[] cloumnTypes = new String[count]; //列类型
        String[] cloumnNames = new String[count]; //列名称
        String[] cloumnNotes = new String[count]; //列注释
        String[] cloumnSetGets = new String[count]; //列setget集合
        for (int i = 0, j = 1; i < 2; i++) {

        }
        String[] tableColumnTypes = new String[count]; //table 列类型
        String[] tableColumnNames = new String[count]; //table 列名称
        String[] tableColumnNotes = new String[count]; //table 列注释
        String[] tableColumnIsNulls = new String[count]; //是否为空
        String[] tableCloumnDefaultValues = new String[count]; //默认值
        String[] primaryKeys=new String[count];//主键标示

        //循环赋值
        int i = 0;
        for (Map < String, Object > m: list) {
            String Name = CodeUtil.removeStr((String) m.get("Name")); //属性注释
            String Code = CodeUtil.removeStr((String) m.get("Code")); //名称
            String Comment = CodeUtil.removeStr((String) m.get("Name")); //列注释
            String Type = CodeUtil.removeStr((String) m.get("Type")); //类型
            String Length = (String) m.get("Length"); //长度
            String Precision = (String) m.get("Precision"); //精度
            String primaryKey=(String) m.get("primaryKey");
            cloumnTypes[i] = CodeUtil.oralceToClass(Type,Length,Precision);
            tableColumnTypes[i]= CodeUtil.oralceToMysql(Type,Length,Precision);;
            cloumnNames[i] = CodeUtil.toCamelCase(Code, false);
            cloumnNotes[i] = Name;
            cloumnSetGets[i] = CodeUtil.toCamelCase(Code, true);
            tableColumnNames[i] = Code;
            tableColumnNotes[i] = Comment;
            tableColumnIsNulls[i] = Code.equals("id") ? "0" : "1";
            tableCloumnDefaultValues[i] = "";
            primaryKeys[i]=primaryKey;
            i++;
        }
        
        param.put("projectName", projectName);
        
        param.put("tableDesc", tableDesc);
        param.put("tableName", tableName);
        param.put("className", className);
        param.put("createUser", createUser);
        param.put("prefix", prefix);
        param.put("cloumnTypes", cloumnTypes);
        param.put("cloumnNames", cloumnNames);
        param.put("cloumnNotes", cloumnNotes);
        param.put("cloumnSetGets", cloumnSetGets);
        param.put("tableColumnTypes", tableColumnTypes);
        param.put("tableColumnNames", tableColumnNames);
        param.put("tableColumnNotes", tableColumnNotes);
        param.put("tableColumnIsNulls", tableColumnIsNulls);
        param.put("tableCloumnDefaultValues", tableCloumnDefaultValues);
        param.put("primaryKeys", primaryKeys);


        providerFileUtil(gt, "Api", param, className,prefix); //生成api
        providerFileUtil(gt, "Controller", param, className,prefix); //
        providerFileUtil(gt, "Bean", param, className,prefix);
        providerFileUtil(gt, "MybatisMapper", param, className,prefix);
        providerFileUtil(gt, "MysqlSql", param, className,prefix);
        providerFileUtil(gt, "Dao", param, className,prefix);
        providerFileUtil(gt, "IService", param, className,prefix);
        providerFileUtil(gt, "IServiceImpl", param, className,prefix);
        providerFileUtil(gt, "SOAService", param, className,prefix);

    }

    public static void providerFileUtil(GroupTemplate gt, String beetlName, Map < String, Object > map, String className,String prefix) throws IOException {
        String Qtype = "";
        String HType="";
        switch (beetlName) {
            case "Api":
                Qtype = "Service.java";
                break;
            case "Controller":
                Qtype = "Controller.java";
                break;
            case "Bean":
                Qtype = ".java";
                break;
            case "MybatisMapper":
                Qtype = "Mapper.xml";
                break;
            case "MysqlSql":
                Qtype = "Sql.sql";
                break;
            case "Dao":
                Qtype = "Mapper.java";
                break;
            case "IService":
                Qtype = "Service.java";
                HType = "I";
                break;
            case "IServiceImpl":
                Qtype = "ServiceImpl.java";
                HType = "I";
                break;
            case "SOAService":
                Qtype = "SOAService.java";
                break;
            default:
                Qtype = "";
        }
        Template t = gt.getTemplate("beetl/" + beetlName + ".beetl");
        t.binding("param", map);
        FileUtil.createDir(root + File.separator + "file/"+beetlName+"/"+prefix);
        File f = new File(root + File.separator + "file/"+beetlName+"/"+prefix+"/"+HType + className + Qtype);
        OutputStream os = new FileOutputStream(f);
        t.renderTo(os);
        os.close();
    }

}