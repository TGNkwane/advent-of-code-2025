package com.tebogo.nkwane.helper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class FileHelper {
  private static final int YEAR = 2025;
  private static final String INPUT_FILE = "input";

  private FileHelper() {} // Utility class

  public static File readDayFile(int day, String fileName) {
    String directory = "src/main/resources/inputs/" + YEAR + "/day" + day;
    File file = new File(directory + "/" + fileName);

    if (!file.exists() && INPUT_FILE.equals(fileName)) {
      new File(directory).mkdirs();
      downloadInputFile(day, file);
    }

    return file;
  }

  private static void downloadInputFile(int day, File file) {
    try {
      String cookie = java.nio.file.Files.readString(new File("aoc_cookie").toPath());

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create("https://adventofcode.com/" + YEAR + "/day/" + day + "/input"))
          .header("Cookie", cookie)
          .header("accept", "text/plain")
          .GET()
          .build();

      HttpClient client = HttpClient.newHttpClient();

      HttpResponse<Path> response =
          client.send(request, HttpResponse.BodyHandlers.ofFile(file.toPath()));

      if (response.statusCode() != 200) {
        file.delete();
        throw new IllegalStateException(
            "Failed to download input file: HTTP " + response.statusCode());
      }

    } catch (IOException | InterruptedException e) {
      file.delete();
      throw new RuntimeException("Error downloading input file", e);
    }
  }
}
