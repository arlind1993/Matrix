package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random r = new Random();
        int[] nodes = {784, 16, 16, 10};
        double learningWeight=0.01;
        ImageLoader[] image = new ImageLoader[10];

        JFrame window = new JFrame("Neural network");
        window.setLayout(null);
        window.setContentPane(new SPanel(image, nodes, r, learningWeight));
        window.pack();


        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);


    }


}
