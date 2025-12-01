package com.tebogo.nkwane.day1;

import com.tebogo.nkwane.helper.FileHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day1 {
  Integer current = 50;
  Integer zeros = 0;
  static final String L = "L";
  static final String R = "R";
  public Integer solveA() throws IOException {
    File inputs = FileHelper.readDayFile(1, "input.txt");
    List<String> inputsIterator = Files.readAllLines(inputs.toPath());

    inputsIterator.forEach(input -> {

      if(input.contains(L)) {
        Integer currentTransform = Integer.parseInt(input.replace(L, ""));
        current = lowerBound(current, currentTransform);
      }

      if(input.contains(R)) {
        Integer currentTransform = Integer.parseInt(input.replace(R, ""));
        current = upperBound(current, currentTransform);
      }
    });

    IO.println("zeros: %s".formatted(zeros));
    return current;
  }

  Integer lowerBound(Integer currentValue, Integer currentTransform) {
    // takes in a current total and handle L calculations
    Integer nextValue = currentValue - currentTransform;
    if(nextValue >= 0 && nextValue < 100) {
      if(nextValue == 0) { zeros++; }
      return nextValue;
    }
    while(nextValue < 0) {
      nextValue = nextValue + 100;
    }
    if(nextValue == 0) { zeros++; }
    return nextValue;
  }

  Integer upperBound(Integer currentValue, Integer currentTransform) {
    // takes in a current total and handle R calculations
    Integer nextValue = currentValue + currentTransform;
    if(nextValue >= 0 && nextValue < 100) { if(nextValue == 0) { zeros++; } return nextValue;}
    while(nextValue > 99) {
      nextValue = nextValue - 100;
    }
    if(nextValue == 0) { zeros++; }
    return nextValue;
  }

  public Integer solveB() throws IOException {
    File inputs = FileHelper.readDayFile(1, "input.txt");
    List<String> inputsIterator = Files.readAllLines(inputs.toPath());

    inputsIterator.forEach(input -> {

      if(input.contains(L)) {
        Integer currentTransform = Integer.parseInt(input.replace(L, ""));
        current = lowerBoundRepeatedZero(current, currentTransform);
      }

      if(input.contains(R)) {
        Integer currentTransform = Integer.parseInt(input.replace(R, ""));
        current = upperBoundRepeatedZero(current, currentTransform);
      }
    });

    IO.println("zeros: %s".formatted(zeros));
    return current;
  }

  // switch up
  private Integer upperBoundRepeatedZero(Integer currentValue, Integer currentTransform) {
    int pos = currentValue;

    for (int i = 0; i < currentTransform; i++) {
      pos++;               // one click to the right
      if (pos > 99) {
        pos = 0;           // wrap
      }
      if (pos == 0) {
        zeros++;           // every click that lands on 0
      }
    }

    return pos;
  }

  private Integer lowerBoundRepeatedZero(Integer currentValue, Integer currentTransform) {
    int pos = currentValue;

    for (int i = 0; i < currentTransform; i++) {
      pos--;               // one click to the left
      if (pos < 0) {
        pos = 99;          // wrap
      }
      if (pos == 0) {
        zeros++;           // every click that lands on 0
      }
    }

    return pos;
  }

}
