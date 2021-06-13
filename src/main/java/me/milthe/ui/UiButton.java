package me.milthe.ui;

import java.io.InputStream;

/**
 * UiComponent des Types Button. Soll bei klick auf sich etwas verändern
 */
public class UiButton extends UiComponent {
    public UiButton(String name, InputStream filepathImage) {
        super(name, filepathImage);
    }
}
