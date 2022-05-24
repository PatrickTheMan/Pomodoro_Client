package UI.Structures.MenuBar.Parts;

import UI.Buttons.CustomButton;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class CustomMenuBarPart extends StackPane{

    private static final Duration ANIMATION_SPEED = Duration.seconds(0.1);

    public CustomMenuBarPart(CustomButton customMenuButton){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("custom-menu-bar-part");

        // Make a pane that will behind the button
        StackPane pane = new StackPane();
        pane.getStyleClass().add("custom-menu-bar-part-pane");

        // The text that will be shown
        Label label = new Label();
        label.setText(customMenuButton.getButtonText());
        label.getStyleClass().add("custom-menu-bar-part-label");

        // Add the label to the pane
        pane.getChildren().add(label);

        // Add the pane behind the menu button
        this.getChildren().addAll(pane,customMenuButton);

        // The animation get created
        TranslateTransition transition = new TranslateTransition(ANIMATION_SPEED, pane);

        this.setOnMouseEntered(e -> {

            transition.stop();

            transition.setToX(this.getWidth());

            transition.play();

        });

        this.setOnMouseExited(e -> {

            transition.stop();

            transition.setToX(0);

            transition.play();
        });

    }

}