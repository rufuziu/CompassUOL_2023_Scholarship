import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
  private static final String amused = ":-)";
  private static final String annoyed = ":-(";
  private static int feelingCounter = 0;
  private static void findEmoticon(String msg, String emote) {
    int i = 0;
    int founder;
    while (true) {
      founder = msg.indexOf(emote, i);
      if (founder == -1) break;
      feelingCounter += emote.equals(amused) ? 1 : -1;
      i = founder + emote.length();
    }
  }
  private static void expressSentiment() {
    if (feelingCounter == 0) System.out.println("neutral");
    else if (feelingCounter > 0) System.out.println("fun");
    else System.out.println("upset");
    feelingCounter = 0;
  }
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(
            new File("resources\\testFile.txt"))) {
      while (scanner.hasNextLine()) {
        String msg = scanner.nextLine();
        findEmoticon(msg, amused);
        findEmoticon(msg, annoyed);
        expressSentiment();
      }
    } catch (FileNotFoundException e) {
      Scanner scanner = new Scanner(System.in);
      String msg = scanner.nextLine();
      findEmoticon(msg, amused);
      findEmoticon(msg, annoyed);
      expressSentiment();
    }
  }
}