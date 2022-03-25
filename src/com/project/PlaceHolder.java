package com.project;

import javax.swing.*;
import java.awt.*;

public class PlaceHolder extends JTextField {

    private String placeholder;

    public PlaceHolder() {
    }

    public PlaceHolder(String pText) {
        super(pText);
    }


    @Override
    protected void paintComponent(Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(0x75656365, true));
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }

    public void setPlaceholder(String s) {
        placeholder = s;
    }
}
