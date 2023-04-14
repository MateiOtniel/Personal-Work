package gamification.accesa.controller;

import gamification.accesa.MainApplication;
import gamification.accesa.controller.exception.InputException;
import gamification.accesa.controller.validator.NumberValidator;
import gamification.accesa.domain.Player;
import gamification.accesa.domain.Quiz;
import gamification.accesa.service.IService;
import gamification.accesa.service.MainControllerService;
import gamification.accesa.service.utils.ServiceUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController{

    private MainControllerService mainControllerService;

    private Player player;

    private Stage loginStage;

    @FXML
    private Label rankLabel;

    @FXML
    private Label tokensLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private ListView<Button> quizList;

    @FXML
    private TextField titleTextField;


    private final ObservableList<Button> quizModelGrade
            = FXCollections.observableArrayList();

    @FXML
    private TextField answerTextField;

    @FXML
    private TextArea descriptionTextArea;


    @FXML
    private Pane quizListPane;

    @FXML
    private Pane quizPane;

    @FXML
    private TextField tokensTextField;

    @FXML
    private Label errorLabel;


    @FXML
    private void initialize(){
        quizList.setItems(quizModelGrade);
        quizPane.setVisible(false);
        titleTextField.setFocusTraversable(false);
        descriptionTextArea.setFocusTraversable(false);
        answerTextField.setFocusTraversable(false);
        tokensTextField.setFocusTraversable(false);
        titleTextField.setOnKeyPressed(event -> {
            if(Objects.requireNonNull(event.getCode()) == KeyCode.ENTER){
                descriptionTextArea.requestFocus();
            }
        });
        descriptionTextArea.setOnKeyPressed(event -> {
            if(Objects.requireNonNull(event.getCode()) == KeyCode.ENTER){
                answerTextField.requestFocus();
            }
        });
        answerTextField.setOnKeyPressed(event -> {
            if(Objects.requireNonNull(event.getCode()) == KeyCode.ENTER){
                tokensTextField.requestFocus();
            }
        });
        tokensTextField.setOnKeyPressed(event -> {
            if(Objects.requireNonNull(event.getCode()) == KeyCode.ENTER){
                submitQuiz();
            }
        });
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void refreshStats(){
        usernameLabel.setText("Username: " + player.getUsername());
        rankLabel.setText("Rank: " +
                mainControllerService.getRank(player));
        tokensLabel.setText("Tokens: " +
                player.getTokens());
    }

    private void refreshQuizForm(){
        titleTextField.clear();
        descriptionTextArea.clear();
        answerTextField.clear();
        tokensTextField.clear();
    }

    public void setService(IService mainControllerService){
        this.mainControllerService = (MainControllerService) mainControllerService;
        refreshStats();
        refreshQuizzes();
    }

    public void setLoginStage(Stage currentStage){
        this.loginStage = currentStage;
    }

    @FXML
    private void logout(){
        Stage currentStage = (Stage) quizList.getScene().getWindow();
        currentStage.close();
        loginStage.show();
    }

    public void refreshQuizzes(){
        quizModelGrade.clear();
        Iterable<Quiz> quizzes = mainControllerService.getAllQuizzes(player.getId());
        //iau toate quizurile din baza de date
        for(Quiz quiz : quizzes){
            //redenumesc butoanele
            errorLabel.setVisible(false);
            Button button = new Button(quiz.getTitle());
            button.setPrefSize(370, 70);
            button.addEventHandler(ActionEvent.ACTION, event ->
                    switchToQuizController(quiz));
            quizModelGrade.add(button);
        }
    }

    private void switchToQuizController(Quiz quiz){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("quiz.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(Objects.requireNonNull(
                    Objects.requireNonNull(MainApplication
                            .class.getResource("stylesheet.css")).toExternalForm()));
            Stage currentStage = (Stage) quizList.getScene().getWindow();
            Stage newStage = new Stage();
            QuizController controller = fxmlLoader.getController();
            controller.setQuiz(quiz);
            controller.setService(ServiceUtils.getQuizControllerService());
            controller.setMainControllerStage(currentStage);
            controller.setPlayer(player);
            controller.setMainController(this);
            currentStage.close();
            newStage.getIcons().add(new Image(Objects.requireNonNull(
                    MainApplication.class.getResourceAsStream("images/icon.png"))));
            newStage.setScene(scene);
            newStage.setTitle("GoQuiz");
            newStage.show();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }


    @FXML
    void switchToBadgeController(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("badge.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(Objects.requireNonNull(
                    Objects.requireNonNull(MainApplication
                            .class.getResource("stylesheet.css")).toExternalForm()));
            Stage currentStage = (Stage) quizList.getScene().getWindow();
            Stage newStage = new Stage();
            BadgeController controller = fxmlLoader.getController();
            controller.setPlayer(player);
            controller.setService(ServiceUtils.getBadgeControllerService());
            controller.setMainControllerStage(currentStage);
            currentStage.close();
            newStage.getIcons().add(new Image(Objects.requireNonNull(MainApplication
                    .class.getResourceAsStream("images/icon.png"))));
            newStage.setScene(scene);
            newStage.setTitle("GoQuiz");
            newStage.show();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showQuizListPane(){
        errorLabel.setVisible(false);
        quizListPane.setVisible(true);
        quizPane.setVisible(false);
    }

    @FXML
    void showQuizPane(){
        errorLabel.setVisible(false);
        if(player.getTokens() < 500){
            errorLabel.setText("Minimum 500 tokens required to add quest!");
            errorLabel.setVisible(true);
            return;
        }
        quizListPane.setVisible(false);
        quizPane.setVisible(true);
        refreshQuizForm();
    }

    @FXML
    void submitQuiz(){
        try{
            if(titleTextField.getText().isEmpty()
                    || descriptionTextArea.getText().isEmpty()
                    || answerTextField.getText().isEmpty()
                    || tokensTextField.getText().isEmpty()){
                throw new InputException("All fields must be filled!");
            }
            NumberValidator.validate(tokensTextField.getText());
            mainControllerService.addQuiz(player.getId(),
                    Integer.parseInt(tokensTextField.getText()), titleTextField.getText(),
                    descriptionTextArea.getText(), answerTextField.getText());
            mainControllerService.updatePlayerTokens(player, -500);
            refreshQuizzes();
            refreshQuizForm();
            refreshStats();
            showQuizListPane();
        } catch(InputException e){
            errorLabel.setText(e.getMessage());
            errorLabel.setVisible(true);
        }
    }

}