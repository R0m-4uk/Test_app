package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EndPanel extends JPanel{
    private String[] rangs = {"Нович0к", "ПроДвинутый", "Шарящий", "Мегамозг", "Астроном", "Космический полиглот"};
    private String fileName = "";
    private JLabel label_warning;
    private JLabel imageLabel;
    private JLabel endLabel;
    private JLabel resultLabel;
    private JLabel rangLabel;
    private JLabel nameLabel;
    private JButton saveButton;
    private JButton answersButton;
    private JButton exitButton;


    public EndPanel(JPanel panel, JFrame frame, String[] userAnswers, String[] trueAnswers, int len, String name) throws IOException {
        // Установка заднего фона

        imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(this.getClass().getResource("/icons/space3_1.jpg")));
        imageLabel.setSize(1000,500);

        // Создание текстовой метки endLabel и задание ей некоторых свойств
        endLabel = new JLabel();
        endLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        endLabel.setForeground(new Color(0x1FCB1B));
        endLabel.setSize(1000, 50);
        endLabel.setLocation(375, 30);
        endLabel.setText("Конец тестирования");

        // Создание текстовой метки resultLabel и задание ей некоторых свойств
        int res = QuestionPanel.getResult(trueAnswers, userAnswers);
        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        resultLabel.setForeground(new Color(0xFF9B02));
        resultLabel.setSize(1000, 50);
        resultLabel.setLocation(400, 200);
        resultLabel.setText("Результат:    " + res + "/" + len);

        // Создание текстовой метки rangLabel и задание ей некоторых свойств
        rangLabel = new JLabel();
        rangLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        rangLabel.setForeground(new Color(0xFF9B02));
        rangLabel.setSize(1000, 50);
        rangLabel.setLocation(400, 300);
        rangLabel.setText("Ранг:    " + yourRang(res, len));

        // Создание текстовой метки nameLabel и задание ей некоторых свойств
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        nameLabel.setForeground(new Color(0xFF9B02));
        nameLabel.setSize(1000, 50);
        nameLabel.setLocation(400, 100);
        nameLabel.setText("Имя:    " + name);

        // Создание кнопки для сохранения результатов в файл
        saveButton = new JButton();
        saveButton.setText("Сохранить"); // Мб переименовать в "Создать файл с результатами", а уже следующая кнопка будет "Сохранить"
        saveButton.setBounds(425, 400, 150, 50);
        saveButton.setFocusable(false);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == saveButton) {
                    if (fileName.equals("")) {
                        JOptionPane.showMessageDialog(null, "Информейшн!\nВведите название файла без расширения .txt", "#Информейшн по заполнению", JOptionPane.INFORMATION_MESSAGE);
                        getNameFile(saveButton, panel, frame);
                        answersButton.setEnabled(false);
                        exitButton.setEnabled(false);
                        exitButton.setVisible(false);
                    } else {
                        String s1 = System.getProperty("user.dir");
                        File folder = new File(s1 + "\\YourResults");
                        if(!folder.exists()) {
                            folder.mkdir();
                        }
                        File file1 = new File(folder, fileName + ".txt");

                        panel.remove(label_warning);
                        frame.repaint();
                        frame.revalidate();

                        try {
                            if (file1.createNewFile()) {
                                saveButton.setEnabled(false);
                                answersButton.setEnabled(true);
                                System.out.println("File created");
                            } else {
                                System.out.println("File already exists");
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try (FileWriter writer = new FileWriter(file1, false)) {
                            writer.write("###Результат тестирования###\r\n\r\n");
                            writer.write("\r\tName: " + name + "\r\n");
                            writer.write("\r\tResult: " + res + "/" + len + "\r\n");
                            writer.write("\r\tRang: " + yourRang(res, len) + "\r\n");
                            writer.flush();
                            exitButton.setVisible(true);
                            exitButton.setEnabled(true);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        });

        // Создание кнопки для открытия нового окна с таблицей правильных ответов и ответов пользователя
        answersButton = new JButton();
        answersButton.setText("Проверка ответов");
        answersButton.setBounds(225, 400, 150, 50);
        answersButton.setFocusable(false);
        answersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== answersButton) {
                    table(frame, trueAnswers, userAnswers);
                }
            }
        });
        // Кнопка для выхода из приложения
        exitButton = new JButton();
        exitButton.setText("Выход");
        exitButton.setBounds(625, 400, 150, 50);
        exitButton.setFocusable(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        // Добавление компонентов на панель
        panel.add(endLabel);
        panel.add(resultLabel);
        panel.add(rangLabel);
        panel.add(nameLabel);
        panel.add(saveButton);
        panel.add(answersButton);
        panel.add(exitButton);
        panel.add(imageLabel);
    }

    // Высчитывается ранг в соответствии с полученными баллами
    private String yourRang(int a, int b) {
        if ((float) a / b * 100 == 0) {
            return rangs[0];
        } else if ((float) a / b * 100 >= 1 && (float) a / b * 100 <= 25) {
            return rangs[1];
        } else if ((float) a / b * 100 >= 26 && (float) a / b * 100 <= 50) {
            return rangs[2];
        } else if ((float) a / b * 100 >= 51 && (float) a / b * 100 <= 75) {
            return rangs[3];
        } else if ((float) a / b * 100 >= 76 && (float) a / b * 100 <= 99) {
            return rangs[4];
        } else return rangs[5];
    }

    // Метод необходим для ввода имени файла
    private void getNameFile(JButton button, JPanel panel, JFrame frame){

        button.setEnabled(false);

        PlaceHolder textField = new PlaceHolder();
        textField.setPlaceholder("Введите имя файла..");
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        textField.setBounds(600,405,250,40);

        JButton okButton = new JButton();
        okButton.setText("Ok");
        okButton.setBounds(860, 405, 50, 40);
        okButton.setFocusable(false);

        panel.remove(imageLabel);
        panel.add(textField);
        panel.add(okButton);
        panel.add(imageLabel);
        frame.repaint();
        frame.revalidate();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == okButton) {
                    String str = textField.getText();
                    if(str.trim().equals("")){
                        JOptionPane.showMessageDialog(null, "Ошибка!\nВы не ввели имя файла, либо ввели только пробелы..", "#Ошибка в имени файла", JOptionPane.ERROR_MESSAGE);
                    } else {
                        fileName = textField.getText();
                        panel.remove(okButton);
                        panel.remove(textField);

                        button.setEnabled(true);

                        label_warning = new JLabel("← Подтвердить сохранение");
                        label_warning.setFont(new Font("Times New Roman", Font.BOLD, 23));
                        label_warning.setForeground(new Color(0xFF0202));
                        label_warning.setBounds(600,405,400,40);

                        panel.remove(imageLabel);
                        panel.add(label_warning);
                        panel.add(imageLabel);
                        frame.repaint();
                        frame.revalidate();
                        panel.setVisible(true);
                    }
                }
            }
        });

    }

    //
    private void table(JFrame frame, String[] trueAnswers, String[] userAnswers) {
        String[][] tableAnswers = new String[trueAnswers.length][3];
        String[] columnNames = {"Номер вопроса", "Ваши ответы", "Правильные ответы"};
        for (int i = 0; i != tableAnswers.length; i++) {
            tableAnswers[i][0] = String.valueOf(i+1);
            tableAnswers[i][1] = userAnswers[i];
            tableAnswers[i][2] = trueAnswers[i];
        }

        JTable table = new JTable(tableAnswers, columnNames) {
            @Override
            public  boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table.setBounds(300,50,500,350);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);


        JDialog dialog = new JDialog(frame, "CheckingResponses",true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        dialog.getContentPane().add(scrollPane, BorderLayout.NORTH);
        dialog.pack();
        dialog.setVisible(true);

    }
}
