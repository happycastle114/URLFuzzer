package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Starter {
    @FXML
    public TextField url;

    public void start(ActionEvent event) throws Exception{
        if(url.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No URL");
            alert.setHeaderText("NO URL");
            alert.showAndWait();
        } else{
            StaticValue.SetURL(url.getText());
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("list.fxml")));
            Stage stage = new Stage();
            stage.setTitle("START");
            stage.setScene(scene);
            stage.show();
        }
    }

}
