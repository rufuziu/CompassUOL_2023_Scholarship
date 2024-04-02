import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(new File("resources\\testFile.txt"))) {
      while (scanner.hasNextLine()) {
        try {
          int m = Integer.parseInt(scanner.nextLine());
          if (m == 0) break;
          else if (!(m < 0) && !(m > 100)) {
            String op = scanner.nextLine();
            Calculator.Calculate(m, op);
          }
        } catch (Exception e) {
        }
      }
      Calculator.showMemory();
    } catch (FileNotFoundException e) {
      Scanner scanner = new Scanner(System.in);
      while (true) {
        try {
          int m = Integer.parseInt(scanner.nextLine());
          if (m == 0) break;
          else if (!(m < 0) && !(m > 100)) {
            String op = scanner.nextLine();
            Calculator.Calculate(m, op);
          }
        } catch (Exception ex) {}
      }
      Calculator.showMemory();
    }
  }
}