package cn.org.dialogue.netfilter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class MainApplication extends Application {
    public static Logger log = LoggerFactory.getLogger(MainApplication.class);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("NetFlow Capture");
        stage.setScene(scene);
        stage.show();
        log.info("启动成功");
    }

    public static void main(String[] args) {
        launch();
    }
}