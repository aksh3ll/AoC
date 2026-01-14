package fr.akshell.aoc.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.PuzzleInputException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

class MiscUtilsTest {

    @Test
    void givenString_whenIsNumber_thenTypeChecked() {
        assertThat(MiscUtils.isNumber("123")).isTrue();
        assertThat(MiscUtils.isNumber("123.0")).isFalse();
        assertThat(MiscUtils.isNumber("abc")).isFalse();
        assertThat(MiscUtils.isNumber("1.23E2")).isFalse();
        assertThat(MiscUtils.isNumber(null)).isFalse();
        assertThat(MiscUtils.isNumber("")).isFalse();
    }

    @Test
    void givenComplexObject_whenDeepCopy_thenTotallyNewObjectReturned() {
        Maze literal = Maze.of("abcd\nbcde\ncdef\ndefg");
        Maze copy = MiscUtils.deepCopy(literal);
        assertThat(copy).isNotSameAs(literal).isEqualTo(literal);
    }

    @Test
    void givenBrokenObject_whenDeepCopy_thenExceptionIsReturned() {
        try (var _ = mockConstruction(ObjectInputStream.class,
                (mock, _) -> when(mock.readObject()).thenThrow(new IOException()))) {
            Maze literal = Maze.of("abcd\nbcde\ncdef\ndefg");
            assertThatThrownBy(() -> MiscUtils.deepCopy(literal)).isInstanceOf(MiscUtils.DeepCopyException.class);
        }
    }

    @Test
    void givenString_whenDecodeHex_thenDecodedStringReturned() {
        assertThat(MiscUtils.decodeHex("a\\x20b")).isEqualTo("a b");
        assertThat(MiscUtils.decodeHex("a\\x20b\\x20c")).isEqualTo("a b c");
        assertThat(MiscUtils.decodeHex("a\\x20b\\x20c\\x20d")).isEqualTo("a b c d");
        assertThat(MiscUtils.decodeHex("abc")).isEqualTo("abc");
        assertThat(MiscUtils.decodeHex("aaa\\\"aaa")).isEqualTo("aaa\"aaa");
        assertThat(MiscUtils.decodeHex("\\x27")).isEqualTo("'");
        assertThat(MiscUtils.decodeHex("\\n\\r\\t\\b\\f\\z\\")).isEqualTo("\n\r\t\b\f\\z\\");
        assertThat(MiscUtils.decodeHex("x\\\"\\xcaj\\\\xwwvpdldz")).isEqualTo("x\"Êj\\xwwvpdldz");
    }

    @Test
    @SuppressWarnings("unchecked")
    void givenUrl_whenReadFromWebsite_thenContentIsReturned() throws IOException, InterruptedException {
        // Given
        String url = "https://raw.githubusercontent.com/aksh3ll/AoC/refs/heads/main/2024/day16.input.txt";
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);
        HttpRequest request = mock(HttpRequest.class);
        HttpClient httpClientMock = mock(HttpClient.class);
        HttpResponse<String> response = mock(HttpResponse.class);
        try (MockedStatic<HttpRequest> httpRequestStaticMock = mockStatic(HttpRequest.class);
                MockedStatic<HttpClient> httpClientStaticMock = mockStatic(HttpClient.class)) {
            httpRequestStaticMock.when(() -> HttpRequest.newBuilder(any(URI.class))).thenReturn(builder);
            doReturn(request).when(builder).build();
            httpClientStaticMock.when(HttpClient::newHttpClient).thenReturn(httpClientMock);
            when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                    .thenReturn(response);
            when(response.body()).thenReturn("final input line");

            // When
            String content = MiscUtils.readFromWebsite(url);

            // Then
            assertThat(content).isNotNull().isNotEmpty();
            assertThat(content.lines()).hasSize(1);
        }
    }

    @ParameterizedTest
    @ValueSource(classes = {IOException.class, InterruptedException.class})
    @SuppressWarnings("unchecked")
    void givenException_whenReadFromWebsite_thenThrowPuzzleInputException(Class<Exception> thrownException)
            throws IOException, InterruptedException {
        // Given
        String url = "https://raw.githubusercontent.com/aksh3ll/AoC/refs/heads/main/2024/day16.input.txt";
        HttpRequest.Builder builder = mock(HttpRequest.Builder.class);
        HttpRequest request = mock(HttpRequest.class);
        HttpClient httpClientMock = mock(HttpClient.class);
        try (MockedStatic<HttpRequest> httpRequestStaticMock = mockStatic(HttpRequest.class);
             MockedStatic<HttpClient> httpClientStaticMock = mockStatic(HttpClient.class)) {
            httpRequestStaticMock.when(() -> HttpRequest.newBuilder(any(URI.class))).thenReturn(builder);
            doReturn(request).when(builder).build();
            httpClientStaticMock.when(HttpClient::newHttpClient).thenReturn(httpClientMock);
            when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                    .thenThrow(thrownException);

            // When & Then
            assertThatThrownBy(() -> MiscUtils.readFromWebsite(url))
                    .isInstanceOf(PuzzleInputException.class)
                    .hasMessageContaining("Failed to read from website");
        }
    }
}
