package com.company;

import java.util.Random;

public class Matrix {

    int columns;
    int rows;

    double[][] data;

    public Matrix(){

    }

    public Matrix(double[][] data){
        this.data=data;
        this.rows=data.length;
        this.columns=data[0].length;
    }

    public Matrix(int rows, int columns){
        this.columns=columns;
        this.rows=rows;

        data=new double[rows][columns];
    }

    Matrix add(Matrix other){
        if (!((this.rows==other.rows)&&(this.columns==other.columns))){
            System.exit(0);
        }

        Matrix res=new Matrix(this.rows,this.columns);
        for (int i = 0; i < res.rows; i++) {
            for (int j = 0; j < res.columns; j++) {
                res.data[i][j]=this.data[i][j]+other.data[i][j];
            }
        }

        return res;
    }
    Matrix subtract(Matrix other){
        if (!((this.rows==other.rows)&&(this.columns==other.columns))){
            System.exit(0);
        }

        Matrix res=new Matrix(this.rows,this.columns);
        for (int i = 0; i < res.rows; i++) {
            for (int j = 0; j < res.columns; j++) {
                res.data[i][j]=this.data[i][j]-other.data[i][j];
            }
        }

        return res;
    }

    Matrix multiply(Matrix other){
        if (this.columns != other.rows){
            System.exit(0);
        }
        Matrix res=new Matrix(this.rows,other.columns);

        for (int i = 0; i < res.rows; i++){
            for (int j = 0; j < res.columns; j++) {
                float sum=0;
                for (int k = 0; k < this.columns; k++) {
                    sum+=this.data[i][k]*other.data[k][j];
                }
                res.data[i][j]=sum;
            }
        }

        return res;
    }
    Matrix activateMatrix(){
        Matrix res=new Matrix(rows,columns);
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++) {
                res.data[i][j]=sigmoidFunction(data[i][j]);
            }
        }
        return res;
    }
    Matrix dActivateMatrix(){
        Matrix res=new Matrix(rows,columns);
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++) {
                res.data[i][j]=derivativeSigmoidFunction(data[i][j]);
            }
        }
        return res;
    }

    void createRandomMatrix(Random r){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j]=r.nextGaussian();
            }
        }
    }

    void show(){

        System.out.println(rows+"x"+columns+"-Matrix:");
        for (int i = 0; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j < columns; j++) {
                if (j!=columns-1){
                    System.out.print(" "+data[i][j]+", ");
                }else {
                    System.out.print(" "+data[i][j]+" ");
                }
            }
            if (i!=rows-1){
                System.out.println("],");
            }else {
                System.out.println("]");
            }
        }
    }

    static double sigmoidFunction(double input){
        return 1/(1+Math.exp(-input));
    }

    static double derivativeSigmoidFunction(double input){
        return Math.exp(-input)/Math.pow((1+Math.exp(-input)),2);
    }

}
