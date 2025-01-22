package fr.akshell.aoc.base;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseTest<T> {
    private final int year;
    private final int day;
    BaseDay<T> dayInstance;
    private final String inputDemo1;
    private final T inputDemo1Part1Result;
    private final T inputDemo1Part2Result;
    private final T finalPart1Result;
    private final T finalPart2Result;

    public BaseTest(int year, int day, BaseDay<T> dayInstence, String inputDemo1, T inputDemo1Part1Result, T inputDemo1Part2Result,
                    T finalPart1Result, T finalPart2Result) {
        this.year = year;
        this.day = day;
        this.dayInstance = dayInstence;
        this.inputDemo1 = inputDemo1;
        this.inputDemo1Part1Result = inputDemo1Part1Result;
        this.inputDemo1Part2Result = inputDemo1Part2Result;
        this.finalPart1Result = finalPart1Result;
        this.finalPart2Result = finalPart2Result;
    }

    public BaseDay<T> getDay() {
        return dayInstance;
    }

    private String getInput(int year, int day) {
        String url = "https://raw.githubusercontent.com/aksh3ll/AoC/refs/heads/main/" + year + "/day" + day + ".input.txt";
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(uri).build();
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Input not found for year " + year + " and day " + day);
        }
    }

    public String getFinalInput() {
        return getInput(year, day);
    }

    @Test
    public void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(dayInstance.part1(inputDemo1)).isEqualTo(inputDemo1Part1Result);
    }

    @Test
    public void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(dayInstance.part2(inputDemo1)).isEqualTo(inputDemo1Part2Result);
    }

    @Test
    public void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() {
        assertThat(dayInstance.part1(getFinalInput())).isEqualTo(finalPart1Result);
    }

    @Test
    public void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(dayInstance.part2(getFinalInput())).isEqualTo(finalPart2Result);
    }
}
