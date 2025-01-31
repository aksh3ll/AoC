package fr.akshell.aoc.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
class ImageUtilsTest {
    public static final int[][] TEST_GRID = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
    };

    @Test
    void givenGrid_whenWriteToFile_thenFileExists() throws IOException {
        BufferedImage image = ImageUtils.convertGridToImage(TEST_GRID);
        assertThat(image).isNotNull();
        Path path = Files.createTempFile("test", ".png");
        try {
            ImageUtils.fromImageToImageFile(image, path.toString());
            assertThat(Files.exists(path)).isTrue();
        } finally {
            Files.delete(path);
        }
    }

    @Test
    void givenGrid_whenWriteToArray_thenArrayReturned() throws IOException {
        BufferedImage image = ImageUtils.convertGridToImage(TEST_GRID);
        assertThat(image).isNotNull();
        byte[] bytes = ImageUtils.fromImageToByteArray(image);
        assertThat(bytes).isNotNull();
    }
}
