package com.robin.homework.q1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * - Collections:
 *
 * Write a java merge class that will merge two sorted collections of the same kind into a single sorted collection.
 * You need to think of how to design this class in a generic form and efficiently.
 */
public class Merge2List {

  public static <T> List<T> merge(List<T> foo, List<T> bar, BiPredicate<T, T> compare) {

    int fooLength = foo.size();
    int barLength = bar.size();

    List<T> merged = new ArrayList<>();

    int fooPosition, barPosition;
    fooPosition = barPosition = 0;

    while(fooPosition < fooLength && barPosition < barLength) {
      if (compare.test(foo.get(fooPosition), bar.get(barPosition))) {
        merged.add(foo.get(fooPosition++));
      } else {
        merged.add(bar.get(barPosition++));
      }
    }

    while (fooPosition < fooLength) {
      merged.add(foo.get(fooPosition++));
    }

    while (barPosition < barLength) {
      merged.add(bar.get(barPosition++));
    }

    return merged;
  }
}
