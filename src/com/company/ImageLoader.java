package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageLoader {
    final static int bitSize=784;
    double[][] val;
    int num;
    BufferedImage[] images;
    int maxAm=0;
    String[] names;
    ImageLoader(int num){
        this.num=num;
        initialise();
    }

    ImageLoader(int num, int maxAm){
        this.maxAm=maxAm;
        this.num=num;
        initialise();
    }

    private void initialise() {
        try{
            File folder= new File("trainingSet/"+num);
            if (folder.list()!=null){
            names = folder.list();
            if (maxAm>0){
                images=new BufferedImage[maxAm];
                val=new double[maxAm][bitSize];
            }else{
                images=new BufferedImage[names.length];
                val=new double[names.length][bitSize];
            }
                for (int i = 0; i < images.length; i++) {
                    images[i]=ImageIO.read(new File("trainingSet/"+num+"/"+names[i]));
                    if (images[i]==null){
                        System.out.println("no image loaded");
                    }
                    for (int j = 0; j < bitSize; j++) {
                        val[i][j]= getBrightness(new Color(images[i].getRGB(j%28,j/28)));

                    }
                    //System.out.println(Arrays.deepToString(val));
                }
                System.out.println("Nr"+num +" ended");
                for (int i = 0; i < bitSize; i++) {
                    /*if (val[0][i]>0.5){
                        System.out.print(val[0][i]+"("+i+"), ");
                    }*/
                }
                System.out.println();

            }
            }catch (IOException e){
            e.printStackTrace();
        }
    }

    static double getBrightness(Color color){
        double r=(double) color.getRed()/255;
        double g=(double) color.getGreen()/255;
        double b=(double) color.getBlue()/255;

        double max=r>g?(r>b?r:b):(g>b?g:b);
        double min=r<g?(r<b?r:b):(g<b?g:b);
        return (max+min)/2;
    }
}
