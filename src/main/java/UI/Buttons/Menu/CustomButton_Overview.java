package UI.Buttons.Menu;

import UI.Enums.ButtonType;

public class CustomButton_Overview extends CustomMenuButton {
    public CustomButton_Overview() {
        super(ButtonType.Overview);
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-menu-button-overview");
    }
}
