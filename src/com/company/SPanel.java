package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.Random;

public class SPanel extends JPanel {
    JComboBox<String> imageBox;
    JComboBox<String> folderBox;
    JLabel icon;
    JTable neuralNetworkTable;
    JScrollPane tableScrollPane;


    ImageLoader[] image;
    int []nodes;
    Random r;
    final double learningWeight;
    SPanel(ImageLoader[] image, int[] nodes, Random r,double learningWeight){

        this.image=image;
        this.nodes=nodes;
        this.r=r;
        this.learningWeight=learningWeight;
        setLayout(null);
        setPreferredSize(new Dimension(800,600));
        initialise();
    }

    private void initialise() {
        folderBox=new JComboBox<>();
        folderBox.setBounds(10,10,100,28);
        add(folderBox);

        imageBox=new JComboBox<>();
        imageBox.setBounds(150,10,100,28);
        add(imageBox);

        icon=new JLabel();
        icon.setBounds(290,10,28,28);
        add(icon);

        neuralNetworkTable=new JTable();
        neuralNetworkTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        neuralNetworkTable.setFillsViewportHeight(true);
        neuralNetworkTable.getTableHeader().setReorderingAllowed(false);
        neuralNetworkTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

        tableScrollPane=new JScrollPane();
        tableScrollPane.setBounds(10,50,780,520);
        tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(tableScrollPane);

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
                //System.out.println((String) e.getItem());

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
                Matrix wantedOutput=new Matrix(nodes[nodes.length-1],1);
                wantedOutput.data[ref.fPos][0]=1;
                train(colVectors,weightMatrices,biasColVectors,wantedOutput);

                DefaultTableModel tb = new DefaultTableModel();
                for (int i = 0; i < nodes.length-1; i++) {
                    tb.addColumn("Node " + i);
                    tb.addColumn("Weight " + i);
                    tb.addColumn("Bias " + i);
                }
                tb.addColumn("Result");
                String[][] res =prepare(nodes,colVectors,weightMatrices,biasColVectors);

                //System.out.println(Arrays.deepToString(res));
                for (int i = 0; i < res.length; i++) {
                    //System.out.println(Arrays.toString(res[i]));
                    tb.addRow(res[i]);
                }
                neuralNetworkTable.setModel(tb);
                tableScrollPane.setViewportView(neuralNetworkTable);
            }
        });
    }

    void train(Matrix[]colVectors, Matrix[] weightMatrices, Matrix[]biasColVectors, Matrix wantedOutput){
        for (int i = 0; i < nodes.length - 1; i++) {
            colVectors[i + 1] = ((weightMatrices[i].multiply(colVectors[i])).add(biasColVectors[i])).activateMatrix();
            //colVectors[i+1].show();
        }
        Matrix calculatedOutput=colVectors[nodes.length-1];
        System.out.println("Calc out:");
        calculatedOutput.show();
        System.out.println("Want out:");
        wantedOutput.show();

        Matrix error = wantedOutput.subtract(calculatedOutput);
        System.out.println("Err out:");
        error.show();
        Matrix gradient=calculatedOutput.dActivateMatrix();
        System.out.println("Gr out:");
        gradient.show();
        Matrix resGradient=new Matrix(gradient.rows,gradient.columns);

        for (int i = 0; i < gradient.rows; i++) {
            for (int j = 0; j < gradient.columns; j++) {
                resGradient.data[i][j]=gradient.data[i][j] *error.data[i][j]*learningWeight;
            }
        }
        System.out.println("Gr res out:");
        resGradient.show();

    }

    private static String[][] prepare(int[] nodes, Matrix[] colVectors, Matrix[] weightMatrices, Matrix[] biasColVectors) {
        String [][]res=new String[ImageLoader.bitSize][(nodes.length-1)*3+1];
        for(int i=0;i<res.length; i++){
            for (int j = 0; j < res[0].length; j++) {
                //res[i][j]=i+" "+j;
                if (j==res[0].length-1){
                    if(i<colVectors[nodes.length-1].data.length) {
                        res[i][j] = colVectors[nodes.length - 1].data[i][0] + "";
                    }
                }else {
                    switch (j%3){
                        case 0:
                            if (i<nodes[(j/3)]) {
                                res[i][j] = colVectors[j/3].data[i][0]+"";
                            }
                            break;
                        case 1:
                            if (i<nodes[(j/3)+1]) {
                                res[i][j] = Arrays.toString(weightMatrices[j / 3].data[i])+ "";
                            }
                            break;
                        case 2:
                            if (i<nodes[(j/3)+1]) {
                                res[i][j] = biasColVectors[j / 3].data[i][0] + "";
                            }
                            break;
                    }
                }
            }
        }
        return res;
    }
}
