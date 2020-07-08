package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesFile {
    static HashMap<String,String> propertiesMap = new HashMap<>();

    public static HashMap<String, String> readFile(String filePath){
        File file = new File(filePath);
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        assert fileInput != null;
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration keys = prop.keys();
        while (keys.hasMoreElements()){
            String key = (String)keys.nextElement();
            String value = prop.getProperty(key);
            propertiesMap.put(key,value);
        }
        return propertiesMap;
    }
}
