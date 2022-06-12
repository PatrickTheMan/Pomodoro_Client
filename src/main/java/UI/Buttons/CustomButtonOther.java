package UI.Buttons;

import Application.Singleton.ControllerSingleton;

/**
 * @author Patrick G. Schemel
 */
public class CustomButtonOther extends CustomButton {

    //region [Constructor]

    protected CustomButtonOther(){}

    //endregion

    //region [Buttons]

    /**
     * <Strong>Use the PlayAndPause button setup</Strong>
     * @return the button
     */
    public CustomButtonOther add(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Add";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-add");

        return this;
    }

    /**
     * <Strong>Use the PlayAndPause button setup</Strong>
     * @return the button
     */
    public CustomButtonOther minus(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Remove";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-remove");

        return this;
    }

    /**
     * <Strong>Use the PlayAndPause button setup</Strong>
     * @return the button
     */
    public CustomButtonOther edit(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Edit";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-edit");

        return this;
    }

    /**
     * <Strong>Use the PlayAndPause button setup</Strong>
     * @return the button
     */
    public CustomButtonOther accept(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Accept";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-accept");

        return this;
    }

    /**
     * <Strong>Use the PlayAndPause button setup</Strong>
     * @return the button
     */
    public CustomButtonOther delete(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Delete";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-delete");

        return this;
    }

    /**
     * <Strong>Use the PlayAndPause button setup</Strong>
     * @return the button
     */
    public CustomButtonOther forward(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Forward";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-forward");

        return this;
    }

    /**
     * <Strong>Use the PlayAndPause button setup</Strong>
     * @return the button
     */
    public CustomButtonOther back(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Back";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-back");

        return this;
    }

    //endregion

}
