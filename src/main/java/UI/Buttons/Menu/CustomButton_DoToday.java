package UI.Buttons.Menu;

import UI.Enums.ButtonType;

public class CustomButton_DoToday extends CustomMenuButton {
    public CustomButton_DoToday() {
        super(ButtonType.DoToday);
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-menu-button-dotoday");
    }
}
