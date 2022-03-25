package com.project;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ParseTextFile {
    //private static String str_ = "";
    public static int AmountOfQuestions = 0;
    // Константо количество ответов на вопросы = 4
    public static final int AmountOfAnswers = 4;
    // Записываем все данные из текстовика в строчку
    public static String textFile(String FilePath) {

        File file1 = new File(FilePath);
        StringBuilder str = new StringBuilder();

        try (FileReader reader = new FileReader(file1)) {
            int c;
            while ((c = reader.read()) != -1) {
                str.append((char) c);
            }
// /* Для создания exe-file'a, дабы буквы русские остались
            String str1 = new String(str.toString().getBytes("Windows-1251"),"UTF-8");
            return str1;
           // return str.toString();
        } catch (IOException ex) {
            return "Error";
        }
    }

    // Метод NumbersLines считает количество вопросов в строчке
    public static int numbersLines(String str) {
        int Num = 1;
        int len = str.length();
        for (int i = 0; i != len; i++) {
            if(str.charAt(i) == '#') {
                Num++;
            }
        }
        AmountOfQuestions = Num;
        return Num;
    }

    // Метод NumbersOfQuestions упаковывает все вопросы в массив
    public static String[] numbersOfQuestions(String str) {
        int idx_q = 0;
        String[] questions = new String[AmountOfQuestions];
        StringBuilder str1 = new StringBuilder();
        int len = str.length();
        for (int i = 1; i != len; i++) {

            if (str.charAt(i) == '\"') {
                str1.append(str.charAt(i));
                i++;
                while(str.charAt(i) != '\"') {
                    str1.append(str.charAt(i));
                    i++;
                }
                str1.append(str.charAt(i));
                questions[idx_q] = str1.toString();
                str1 = new StringBuilder();
                i++;
            }

            if(str.charAt(i) == '#') {
                idx_q++;
                i++;
            }

        }
        return questions;
    }
    //Метод NumbersOfAnswers ищет и упаковывает все ответы в массив
    public static String[][] numbersOfAnswers(String str) {
        int idx_a = 0, idx_q = 0;
        String[][] answers = new String[AmountOfQuestions][AmountOfAnswers];
        StringBuilder str1 = new StringBuilder();
        int len = str.length();
        for (int i = 1; i != len; i++) {
            if(idx_a == 4) {
                idx_a = 0;
            }

            if (str.charAt(i) == '\"') {
                i++;
                while(str.charAt(i) != '\"') {
                    i++;
                }
                i += 2;
            }

            str1.append(str.charAt(i));

            if(str.charAt(i) == '\n') {
                answers[idx_q][idx_a] = str1.toString();
                idx_a++;
                str1 = new StringBuilder();
            }

            if(str.charAt(i) == '#') {
                idx_q++;
                i++;
            }

        }
        return answers;
    }
    // Удаляет все символы "+" в каждом элементе массива правильных ответов
    public static String[] trueAnswers(String[][] answers) {
        String[] trueAnswers = new String[answers.length];
        int len = answers.length;
        for(int i = 0; i != len; i++) {
            for(int j = 0; j != answers[i].length; j++) {
                first:
                    if(answers[i][j].charAt(0) == '+') {
                        trueAnswers[i] = answers[i][j].replace("+", "");
                        break;
                    } else { break first; }
            }
        }

        return trueAnswers;
    }
    // Удаляет все символы "+" в некоторых элементах массива со всеми ответами
    public static String[][] delPlus(String[][] answers) {
        int len = answers.length;
        for(int i = 0; i != len; i++) {
            for (int j = 0; j != answers[i].length; j++) {
                first:
                if(answers[i][j].charAt(0) == '+') {
                    answers[i][j] = answers[i][j].replace("+", "");
                    break;
                } else { break first; }
            }
        }
        return answers;
    }

}
