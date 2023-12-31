package sec12.chap06;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ex04 {

  public static void main(String[] args) throws IOException {
    URL yalco = new URL("https://showcases.yalco.kr");
    URL people = new URL(yalco, "/java/people.csv");
    String PEOPLE_PATH = "src/sec12/chap06/people.txt";

    HttpURLConnection conn = (HttpURLConnection) people.openConnection();

    int responseCode = conn.getResponseCode();
    System.out.println("Response Code :" + responseCode);

    try (
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        FileWriter fw = new FileWriter(PEOPLE_PATH);
        PrintWriter pw = new PrintWriter(fw)
    ) {
      String line;
      while((line = br.readLine()) != null) {
        try {
          String[] pieces = line.split(",");
          pw.println(
              new Person(
                  pieces[0],
                  Integer.parseInt(pieces[1]),
                  Double.parseDouble(pieces[2]),
                  Boolean.parseBoolean(pieces[3])
              )
          );
        } catch (RuntimeException e) {
          System.out.println("😵 처리 실패: " + line);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
