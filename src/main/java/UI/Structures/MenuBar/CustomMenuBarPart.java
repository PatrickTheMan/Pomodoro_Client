package UI.Structures.MenuBar;

import UI.Buttons.Menu.CustomMenuButton;
import UI.Enums.ExpandState;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class CustomMenuBarPart extends StackPane{

    private ExpandState expandState = ExpandState.HIDDEN;
    private static final Duration ANIMATION_SPEED = Duration.seconds(0.1);

    public CustomMenuBarPart(CustomMenuButton customMenuButton){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("custom-menu-bar-part");


        Label label = new Label();
        label.setText(customMenuButton.getButtonText());

        this.getChildren().addAll(label,customMenuButton);





        this.setOnMouseEntered(e -> {
            if (expandState == ExpandState.HIDDEN) {
                TranslateTransition transition = new TranslateTransition(ANIMATION_SPEED, label);
                transition.setByX(this.getWidth());

                expandState = ExpandState.CHANGING;
                transition.setOnFinished(ae -> {
                    expandState = ExpandState.EXPANDED;
                });

                transition.play();
            }

        });

        this.setOnMouseExited(e -> {

            TranslateTransition transition = new TranslateTransition(ANIMATION_SPEED, label);

            if (expandState == ExpandState.CHANGING) {
                transition.jumpTo(ANIMATION_SPEED);

                expandState = ExpandState.HIDDEN;
            }
            if (expandState == ExpandState.EXPANDED) {
                transition.setByX(-this.getWidth());

                expandState = ExpandState.CHANGING;
                transition.setOnFinished(ae -> {
                    expandState = ExpandState.HIDDEN;
                });

                transition.play();
            }
        });



    }

}
