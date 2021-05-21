package me.milthe.ui;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UiTextField extends UiCompontent{
    //ButtonUI mit "LVL Name here" wenn klicked --> wechsel auf UiTextField mit Blanko TextField Image

    //
    //Text
    //KeyPressed/KeyReleased & Imput extra für UiTextField
    //Char Array für Input
    public String userInputString = "";
    private boolean requestingInput;

    //ArrayList in String konvertieren !!Fehlermeldung!!
    //TODO Groß Klein Schreibung in input beachten und nur buchstaben, zahlen ohne extra zeichen annehmen

    public UiTextField(String name, String filepathImage) {
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

    public void setUserinputString(String input){
        userInputString += input;
    }
}
