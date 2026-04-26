// Реализация игры "Крестики-нолики" (3x3)
// Минимаксный алгоритм
package com.mycompany.app;
import java.awt.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

;


public class Program {

    public static FileWriter fileWriter;
    public static PrintWriter printWriter;
    public static void main(String[] args) throws IOException {
       JFrame frame = new JFrame("Demo");
       frame.add(new TicTacToePanel(new GridLayout(3,3)));
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setBounds(5, 5, 500, 500);
       frame.setVisible(true);
    }
}


