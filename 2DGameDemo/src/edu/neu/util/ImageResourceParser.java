package edu.neu.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageResourceParser {
    //exception for the read image
    /*public static BufferedImage getBufferedImage(String path) throws IOException {
        return ImageIO.read(new FileInputStream(this.getClass().getResourceAsStream(path)));
    }*/

    public static BufferedImage getBufferedImage(Object obj, String path) throws IOException {
        return ImageIO.read(obj.getClass().getResourceAsStream(path));
    }
}
