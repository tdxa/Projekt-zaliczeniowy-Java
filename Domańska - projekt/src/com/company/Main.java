package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/*
    Program nakładający filtry na zdjecia zrobiony w ramach projektu zaliczeniowego na ćwiczenia
    z programowania w języku Java.

    Autor: Anna Domańska [grupa 03 - algorytmika i programowanie]
           Informatyka studia stacjonarne stopień pierwszy, rok II, semestr 3.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("###\t\tWELCOME\t\t###");
        System.out.println("1. Create a random pixel image.\n2. Add a filter to photo.\n3. Exit program.");
        System.out.println("Enter the number of the selected option.");

        Scanner scanner = new Scanner(System.in);
        char choose = scanner.next().charAt(0);

        File f = null;
        BufferedImage i;
        String path = System.getProperty("user.home") + "/Desktop";

        switch (choose) {
            case '1':

                System.out.println("Enter the width of image.");
                scanner = new Scanner(System.in);
                int width = scanner.nextInt();

                System.out.println("Enter the height of image.");
                scanner = new Scanner(System.in);
                int height = scanner.nextInt();

                i = NewImage(width, height);
                new CreatePhoto(i).RandomPixel();
                try {

                    f = new File(path+"\\random.jpg");
                    ImageIO.write(i, "png", f);
                    System.out.println("Done! The new image has been saved on your desktop.");

                } catch (IOException e) {
                    System.out.println(e);
                }
                break;


            case '2':
                System.out.println("###\t\tADD A FILTER TO PHOTO\t\t###");
                System.out.println("1. Add greyscale.\n2. Add sepia.\n3. Add negative.\n4. Add blue filter.\n5. Add red filter.\n6. Add green filter.\n7. Add yellow filter.\n8. Exit program.");
                System.out.println("Enter the number of the selected option.");

                scanner = new Scanner(System.in);
                char c = scanner.next().charAt(0);


                switch (c) {
                    case '1':
                        i = OpenImage();

                        new PhotoFilter(i).Greyscale();

                        try {
                            f = new File(path +"\\greyscale.jpg");
                            ImageIO.write(i, "jpg", f);
                            System.out.println("Done! The new image has been saved on your desktop.");
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;
                    case '2':
                        i = OpenImage();

                        new PhotoFilter(i).Sepia();

                        try {
                            f = new File(path+"\\sepia.jpg");
                            ImageIO.write(i, "jpg", f);
                            System.out.println("Done! The new image has been saved on your desktop.");

                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;
                    case '3':
                        i = OpenImage();

                        new PhotoFilter(i).Negative();

                        try {
                            f = new File(path + "\\negative.jpg");
                            ImageIO.write(i, "jpg", f);
                            System.out.println("Done! The new image has been saved on your desktop.");
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;
                    case '4':
                        i = OpenImage();

                        new PhotoFilter(i).AllBlue();

                        try {
                            f = new File(path + "\\blue.jpg");
                            ImageIO.write(i, "jpg", f);
                            System.out.println("Done! The new image has been saved on your desktop.");
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;
                    case '5':
                        i = OpenImage();

                        new PhotoFilter(i).AllRed();

                        try {
                            f = new File(path + "\\red.jpg");
                            ImageIO.write(i, "jpg", f);
                            System.out.println("Done! The new image has been saved on your desktop.");
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;
                    case '6':
                        i = OpenImage();

                        new PhotoFilter(i).AllGreen();

                        try {
                            f = new File(path + "\\green.jpg");
                            ImageIO.write(i, "jpg", f);
                            System.out.println("Done! The new image has been saved on your desktop.");
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;

                    case '7':
                        i = OpenImage();

                        new PhotoFilter(i).AllYellow();

                        try {
                            f = new File(path + "\\yellow.jpg");
                            ImageIO.write(i, "jpg", f);
                            System.out.println("Done! The new image has been saved on your desktop.");
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;

                    case '8':
                        System.exit(0);
                }

            case '3':
                System.exit(0);
            default:
        }


    }


    /*
           Metoda umożliwiająca użytkownikowi wyboru pliku z dysku przez klasę JFileChooser.
           Wybór wśród plików zostaje zwężony przez utworzony filtr dla rozszerzeń jpeg i jpeg,
           dzięki klasie FileNameExtensionFilter.
     */

    public static BufferedImage OpenImage() {
        BufferedImage image = null;
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only JPG & JPEG images", "jpeg", "jpg");

        fileChooser.setFileFilter(filter);
        int a = fileChooser.showOpenDialog(null);

        if (a == JFileChooser.APPROVE_OPTION) {
            try {
                image = ImageIO.read(fileChooser.getSelectedFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    /*
           Metoda tworząca nowy plik graficzny o zadanej szerokości i wysokości.
     */

    public static BufferedImage NewImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

}

