package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.Random;

public class Ex {
    /*
        Matrix a=new Matrix(new float[][]{
                {1,5,8},
                {2,7,3}
        });
        a.show();
        Matrix b=new Matrix(new float[][]{
                {6,7,8,4},
                {2,1,0,3},
                {4,2,1,9}
        });
        b.show();
        Matrix c=a.multiply(b);
        c.show();

        Matrix a=new Matrix(new float[][]{
                {1,5,8},
                {2,7,3}
        });
        a.show();
        Matrix b=new Matrix(new float[][]{
                {6,7,8},
                {2,1,0}
        });
        b.show();
        Matrix c=a.add(b);
        c.show();
*/
}/*
package com.company;

        import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
        import java.awt.*;
        import java.awt.event.ItemEvent;
        import java.util.Arrays;
        import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random r=new Random();
        int[] nodes ={784,16,16,10};
        ImageLoader[] image=new ImageLoader[10];

        JFrame window=new JFrame("Neural network");
        window.setLayout(null);
        window.getContentPane().setPreferredSize(new Dimension(800,600));
        window.pack();

        JComboBox<String> folderBox=new JComboBox<>();
        folderBox.setBounds(10,10,100,28);
        window.add(folderBox);
        JComboBox<String> imageBox=new JComboBox<>();
        imageBox.setBounds(150,10,100,28);
        window.add(imageBox);

        JLabel icon=new JLabel();
        icon.setBounds(290,10,28,28);
        window.add(icon);

        JTable neuralNetworkTable=new JTable();
        neuralNetworkTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        neuralNetworkTable.setFillsViewportHeight(true);
        neuralNetworkTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane tableScrollPane=new JScrollPane();
        tableScrollPane.setBounds(10,50,780,520);
        tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        window.add(tableScrollPane);

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        for (int fPos = 0; fPos < 10; fPos++) {
            folderBox.addItem("Folder " + fPos);
        }

        var ref = new Object() {
            int fPos = 0;
            int iPos = 0;
        };

        folderBox.addItemListener(e ->{
            if (e.getStateChange()== ItemEvent.SELECTED) {
                imageBox.removeAllItems();
                String s = (String) e.getItem();
                ref.fPos = s.charAt(s.length() - 1) - 48;
                System.out.println((String) e.getItem());

                image[ref.fPos] = new ImageLoader(ref.fPos, 10);

                for (int iPos = 0; iPos < image[ref.fPos].val.length; iPos++) {
                    imageBox.addItem(iPos + ": " + image[ref.fPos].names[iPos]);
                }
            }
        });

        imageBox.addItemListener(e->{
            if (e.getStateChange()== ItemEvent.SELECTED) {
                String s = (String) e.getItem();
                String st = s.split(":")[0];
                ref.iPos = Integer.parseInt(st);

                icon.setIcon(new ImageIcon(image[ref.fPos].images[ref.iPos]));

                Matrix[] colVectors = new Matrix[nodes.length];

                for (int i = 0; i < colVectors.length; i++) {
                    colVectors[i] = new Matrix(nodes[i], 1);
                }

                colVectors[0] = new Matrix(nodes[0], 1);
                //System.out.println("Weights:");
                Matrix[] weightMatrices = new Matrix[nodes.length - 1];
                for (int i = 0; i < weightMatrices.length; i++) {
                    weightMatrices[i] = new Matrix(nodes[i + 1], nodes[i]);
                    weightMatrices[i].createRandomMatrix(r);
                    //weightMatrices[i].show();

                }
                //System.out.println("Biases");
                Matrix[] biasColVectors = new Matrix[nodes.length - 1];
                for (int i = 0; i < biasColVectors.length; i++) {
                    biasColVectors[i] = new Matrix(nodes[i + 1], 1);
                    //weightMatrices[i].createMatrix(0);
                    //biasColVectors[i].show();
                }

                //System.out.println("Nodes:");
                for (int i = 0; i < image[ref.fPos].val[ref.iPos].length; i++) {
                    colVectors[0].data[i][0] = image[ref.fPos].val[ref.iPos][i];
                }
                //colVectors[0].show();
                for (int i = 0; i < nodes.length - 1; i++) {
                    colVectors[i + 1] = ((weightMatrices[i].multiply(colVectors[i])).add(biasColVectors[i])).activateMatrix();
                    //colVectors[i+1].show();
                }
                DefaultTableModel tb = new DefaultTableModel();
                for (int i = 0; i < nodes.length - 1; i++) {
                    tb.addColumn("Node " + i);
                    tb.addColumn("Weight " + i);
                    tb.addColumn("Bias " + i);
                }
                tb.addColumn("Result");
                String[][] res =prepare(nodes,colVectors,weightMatrices,biasColVectors,tb);

                System.out.println(Arrays.deepToString(res));
                for (int i = 0; i < res.length; i++) {
                    tb.addRow(res[i]);
                }
                neuralNetworkTable.setModel(tb);
                tableScrollPane.setViewportView(neuralNetworkTable);
            }
        });
    }

    private static String[][] prepare(int[] nodes, Matrix[] colVectors, Matrix[] weightMatrices, Matrix[] biasColVectors, DefaultTableModel tb) {
        String [][]res=new String[ImageLoader.bitSize][nodes.length*3+1];
        for(int i=0;i<res.length; i++){
            for (int j = 0; j < res[0].length; j++) {
                res[i][j]="";
                if (j==res[0].length-1){
                    if(i<colVectors[nodes.length-1].data.length) {
                        res[i][j] = colVectors[nodes.length - 1].data[i][0] + "";
                    }
                }

            }
        }
        return res;
    }

}*/
