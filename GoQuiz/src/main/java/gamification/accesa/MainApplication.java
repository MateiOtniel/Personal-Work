package gamification.accesa;

import gamification.accesa.controller.LoginController;
import gamification.accesa.service.utils.ServiceUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application{
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LoginController loginController = fxmlLoader.getController();
        loginController.setService(ServiceUtils.getLoginControllerService());
        scene.getStylesheets().add(Objects
                .requireNonNull(getClass().getResource("stylesheet.css")).toExternalForm());
        stage.getIcons().add(new Image(Objects
                .requireNonNull(getClass().getResourceAsStream("images/icon.png"))));
        stage.setTitle("GoQuiz");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }

}