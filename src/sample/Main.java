package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(System.getProperty("user.dir")+"\\cardAnimation\\" + 1 + ".gif");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ATM Simulator");
        primaryStage.setScene(new Scene(root, 450, 500));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
