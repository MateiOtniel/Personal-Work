package gamification.accesa.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils{
    public static Properties getProperties() throws FileNotFoundException{
        Properties properties = new Properties();
        try{
            properties.load(new FileReader("bd.config"));
            System.out.println("Properties loaded");
        } catch(FileNotFoundException e){
            throw new FileNotFoundException("bd.config file not found");
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        return properties;
    }
}
