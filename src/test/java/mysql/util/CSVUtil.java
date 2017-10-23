package mysql.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVUtil {

    private static final String[] FILE_HEADER = {
        "Name",
        "Code",
        "Comment",
        "Data Type",
        "Length",
        "Precision",
        "P"
    };
    public static List < Map < String, Object >> readCsv(String filePath) throws Exception {
        BufferedReader bufferdReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
        CSVParser csvFileParser = null;
        List < CSVRecord > csvRecords = null;
        List < Map < String, Object >> list = new ArrayList < > ();
        //创建CSVFormat（header mapping）
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        try {
            //初始化 CSVParser object
            csvFileParser = new CSVParser(bufferdReader, csvFileFormat);
            //CSV文件records
            csvRecords = csvFileParser.getRecords();
            // CSV
            // 
            for (int i = 1; i < csvRecords.size(); i++) {
                Map < String, Object > m = new HashMap < > ();
                CSVRecord record = csvRecords.get(i);
                m.put("Name", record.get("Name"));  //属性注释
                m.put("Code", record.get("Code"));  //属性名称
                m.put("Comment", record.get("Comment")); //列注释
                m.put("Type", record.get("Data Type")); 
                m.put("Length", record.get("Length"));
                m.put("Precision", record.get("Precision"));
                m.put("primaryKey", record.get("P"));
                list.add(m);
            }

            // 遍历打印
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferdReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}