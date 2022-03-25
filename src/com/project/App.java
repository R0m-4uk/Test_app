package com.project;

import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class App {
      public static void main(String[] args) throws IOException {
          Border border = BorderFactory.createLineBorder(new Color(0x8E4E46), 4);

          JLabel label = new JLabel();
          label.setText("Hey, Romi4ik");
          label.setBorder(border);
          label.setOpaque(true);
          label.setBackground(new Color(0x345252));

          JFrame frame = new JFrame("Space Roman");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(500,500);
         // frame.setIconImage(ImageIO.read(new File("src/poke.png")));
        //   frame.getContentPane().setBackground(new Color(137, 57, 175, 176));
          frame.setVisible(false);
          frame.add(label);

          StringBuilder str = new StringBuilder("Список");
          System.out.println(str.toString());
       /*   JFrame frame = new JFrame("Space Roman");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(1000, 1000);
          frame.setVisible(true);
          frame.setIconImage(ImageIO.read(new File("src/poke.png")));
         // frame.getContentPane().setBackground(new Color(137, 57, 175, 176));
          frame.add(label);*/

      }
  }

