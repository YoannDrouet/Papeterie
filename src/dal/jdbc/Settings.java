package dal.jdbc;

import java.util.Properties;

public class Settings {
    private static Properties propritete;

    static {
        try{
            propritete = new Properties();
            propritete.load(Settings.class.getResourceAsStream("settings.properties"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String getPropriete(String cle){
        String parametre = propritete.getProperty(cle,null);
        return parametre;
    }
}
