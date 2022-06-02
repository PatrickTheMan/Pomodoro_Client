package UI.Buttons;

public class CustomButtonOther extends CustomButton {

    protected CustomButtonOther(){}

    public ButtonAdd Add(){
        return new ButtonAdd();
    }

    public ButtonMinus Minus(){
        return new ButtonMinus();
    }

    public ButtonSave Save(){
        return new ButtonSave();
    }

}
