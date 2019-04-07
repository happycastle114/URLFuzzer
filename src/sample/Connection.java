package sample;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

public class Connection extends Thread{
    public String filename;
    public Vector<String> exten;
    public String ip;
    public int port;
    Connection(String filename, Vector<String> exten, String crazy){
        this.exten = exten;
        this.filename = filename;
        String[] happycastle = crazy.split(":");
        ip = happycastle[0];
        port = Integer.valueOf(happycastle[1]);
    }
    @Override
    public void run(){
        try{
            System.out.println("STARTED");
            String line = "";
            File file = new File(filename + ".txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufReader;
            bufReader = new BufferedReader(fileReader);
            int hey = 0;
            HttpHost httpHost = new HttpHost(ip,port);
            HttpClientBuilder clientBuilder = HttpClientBuilder.create();
            clientBuilder.setProxy(httpHost);
            HttpClient client = clientBuilder.build();
            //HttpClient client = new DefaultHttpClient();

            while((line = bufReader.readLine()) != null){
                HttpGet httpGet = new HttpGet(StaticValue.valueURL() + line);
                HttpResponse response = client.execute(httpGet);
                int code = response.getStatusLine().getStatusCode();
                hey++;
                if(code!=404){
                    System.out.println(line  + "\n");
                }
                for(int k = 0; k < exten.size();k++){
                    client = new DefaultHttpClient();
                    httpGet = new HttpGet(StaticValue.valueURL() + line + exten.get(k));
                    response = client.execute(httpGet);
                    code = response.getStatusLine().getStatusCode();
                    hey++;
                    if(code!=404){
                        System.out.println(line + exten.get(k) + "\n");
                        //ListofFile.appendText(line + exten.get(k) + "\n");
                    }
                }
                System.out.println(hey + " - " + filename);


            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
