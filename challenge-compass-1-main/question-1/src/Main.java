import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Main {
  private static String name;
  private static int numbProb;
  private static void resetVars() {
    name = "";
    numbProb = 0;
  }
  private static boolean checkInput(String input) {
    name = input.replaceAll("[^0-9]", "");
    if (name.length() <= 2) {
      try {
        numbProb =
                Integer.parseInt(input.replaceAll("[^0-9]", ""));
      } catch (Exception e) {
        resetVars();
        return false;
      }
      if (numbProb > 0 && numbProb <= 10) {
        name = input.replaceAll("[a-z]|[0-9]| ","");
        if(!name.isEmpty()){
          resetVars();
          return false;
        }
        else{
          name = input.replaceAll("[^a-z]", "");
          return !name.isEmpty() && name.length() <= 20;
        }
      }
    } else {
      resetVars();
      return false;
    }
    return false;
  }
  private static void unfortunateStudent(List<Student> students) {
    Collections.sort(students);
    System.out.print("Instancia ");
    System.out.print(students.get(0).getId());
    System.out.println();
    System.out.print(students.get(0).getName());
    System.out.print(" ");
    System.out.print(students.get(0).getProblemsSolved());
  }
  public static void main(String[] args) {
    List<Student> students = new ArrayList<>();
    String input;
    int x;
    try (Scanner scanner = new Scanner(
            new File("resources\\testFile.txt"))) {
      while (scanner.hasNextLine()) {
        try {
          x = Integer.parseInt(scanner.nextLine());
          if (x >= 1 && x <= 100) {
            while (x != 0) {
              input = scanner.nextLine().trim();
              if (checkInput(input)) {
                students.add(new Student(name, numbProb));
                resetVars();
                x--;
              }
            }
            unfortunateStudent(students);
          }
        } catch (Exception ex) {}
      }
    } catch (FileNotFoundException e) {
      Scanner scanner = new Scanner(System.in);
      while (true) {
        try {
          x = Integer.parseInt(scanner.nextLine());
          if (x >= 1 && x <= 100) {
            while (x != 0) {
              input = scanner.nextLine().trim();
              if (checkInput(input)) {
                students.add(new Student(name, numbProb));
                resetVars();
                x--;
              }
            }
            unfortunateStudent(students);
            break;
          }
        } catch (Exception ex) {}
      }
    }
  }
}