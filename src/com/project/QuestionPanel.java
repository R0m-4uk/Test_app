package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class QuestionPanel extends JPanel {
    private final String[] questions;
    private final String[][] answers;
    private final String[] trueAnswers;
    private final String[] userAnswers;
    private final JFrame frame;
    private JPanel panel;
    private int currentAnswerIndex = 0;
    private int amountTrueAnswers = 0;
    private String name;


    // Конструктор новой панели
    public QuestionPanel(JFrame frame, JPanel panel, String[] questions, String[][] answers, String[] trueAnswers, int len, String text) throws IOException {
        this.frame = frame;
        this.panel = panel;
        this.questions = questions;
        this.answers = answers;
        this.trueAnswers = trueAnswers;
        this.userAnswers = new String[len];
        this.name = text;

        panel.removeAll();
        panel = getNext();
        frame.repaint();
        frame.revalidate();
    }



    private JPanel getNext() throws IOException {
        // закончились вопросы
        if (currentAnswerIndex == questions.length) {
            EndPanel myPanel = new EndPanel(panel, frame, userAnswers, trueAnswers, trueAnswers.length, name);
            panel.add(myPanel);
            return panel;
        }

        // Создание необходимых компонентов панели
        JLabel questionLabel = new JLabel();
        JRadioButton rb1;
        JRadioButton rb2;
        JRadioButton rb3;
        JRadioButton rb4;
        JButton nextButton;

        // Задание текстовой метке label1 некоторых свойств
        questionLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        questionLabel.setForeground(new Color(0xFFE500));
        questionLabel.setText(questions[currentAnswerIndex]);
        questionLabel.setText("<html><p style=\"width:700px\">"+questions[currentAnswerIndex]+"</p></html>");
        questionLabel.setSize(1000, 100);
        questionLabel.setLocation(50, 50);
        // Задание кнопке rb1 некоторых свойств
        rb1 = new JRadioButton("<html><p style=\"width:100px\">"+answers[currentAnswerIndex][0]+"</p></html>");
        rb1.setFont(new Font("Times New Roman", Font.BOLD, 19));
        rb1.setForeground(new Color(0xFF9B02));
        rb1.setContentAreaFilled(false);
        rb1.setFocusable(false);
        rb1.setBounds(250, 160, 200, 100);
        // Задание кнопке rb2 некоторых свойств
        rb2 = new JRadioButton("<html><p style=\"width:100px\">"+answers[currentAnswerIndex][1]+"</p></html>");
        rb2.setFont(new Font("Times New Roman", Font.BOLD, 19));
        rb2.setForeground(new Color(0xFF9B02));
        rb2.setContentAreaFilled(false);
        rb2.setFocusable(false);
        rb2.setBounds(250, 280, 200, 100);
        // Задание кнопке rb3 некоторых свойств
        rb3 = new JRadioButton("<html><p style=\"width:100px\">"+answers[currentAnswerIndex][2]+"</p></html>");
        rb3.setFont(new Font("Times New Roman", Font.BOLD, 19));
        rb3.setForeground(new Color(0xFF9B02));
        rb3.setContentAreaFilled(false);
        rb3.setFocusable(false);
        rb3.setBounds(550, 160, 200, 100);
        // Задание кнопке rb4 некоторых свойств
        rb4 = new JRadioButton("<html><p style=\"width:100px\">"+answers[currentAnswerIndex][3]+"</p></html>");
        rb4.setFont(new Font("Times New Roman", Font.BOLD, 19));
        rb4.setForeground(new Color(0xFF9B02));
        rb4.setContentAreaFilled(false);
        rb4.setFocusable(false);
        rb4.setBounds(550, 280, 200, 100);
        // Создание кнопки и задание ей некоторых свойств
        nextButton = new JButton();
        // Если вопрос последний, то даём названию кнопки имя "Закончить", в противном случае "Дальше"
        if(currentAnswerIndex == trueAnswers.length-1) {
            nextButton.setText("Закончить");
        } else { nextButton.setText("Дальше"); }
        nextButton.setBounds(425, 400, 150, 50);
        nextButton.setFocusable(false);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == nextButton) {
                    if(warning(rb1, rb2, rb3, rb4)) {
                        // После нажатия кнопки панель обновляется на новую
                        try {
                            userAnswers[amountTrueAnswers++] = retAnswer(rb1, rb2, rb3, rb4);
                            panel.removeAll();
                            panel = getNext();
                            frame.repaint();
                            frame.revalidate();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ошибка!\nВы не выбрали не один вариант ответа..", "#Ошибка ОтветГде", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        // Группировка кнопок с целью выбора только единственной кнопки
        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        group.add(rb3);
        group.add(rb4);


        JLabel label_1 = new JLabel();
        label_1.setIcon(new ImageIcon(this.getClass().getResource("/icons/space3_1.jpg")));
        label_1.setSize(1000, 500);

        currentAnswerIndex++;

        panel.add(questionLabel);
        panel.add(rb1);
        panel.add(rb2);
        panel.add(rb3);
        panel.add(rb4);
        panel.add(nextButton);
        panel.add(label_1);

        return panel;
    }
    // Проверка на то что выбрана хотя бы одна кнопка
    private boolean warning(JRadioButton rb1, JRadioButton rb2, JRadioButton rb3, JRadioButton rb4) {
        return rb1.isSelected() || rb2.isSelected() || rb3.isSelected() || rb4.isSelected();
    }
    // Метод возвращает текст нажатой кнопки
    private String retAnswer(JRadioButton rb1, JRadioButton rb2, JRadioButton rb3, JRadioButton rb4) {
        return (rb1.isSelected() ? answers[currentAnswerIndex-1][0] :
               (rb2.isSelected() ? answers[currentAnswerIndex-1][1] :
               (rb3.isSelected() ? answers[currentAnswerIndex-1][2] :
               (rb4.isSelected() ? answers[currentAnswerIndex-1][3] : null))));

    }
    // Метод возвращает количество правильных ответов пользователя
    public static int getResult(String[] trueAnswers, String[] userAnswers) {
        int result = 0;
        for(int i = 0; i != trueAnswers.length; i++) {
            if(trueAnswers[i].equals(userAnswers[i])) {
                result++;
            }
        }
        return result;
    }

}
