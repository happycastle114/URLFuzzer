package sample;

public class StaticValue {
    public static String url = new String();

    public static String valueURL(){
        return url;
    }
    public static void SetURL(String url1){
        url = url1;
    }
}
