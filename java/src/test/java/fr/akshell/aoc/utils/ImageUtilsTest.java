package fr.akshell.aoc.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
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
    public static final byte[] TEST_ARRAY = new byte[]{
            -119, 80, 78, 71, 13, 10, 26, 10,
            0, 0, 0, 13, 73, 72, 68, 82,
            0, 0, 0, 8, 0, 0, 0, 8,
            8, 2, 0, 0, 0, 75, 109, 41,
            -36, 0, 0, 0, 35, 73, 68, 65,
            84, 120, 94, 99, -8, -113, 3, 48,
            -128, 48, 6, 64, 72, -96, -88, 69,
            -109, 64, -88, 37, 86, 2, 14, 80,
            36, -48, 0, 84, 2, 43, 0, 0,
            -76, -102, 119, -119, -121, 37, -40, 97,
            0, 0, 0, 0, 73, 69, 78, 68,
            -82, 66, 96, -126
    };

    @Test
    void givenGrid_whenWriteToFile_thenFileExistsAndContentMatch() throws IOException {
        BufferedImage image = ImageUtils.convertGridToImage(TEST_GRID);
        assertThat(image).isNotNull();
        Path path = Files.createTempFile("test", ".png");
        try {
            var result = ImageUtils.fromImageToImageFile(image, path.toString());
            assertThat(result).isTrue();
            assertThat(Files.exists(path)).isTrue();
            assertThat(Files.readAllBytes(path)).isEqualTo(TEST_ARRAY);
        } finally {
            Files.delete(path);
        }
    }

    @Test
    void givenGrid_whenWriteToArray_thenArrayReturned() throws IOException {
        BufferedImage image = ImageUtils.convertGridToImage(TEST_GRID);
        assertThat(image).isNotNull();
        byte[] bytes = ImageUtils.fromImageToByteArray(image);
        assertThat(bytes).isNotNull().isEqualTo(TEST_ARRAY);
    }

    @Test
    void givenIOException_whenWriteFile_thenExceptionThrown() throws IOException {
        BufferedImage image = ImageUtils.convertGridToImage(TEST_GRID);
        assertThat(image).isNotNull();
        Path path = Files.createTempFile("test", ".png");
        try (var ioOImageWriteMock = mockStatic(ImageIO.class, CALLS_REAL_METHODS)) {
            ioOImageWriteMock
                    .when(() -> ImageIO.write(any(BufferedImage.class), anyString(), any(OutputStream.class)))
                    .thenThrow(new IOException("Mocked IO Exception"));

            assertThatThrownBy(() -> ImageUtils.fromImageToImageFile(image, path.toString()))
                    .isInstanceOf(IOException.class)
                    .hasMessage("Mocked IO Exception");
        }
    }

    @Test
    void givenWriteFailed_whenWriteFile_thenReturnFalse() throws IOException {
        BufferedImage image = ImageUtils.convertGridToImage(TEST_GRID);
        assertThat(image).isNotNull();
        Path path = Files.createTempFile("test", ".png");
        try (var ioOImageWriteMock = mockStatic(ImageIO.class, CALLS_REAL_METHODS)) {
            ioOImageWriteMock
                    .when(() -> ImageIO.write(any(BufferedImage.class), anyString(), any(OutputStream.class)))
                    .thenReturn(false);
            var result = ImageUtils.fromImageToImageFile(image, path.toString());
            assertThat(result).isFalse();
        }
    }
}
