package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.stage.WindowEvent;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

public class Finder {
    @FXML
    public TextArea ListofFile;

    @FXML
    public ProgressBar toggleBar;

    public void Findis(ActionEvent event) throws Exception{
        Vector<String> exten = new Vector<>();
        Vector<String> proxys = new Vector<>();
        //File file = new File("words.txt");
        File file2 = new File("Extension.txt");
        //FileReader fileReader = new FileReader(file);
        FileReader fileReader1 = new FileReader(file2);
        BufferedReader bufReader = new BufferedReader(fileReader1);
        String line = "";
        while((line = bufReader.readLine()) != null){
            exten.add(line);
        }
        file2 = new File("proxylist.txt");
        fileReader1 = new FileReader(file2);
        bufReader = new BufferedReader(fileReader1);
        line = "";
        while((line = bufReader.readLine()) != null){
            proxys.add(line);
        }
        int count = 466547 * 73;
        int hey = 0;
        for(int i = 0; i < 1000 ; i++){
            Thread t =  new Thread(new Connection("outis/part" + (i-1) ,exten,proxys.get(i)));
            t.start();
        }
        System.out.println("FINISHED!");
    }
}
