package com.java8.springboot.java.InputOutput_Stream;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
 
 
public class ImageIOExample {    
 
    public static void main( String[] args ){
      imageIoWrite();
    }
     
    public static void imageIoWrite() {
         BufferedImage bImage = null;
         try {
             File initialImage = new File("C://Users/Rou/Desktop/image.jpg");
             bImage = ImageIO.read(initialImage);
 
             /* Writes an image using an arbitrary ImageWriterthat supports the given format to a File.
             Ifthere is already a File present, its contents arediscarded.
             Parameters:
            	 im a RenderedImage to be written.
            	 formatName a String containing the informalname of the format.output 
            	 a File to be written to.
            	 */

             ImageIO.write(bImage, "gif", new File("C://Users/Rou/Desktop/image.gif"));
             ImageIO.write(bImage, "jpg", new File("C://Users/Rou/Desktop/image.png"));
             ImageIO.write(bImage, "bmp", new File("C://Users/Rou/Desktop/image.bmp"));
 
         } catch (IOException e) {
               System.out.println("Exception occured :" + e.getMessage());
         }
         System.out.println("Images were written succesfully.");
    }
 
}

