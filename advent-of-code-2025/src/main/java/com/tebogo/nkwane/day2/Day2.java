package com.tebogo.nkwane.day2;

import com.tebogo.nkwane.helper.FileHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Day2 {
  private Long total = 0L;
  public Long solveA() throws IOException {
    File inputs = FileHelper.readDayFile(2, "input.txt");
    // each element in the list is the start-end string
    List<String> numberRanges = Arrays.stream(Files.readAllLines(inputs.toPath()).get(0).split(",")).toList();

    // A few thoughts on invalid numbers
    // 1 they are always of the even length anything with odd length we don't need to check
    // 2 if the length is even then we only need to check the first substring against the last sub string
    // if they match we have to keep track of the sum of all of these

    numberRanges.forEach(numberRange -> {
      Long start, end;
      String[] range = numberRange.split("-");
      start = Long.parseLong(range[0]);
      end = Long.parseLong(range[1]);

      for (Long i = start; i <= end; i++) {
        // 1 - is it even or odd in length
        String numInString = i.toString();
        int lengthOfNum = numInString.length();
        if (lengthOfNum % 2 == 0) {
          Long halfWay = lengthOfNum/2L;
          String firstHalf = numInString.substring(0, Math.toIntExact(halfWay));
          String secondHalf = numInString.substring(Math.toIntExact(halfWay), lengthOfNum);

          // if they are the same we add
          if (firstHalf.equals(secondHalf)) { total += i; }
        }
      }
    });

    IO.println(total);

    return 0L;
  }

  public Long solveB() {
    // Things get switched up now
    //
    return 0L;
  }
}
