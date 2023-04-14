package gamification.accesa.controller;

import gamification.accesa.domain.Player;
import gamification.accesa.domain.Quiz;
import gamification.accesa.service.IService;
import gamification.accesa.service.QuizControllerService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class QuizController{
    private QuizControllerService quizControllerService;

    private Quiz quiz;

    private Player player;

    private Stage mainControllerStage;

    private MainController mainController;

    @FXML
    private TextField answerTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label titleLabel;

    @FXML
    private Label errorLabel;

    private void initialize(){
        titleLabel.setText(quiz.getTitle());
        descriptionTextArea.setText(quiz.getDescription());
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setWrapText(true);
        errorLabel.setText("Wrong answer!");
        errorLabel.setVisible(false);
        answerTextField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                submitAnswer();
            }
        });
    }

    public void setQuiz(Quiz quiz){
        this.quiz = quiz;
        initialize();
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setService(IService quizControllerService){
        this.quizControllerService = (QuizControllerService) quizControllerService;
    }

    public void setMainControllerStage(Stage stage){
        this.mainControllerStage = stage;
    }

    @FXML
    void submitAnswer(){
        String answer = answerTextField.getText();
        if(quizControllerService.checkAnswer(quiz, answer)){
            quizControllerService.updatePlayer(player, quiz.getTokens());
            quizControllerService.addSolvedQuiz(player.getId(), quiz.getId());
            mainController.refreshQuizzes();
            mainController.refreshStats();
            switchToMainController();
        } else{
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void switchToMainController(){
        Stage currentStage = (Stage) answerTextField.getScene().getWindow();
        currentStage.close();
        mainControllerStage.show();
    }

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
}
