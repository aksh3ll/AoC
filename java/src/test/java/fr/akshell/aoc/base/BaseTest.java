package fr.akshell.aoc.base;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class BaseTest {
    private final int year;
    private final int day;

    public BaseTest(int year, int day) {
        this.year = year;
        this.day = day;
    }

    private String getInput(int year, int day) throws IOException, InterruptedException {
        URI uri = URI.create("https://raw.githubusercontent.com/aksh3ll/AoC/refs/heads/main/" + year + "/day" + day + ".input.txt");
        HttpRequest request = HttpRequest.newBuilder(uri).build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String getFinalInput() throws IOException, InterruptedException {
        return getInput(year, day);
    }

    @Test
    public abstract void givenDemoInput1_whenRunningPart1_thenExpectedResultIsFound();

    @Test
    public abstract void givenDemoInput1_whenRunningPart2_thenExpectedResultIsFound();


    @Test
    public abstract void givenFinalInput_whenRunningPart1_thenExpectedResultIsFound() throws IOException, InterruptedException;

    @Test
    public abstract void givenFinalInput_whenRunningPart2_thenExpectedResultIsFound() throws IOException, InterruptedException;
}
