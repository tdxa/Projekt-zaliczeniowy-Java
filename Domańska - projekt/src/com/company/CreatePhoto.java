package com.company;

import java.awt.image.BufferedImage;

public class CreatePhoto{

    int width, height;
    BufferedImage image;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public CreatePhoto(BufferedImage image) {
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.image = image;
    }

    /*
        Metoda ustawiająca losowe wartości RGBA z zakresu 0-255 dla każdego piksela znajdującego się
        w nowoutworzonym pustym pliku graficznym o podanych wymiarach.
     */

    void RandomPixel(){
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0 ; j < this.getWidth(); j++){

                int alpha = (int)(Math.random()*256);
                int red = (int)(Math.random()*256);
                int green = (int)(Math.random()*256);
                int blue = (int)(Math.random()*256);

                int pixel = (alpha * 16777216) | (red * 65536) | (green * 256) | blue;

                image.setRGB(j,i, pixel);
            }
        }
    }
}