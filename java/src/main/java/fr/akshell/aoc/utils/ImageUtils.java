package fr.akshell.aoc.utils;

import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@UtilityClass
public class ImageUtils {

    public static BufferedImage convertGridToImage(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Set each pixel to a color based on the grid values
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int value = grid[y][x];
                Color color = value == 0 ? Color.BLACK : Color.WHITE; // Example: black for 0, white for 1
                image.setRGB(x, y, color.getRGB());
            }
        }
        return image;
    }

    public static void fromImageToOutput(BufferedImage image, OutputStream output) throws IOException {
        ImageIO.write(image, "png", output);
    }

    public static void fromImageToImageFile(BufferedImage image, String fileName) throws IOException {
        try (OutputStream output = new FileOutputStream(fileName)) {
            fromImageToOutput(image, output);
        }
    }

    public static byte[] fromImageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        fromImageToOutput(image, output);
        return output.toByteArray();
    }
}