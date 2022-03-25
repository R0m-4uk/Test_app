package com.project;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class PlaceholderTextField extends JTextField {

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Frame");
        frame.setLayout(new FlowLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.setBorder(BorderFactory.createEtchedBorder());

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBorder(BorderFactory.createEtchedBorder());

        JLabel label1 = new JLabel("Label 1");
        JLabel label2 = new JLabel("New label. Label 2");

        JButton button = new JButton("Change");
        button.addActionListener(e -> {
            panel1.removeAll();

            panel1.add(label2);
            frame.repaint();
            frame.revalidate();
            frame.pack();
            frame.setLocationRelativeTo(null);
        });

        panel1.add(label1);
        panel2.add(button);

        frame.add(panel1);
        frame.add(panel2);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //System.out.println("_"+"\u200B"+"_");
       /* PlaceholderTextField tf = new PlaceholderTextField("");
        //tf.setColumns(20);
        tf.setPlaceholder("All your base are belong to us!");
        final Font f = tf.getFont();
        tf.setFont(new Font(f.getName(), f.getStyle(), 30));
        JOptionPane.showMessageDialog(null, tf);*/
    }





/*
    private String placeholder;

    public PlaceholderTextField() {
    }

    public PlaceholderTextField(String pText) {
        super(pText);
    }
/*
    public String getPlaceholder() {
        return placeholder;
    }*/
/*
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
*/
}