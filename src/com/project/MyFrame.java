package com.project;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// Окно наследуется от JFrame
@SuppressWarnings("ALL")
public class MyFrame extends JFrame implements ActionListener {
    // Создание необходимых элементов окна
    private JButton startButton, chooseButton;
    private JLabel imageLabel, textLabel;
    private PlaceHolder tf;
    private JComboBox<String> comboBox;
    private JPanel panel;
    private String PathName = "Ваш файл";
    // Конструктор
    MyFrame() {

        panel = new JPanel();
        panel.setSize(1000,500);
        panel.setLayout(null);

        // Создание иконки, названия, размера и позиции окна
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/poke.png")));
        this.setTitle("Space Roman");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Создание двух текстовых меток (NickName и Status) и задание некоторых свойств для них
        JLabel nameLabel = new JLabel();
        JLabel statusLabel = new JLabel();
        nameLabel.setText("Ваше Имя:");
        statusLabel.setText("Статус:");
        nameLabel.setForeground(new Color(0x1FCB1B));
        statusLabel.setForeground(new Color(0xFF9B02)); // 0xA09898  0x1FCB1B
        nameLabel.setSize(150,50);
        statusLabel.setSize(100,50);
        nameLabel.setLocation(230,100);
        statusLabel.setLocation(270,195);
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        statusLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));

        // Создание текстового поля для ввода имени и задание некоторых свойств для него
        tf = new PlaceHolder("");
        tf.setPlaceholder("Введите своё имя..");
        tf.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        tf.setBounds(350,100,300,50);

        // Создание открывающегося списка для выбора своего статуса
        String[] statuses = {"Student", ""};  /* В будущем , "Teacher", "Admin" ... */
        comboBox = new JComboBox<>(statuses);
        comboBox.setSelectedIndex(1);
        comboBox.setBounds(350,200,100,40);


        // Создание кнопки для начала тестирования
        startButton = new JButton();
        startButton.setBounds(425,350,150,45);
        startButton.addActionListener(this);
        startButton.setText("Начать");
        startButton.setFocusable(false);

        chooseButton = new JButton();
        chooseButton.setBounds(225,350,150,45);
        textLabel = new JLabel();
        textLabel.setText("File choose: " + PathName);
        textLabel.setBounds(25,370,900,100);
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
        textLabel.setForeground(new Color(0xFF0202));

        chooseButton.addActionListener(this);
        chooseButton.setText("Выбрать файл");
        chooseButton.setFocusable(false);
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==chooseButton) {
                    Boolean old = UIManager.getBoolean("FileChooser.readOnly");
                    UIManager.put("FileChooser.readOnly", Boolean.TRUE);
                    JFileChooser fileChooser = new JFileChooser();
                    UIManager.put("FileChooser.readOnly", old);
                    int response = fileChooser.showOpenDialog(null);

                    if (response == JFileChooser.APPROVE_OPTION) {
                        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        PathName = file.toString();
                        textLabel.setText("<html><p style=\"width:890px\">" + "File choose: " + PathName + "</p></html>");
                    }

                }
            }
        });


        // Создание заднего фона
        imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(this.getClass().getResource("/icons/space3_1.jpg")));
        imageLabel.setSize(1000,500);

        // Добавляем все компоненты на Панель, которую добавляем на само окно
        panel.add(nameLabel);
        panel.add(textLabel);
        panel.add(statusLabel);
        panel.add(comboBox);
        panel.add(tf);
        panel.add(chooseButton);
        panel.add(startButton);
        panel.add(imageLabel);
        this.add(panel);

        this.setVisible(true);
    }

    // Проверка содержимого текстого поля (отлавливает всевозможные пробелы), нуждается в доработке
    private String checkText(String s) {
        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i != s.length(); i++) {
            switch (String.valueOf(s.charAt(i))) {
                case "\u2000":
                    continue;
                case "\u2001":
                    continue;
                case "\u2002":
                    continue;
                case "\u2003":
                    continue;
                case "\u2004":
                    continue;
                case "\u2005":
                    continue;
                case "\u2006":
                    continue;
                case "\u2007":
                    continue;
                case "\u2008":
                    continue;
                case "\u2009":
                    continue;
                case "\u200A":
                    continue;
                case "\u200B":
                    continue;
                case "\u200C":
                    continue;
                case "\u200D":
                    continue;
                case "\u200E":
                    continue;
                case "\u200F":
                    continue;
                case "\u202F":
                    continue;
                default:
                    s1.append(s.charAt(i));
                    break;
            }
        }

        return s1.toString();
    }

    // Метод для кнопки, определяет какие будут последствия после её нажатия
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) {
            // Проверка на НеПустое текстовое поле и на то, что выбран НеПустой элемент списка
            if(checkText(tf.getText()).trim().equals("") && comboBox.getSelectedIndex() == 1 && PathName.equals("Ваш файл")) {
                JOptionPane.showMessageDialog(null, "Ошибка!\nВы не ввели имя, не выбрали свой статус и не выбрали файл..", "#Ошибка ТриплТрабл", JOptionPane.ERROR_MESSAGE);
            } else if(checkText(tf.getText()).trim().equals("") && comboBox.getSelectedIndex() == 1) {
                JOptionPane.showMessageDialog(null, "Ошибка!\nВы не ввели имя(либо ввели только пробелы) и не выбрали статус", "#Ошибка ДаблТрабл(1)", JOptionPane.ERROR_MESSAGE);
            } else if(checkText(tf.getText()).trim().equals("") && PathName.equals("Ваш файл")) {
                JOptionPane.showMessageDialog(null, "Ошибка!\nВы не ввели имя(либо ввели только пробелы) и не выбрали файл", "#Ошибка ДаблТрабл(2)", JOptionPane.ERROR_MESSAGE);
            } else if(comboBox.getSelectedIndex() == 1 && PathName.equals("Ваш файл")) {
                JOptionPane.showMessageDialog(null, "Ошибка!\nВы не выбрали статус и не выбрали файл", "#Ошибка ДаблТрабл(3)", JOptionPane.ERROR_MESSAGE);
            } else if(checkText(tf.getText()).trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Ошибка!\nВы не ввели имя, либо ввели только пробелы..", "#Ошибка имени", JOptionPane.ERROR_MESSAGE);
            } else if(comboBox.getSelectedIndex() == 1) {
                JOptionPane.showMessageDialog(null, "Ошибка!\nВы не выбрали свой статус..", "#Ошибка статуса", JOptionPane.ERROR_MESSAGE);
            } else if (PathName.equals("Ваш файл")) {
                JOptionPane.showMessageDialog(null, "Ошибка!\nВы не выбрали файл..", "#Ошибка выбора файла", JOptionPane.ERROR_MESSAGE);
            } else {

                    // Выписка необходимых данных из текстовика с тестом в переменные
                    String str = ParseTextFile.textFile(PathName);
                    int lines = ParseTextFile.numbersLines(str);
                    String[] questions = ParseTextFile.numbersOfQuestions(str);
                    String[][] answers = ParseTextFile.numbersOfAnswers(str);
                    String[] trueAnswers = ParseTextFile.trueAnswers(answers);
                    answers = ParseTextFile.delPlus(answers);
                    // Меняем панель окна на следующую (уже с первым вопросом теста)
                try {
                    QuestionPanel window = new QuestionPanel(this, panel, questions, answers,
                                                    trueAnswers, lines, tf.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        }
    }
}
