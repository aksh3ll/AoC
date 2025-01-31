package fr.akshell.aoc.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseDay<T> {
    protected static Logger LOGGER = LoggerFactory.getLogger(BaseDay.class);

    public abstract T part1(String input);

    public abstract T part2(String input);
}
