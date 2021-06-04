package me.milthe.ui;

import java.io.InputStream;

public class UiTextField extends UiCompontent {

    public String userInputString = "";
    private boolean requestingInput;

    //TODO Groß Klein Schreibung in input beachten und nur buchstaben, zahlen ohne extra zeichen annehmen

    public UiTextField(String name, InputStream filepathImage) {
        super(name, filepathImage);
        requestingInput = false;
        visible = true;
    }

    public boolean isRequestingInput() {
        return requestingInput;
    }

    public void setRequestingInput(boolean requestingInput) {
        this.requestingInput = requestingInput;
    }

    public void setUserinputString(String input) {
        userInputString += input;
    }
}
