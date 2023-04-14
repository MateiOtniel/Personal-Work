package gamification.accesa.controller;

import gamification.accesa.MainApplication;
import gamification.accesa.controller.exception.InputException;
import gamification.accesa.domain.Player;
import gamification.accesa.service.IService;
import gamification.accesa.service.LoginControllerService;
import gamification.accesa.service.utils.ServiceUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController{
    private LoginControllerService loginControllerService;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button button;

    @FXML
    private Label errorLabel;

    @FXML
    private void initialize(){
        passwordField.clear();
        usernameField.clear();
        usernameField.setOnKeyPressed(event -> {
            if(Objects.requireNonNull(event.getCode()) == KeyCode.ENTER){
                passwordField.requestFocus();
            }
        });
        passwordField.setOnKeyPressed(event -> {
            if(Objects.requireNonNull(event.getCode()) == KeyCode.ENTER){
                loginAction();
            }
        });
        usernameField.setFocusTraversable(false);
        passwordField.setFocusTraversable(false);
        button.setFocusTraversable(false);
        errorLabel.setVisible(false);
    }

    public void setService(IService loginControllerService){
        this.loginControllerService = (LoginControllerService) loginControllerService;
        System.out.println("LoginControllerService set");
    }

    @FXML
    void loginAction(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        Player player = loginControllerService.getPlayerByUsernameAndPassword(username, password);
        try{
            if(player == null) throw new InputException("Invalid username or password");
            switchToMainController(player);
            refreshInput();
        } catch(InputException e){
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
            passwordField.setOnKeyPressed(event -> {
                if(Objects.requireNonNull(event.getCode()) == KeyCode.ENTER){
                    loginAction();
                }
            });
        }
    }

    private void refreshInput(){
        usernameField.clear();
        passwordField.clear();
        errorLabel.setVisible(false);
    }

    private void switchToMainController(Player player){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(Objects.requireNonNull(Objects
                    .requireNonNull(MainApplication
                            .class.getResource("stylesheet.css")).toExternalForm()));
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            Stage newStage = new Stage();
            MainController controller = fxmlLoader.getController();
            controller.setPlayer(player);
            controller.setService(ServiceUtils.getMainControllerService());
            controller.setLoginStage(currentStage);
            currentStage.close();
            newStage.getIcons().add(new Image(Objects
                    .requireNonNull(MainApplication.class.getResourceAsStream("images/icon.png"))));
            newStage.setScene(scene);
            newStage.setTitle("GoQuiz");
            newStage.show();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}