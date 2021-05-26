package me.milthe.ui;

import java.io.InputStream;

public class UiButton extends UiCompontent{
    public UiButton(String name, InputStream filepathImage) {
        super(name, filepathImage);
        visible = true;
    }
}
