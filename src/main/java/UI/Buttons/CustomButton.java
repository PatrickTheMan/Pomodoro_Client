package UI.Buttons;

import Application.Singleton.ControllerSingleton;
import javafx.scene.control.Button;

/**
 * @author Patrick G. Schemel
 */
public class CustomButton extends Button {

    //region [Variables]

    protected String buttonText;

    //endregion

    //region [Normal Getters & Setters]

    public String getButtonText() {
        return buttonText;
    }

    //endregion

    //region [Constructor]

    public CustomButton(){
        // Make this button use the custom-menu-button css styling
        this.getStyleClass().add("custom-button");
        // Set the selection & hovered function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });
        this.hoverProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setHovered(this,newVal);
        });
    }

    public CustomButton(String text){
        // Make this button use the custom-menu-button css styling
        this.getStyleClass().add("custom-button-text");

        // Set the text
        this.setText(text);

        // Set the selection & hovered function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });
        this.hoverProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setHovered(this,newVal);
        });
    }

    //endregion

    //region [Buttons Types]

    /**
     * <Strong>Goes to the menu section of the custombuttons</Strong>
     * @return a menu button
     */
    public CustomButtonMenu menu(){
        return new CustomButtonMenu();
    }

    /**
     * <Strong>Goes to the controls section of the custombuttons</Strong>
     * @return a control button
     */
    public CustomButtonControls controls(){return new CustomButtonControls();}

    /**
     * <Strong>Goes to the other section of the custombuttons</Strong>
     * @return an other button
     */
    public CustomButtonOther other(){
        return new CustomButtonOther();
    }

    //endregion

    //region [Methods]

    /**
     * <Strong>Sets the button as focused</Strong>
     * @param focused is wether or not the button is focused
     */
    public void setCustomFocused(boolean focused) {
        this.setFocused(focused);
    }

    /**
     * <Strong>Sets the size of the text</Strong>
     * @param size is the size of the background (which has an image)
     */
    public void setSize(int size){
        // Set the button size
        this.setStyle("-fx-background-size: "+size+";");
    }

    //endregion

}
