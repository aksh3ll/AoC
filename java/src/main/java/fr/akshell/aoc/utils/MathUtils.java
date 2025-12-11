package fr.akshell.aoc.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@UtilityClass
public class MathUtils {

    public static class Combinations<T> implements Iterator<List<T>> {
        private final List<T> elements;
        private final int[] indices;

        public Combinations(List<T> elements, int k) {
            this.elements = elements;
            this.indices = new int[k];
        }

        @Override
        public boolean hasNext() {
            return indices[0] < elements.size();
        }

        @Override
        public List<T> next() {
            if (indices[0] >= elements.size()) {
                throw new NoSuchElementException();
            }
            List<T> parts = new ArrayList<>();
            for (int index : indices) {
                parts.add(elements.get(index));
            }
            for (int pos = indices.length - 1; pos >= 0; pos--) {
                indices[pos]++;
                if (pos > 0 && indices[pos] >= elements.size()) {
                    indices[pos] = 0;
                } else {
                    break;
                }
            }
            return parts;
        }
    }
}
