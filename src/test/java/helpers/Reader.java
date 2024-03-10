package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Reader {
    private static final String Properties_PATH="src/test/resources/resourses.properties";

    public static  String getProperty(String key) {
        Properties props = new Properties();
        try(FileInputStream fis = new FileInputStream(Properties_PATH)){
            props.load(fis);
            return props.getProperty(key);

        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }

    }
}
