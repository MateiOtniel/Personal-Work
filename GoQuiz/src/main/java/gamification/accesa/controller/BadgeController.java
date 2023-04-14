package gamification.accesa.controller;

import gamification.accesa.domain.Player;
import gamification.accesa.service.BadgeControllerService;
import gamification.accesa.service.IService;
import gamification.accesa.utils.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BadgeController{

    @FXML
    private ImageView bronzeImage;

    @FXML
    private ImageView creatorImage;

    @FXML
    private ImageView goldImage;

    @FXML
    private ImageView intellectualImage;

    @FXML
    private ImageView royalImage;

    @FXML
    private ImageView silverImage;

    private Stage mainControllerStage;

    private BadgeControllerService badgeControllerService;
    private Player player;

    @FXML
    private void initialize(){
        bronzeImage.setDisable(true);
        bronzeImage.setOpacity(0.5);
        silverImage.setDisable(true);
        silverImage.setOpacity(0.5);
        goldImage.setDisable(true);
        goldImage.setOpacity(0.5);
        creatorImage.setDisable(true);
        creatorImage.setOpacity(0.5);
        intellectualImage.setDisable(true);
        intellectualImage.setOpacity(0.5);
        royalImage.setDisable(true);
        royalImage.setOpacity(0.5);
    }
    public void setService(IService service){
        badgeControllerService = (BadgeControllerService) service;
        setBadges();
    }

    private void setBadges(){
        ArrayList<String> badges = badgeControllerService.getBadges(player);
        System.out.println(badges);
        for(String badge : badges){
            switch(badge){
                case "bronze":
                    enableBadge(bronzeImage, Constants.BRONZE_BADGE_INFO);
                    break;
                case "silver":
                    enableBadge(silverImage, Constants.SILVER_BADGE_INFO);
                    break;
                case "gold":
                    enableBadge(goldImage, Constants.GOLD_BADGE_INFO);
                    break;
                case "creator":
                    enableBadge(creatorImage, Constants.CREATOR_BADGE_INFO);
                    break;
                case "intellectual":
                    enableBadge(intellectualImage, Constants.INTELLECTUAL_BADGE_INFO);
                    break;
                case "royal":
                    enableBadge(royalImage, Constants.ROYAL_BADGE_INFO);
                    break;
            }
        }
    }

    private void enableBadge(ImageView imageView, String badgeInfo){
        imageView.setDisable(false);
        imageView.setOpacity(1);
        Tooltip tooltip = new Tooltip(badgeInfo);
        Tooltip.install(imageView, tooltip);
    }

    public void setMainControllerStage(Stage currentStage){
        this.mainControllerStage = currentStage;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    @FXML
    private void switchToMainController(){
        Stage currentStage = (Stage) bronzeImage.getScene().getWindow();
        currentStage.close();
        mainControllerStage.show();
    }
}
