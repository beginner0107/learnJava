package sec12.chap07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

  public static final String SERVER_IP = "127.0.0.1";

  public static final int PORT_NO = 1234;
  public static void main(String[] args) {
    try(
        ServerSocket serverSkt = new ServerSocket(PORT_NO);
        ) {
      while(true) {
        try(
            Socket clientSkt = serverSkt.accept();

            InputStream is = clientSkt.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringWriter sw = new StringWriter();
            PrintWriter piw = new PrintWriter(sw);

            OutputStream os = clientSkt.getOutputStream();
            PrintWriter pow = new PrintWriter(os, true);
            ) {
            String line;
            int lineCount = 1;
            while((line = br.readLine()) != null) {
              piw.printf(
                  "%3d : %s%n".formatted(
                      lineCount++, line
                  )
              );
              pow.printf("✅ 수신: %s... 등 %d자%n".formatted(
                  line.substring(
                      0, Math.min(3, line.length())

                  ), line.length()
              ));
            }
          System.out.println(sw);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
