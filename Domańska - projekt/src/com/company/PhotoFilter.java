package com.company;

import java.awt.image.BufferedImage;

public class PhotoFilter{

    int height, width;
    BufferedImage image;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public BufferedImage getImage() {
        return image;
    }

    public PhotoFilter(BufferedImage image) {
        this.image = image;
        this.height = image.getHeight();
        this.width = image.getWidth();
    }

    /*
        Metoda nakładająca skalę szarości na wgrane zdjęcie użytkownika poprzez obliczenie średniej dla każdej wartości
        RGBA w pojedynczym pikselu. Obliczona nowa wartość RGBA zastępuje poprzednią - wartość alfa nie musi zostać
        podmieniana, ponieważ kontroluje ona jedynie przezroczystość piksela.
     */

    void Greyscale(){
        int pixel, alpha, red, green, blue,avg;
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                pixel = this.getImage().getRGB(j,i);

                alpha = (pixel / 16777216)& 255;
                red = (pixel / 65536)& 255;
                green = (pixel / 256)& 255;
                blue = pixel& 255;

                avg = (red+green+blue)/3;

                pixel = (alpha * 16777216) | (avg * 65536) | (avg * 256) | avg;

                image.setRGB(j,i, pixel);
            }
        }
    }

    /*
        Metoda nakładająca sepię na wgrane zdjęcie użytkownika poprzez obliczenie nowej wartości
        RGBA w pojedynczym pikselu. Obliczona nowa wartość RGBA zastępuje poprzednią - wartość alfa nie musi zostać
        podmieniana, ponieważ kontroluje ona jedynie przezroczystość piksela.
     */

    void Sepia(){
        int pixel, alpha, red, green, blue, newRed, newGreen, newBlue;
        for(int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){

                pixel = this.getImage().getRGB(j,i);

                alpha = (pixel / 16777216) & 255;
                red = (pixel / 65536) & 255;
                green = (pixel / 256) & 255;
                blue = pixel & 255;

                newRed = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                newGreen = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                newBlue = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

                CheckingRange(alpha, newRed, newGreen, newBlue, i, j);
            }
        }
    }

    /*
       Metoda nakładająca efekt negatywu na wgrane zdjęcie użytkownika poprzez obliczenie nowej wartości
       RGBA w pojedynczym pikselu przez odjęcie obecnych od górnej wartośći RGB (255). Obliczona nowa wartość RGBA
       zastępuje poprzednią - wartość alfa nie musi zostać podmieniana, ponieważ kontroluje ona jedynie przezroczystość
       piksela.
    */
    void Negative(){
        int pixel, alpha, red, green, blue;
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                pixel = this.getImage().getRGB(j,i);

                alpha = (pixel / 16777216)& 255;
                red = (pixel / 65536)& 255;
                green = (pixel / 256)& 255;
                blue = pixel& 255;

                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;
                pixel = (alpha * 16777216) | (red * 65536) | (green * 256) | blue;

                image.setRGB(j,i, pixel);
            }
        }
    }

    /*
       Metoda nakładająca kolor zielony na wgrane zdjęcie użytkownika poprzez ustawienia wartości 0 dla składników
       czerwonego i niebieskiego RGBA w pojedynczym pikselu. Ustalone nowe wartości RGBA zastępują poprzednie - wartość
       alfa nie musi zostać podmieniana, ponieważ kontroluje ona jedynie przezroczystość piksela.
    */
    void AllGreen(){
        int pixel, alpha, red, green, blue;
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                pixel = this.getImage().getRGB(j,i);

                alpha = (pixel / 16777216)& 255;
                green = (pixel / 256)& 255;
                red = 0;
                blue = 0;
                pixel = (alpha * 16777216) | (red * 65536) | (green * 256) | blue;

                image.setRGB(j,i, pixel);
            }
        }
    }

    /*
      Metoda nakładająca kolor niebieski na wgrane zdjęcie użytkownika poprzez ustawienia wartości 0 dla składników
      czerwonego i zielonego RGBA w pojedynczym pikselu. Ustalone nowe wartości RGBA zastępują poprzednie - wartość
      alfa nie musi zostać podmieniana, ponieważ kontroluje ona jedynie przezroczystość piksela.
   */
    void AllBlue(){
        int pixel, alpha, red, green, blue;
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                pixel = this.getImage().getRGB(j,i);
                alpha = (pixel / 16777216)& 255;
                blue = pixel& 255;
                red = 0;
                green = 0;
                pixel = (alpha * 16777216) | (red * 65536) | (green * 256) | blue;

                image.setRGB(j,i, pixel);
            }
        }
    }

     /*
       Metoda nakładająca kolor czerwony na wgrane zdjęcie użytkownika poprzez ustawienia wartości 0 dla składników
       zielonego i niebieskiego RGBA w pojedynczym pikselu. Ustalone nowe wartości RGBA zastępują poprzednie - wartość
       alfa nie musi zostać podmieniana, ponieważ kontroluje ona jedynie przezroczystość piksela.
    */

    void AllRed(){
        int pixel, alpha, red, green, blue;
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                pixel = this.getImage().getRGB(j,i);

                alpha = (pixel / 16777216)& 255;
                red = (pixel / 65536)& 255;
                green = 0;
                blue = 0;
                pixel = (alpha * 16777216) | (red * 65536) | (green * 256) | blue;

                image.setRGB(j,i, pixel);
            }
        }
    }

     /*
       Metoda nakładająca kolor żółty na wgrane zdjęcie użytkownika poprzez ustawienia wartości 0 dla składnia
       niebieskiego i obliczenia nowych wartości dla czerwonego i zielonego w pojedynczym pikselu. Ustalone nowe
       wartości RGBA zastępują poprzednie - wartość alfa nie musi zostać podmieniana, ponieważ kontroluje ona jedynie
       przezroczystość piksela.
    */

    void AllYellow(){
        int pixel, alpha, red, green, blue, newRed, newGreen, newBlue;
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                pixel = this.getImage().getRGB(j,i);

                alpha = (pixel / 16777216)& 255;
                red = (pixel / 65536)& 255;
                green = (pixel / 256)& 255;
                blue = pixel& 255;

                newRed = (int)(0.5 * red + 0.769 * green + 0.12 * blue);
                newGreen = (int)(0.349 * red + 0.886 * green + 0.168 * blue);
                newBlue = 0* red + 0 * green + 0 * blue;

                CheckingRange(alpha, newRed, newGreen, newBlue, i, j);
            }
        }
    }

    /*
        Metoda sprawdzająca warunki, aby nowe wartośći RGBA wyliczane przy metodach nakładania sepii oraz
        koloru żółtego nie przekroczyły zakresu, na którym bazuje RGBA, czyli 0-255.
     */

    private void CheckingRange(int alpha, int newRed, int newGreen, int newBlue, int i, int j) {
        int red, blue, green, pixel;

        if(newRed > 255)
            red = 255;
        else
            red = newRed;

        if(newBlue > 255)
            blue = 255;
        else
            blue = newBlue;

        if(newGreen > 255)
            green = 255;
        else
            green = newGreen;

        pixel = (alpha * 16777216) | (red * 65536) | (green*256) | blue;

        image.setRGB(j,i,pixel);
    }

}
