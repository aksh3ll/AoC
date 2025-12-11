package fr.akshell.aoc.base;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class BaseTest<T> {
    protected static final boolean DUMMY = true;
    protected static Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private final int year;
    private final int day;
    BaseDay<T> dayInstance;
    private final String inputDemo1;
    private final String inputDemo2;
    private final T inputDemo1Part1Result;
    private final T inputDemo2Part2Result;
    private final T finalPart1Result;
    private final T finalPart2Result;

    public BaseTest(int year, int day, BaseDay<T> dayInstance, String inputDemo1, T inputDemo1Part1Result,
                    String inputDemo2, T inputDemo2Part2Result, T finalPart1Result, T finalPart2Result) {
        this.year = year;
        this.day = day;
        this.dayInstance = dayInstance;
        this.inputDemo1 = inputDemo1;
        this.inputDemo2 = inputDemo2;
        this.inputDemo1Part1Result = inputDemo1Part1Result;
        this.inputDemo2Part2Result = inputDemo2Part2Result;
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
            logger.error("Error while fetching input from {}", url, e);
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
    public void givenDemoInput2_whenRunningPart2_thenExpectedResultIsFound() {
        assertThat(dayInstance.part2(inputDemo2)).isEqualTo(inputDemo2Part2Result);
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
