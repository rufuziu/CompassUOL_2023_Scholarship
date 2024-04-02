import java.util.*;

public class Calculator {
  private static int n = 1;
  private static final LinkedHashMap<String, Integer> memory = new LinkedHashMap<>();

  public static void Calculate(int M, String op) {
    String str = op.replaceAll("[0-9]|[+]|-", "");

    if (str.isEmpty()) {
      op = op.replaceAll(" ", "");
      str = op.replaceAll("[0-9]", "");
      int numOperators = str.length();

      str = op.replaceAll("[+]", " ");
      str = str.replaceAll("-", " -").trim();
      String[] numbers = str.split(" ");

      if (numOperators < numbers.length && M == numbers.length) {
        List<Integer> numbList = new ArrayList<>();
        for (String n : numbers) {
          int num = Integer.parseInt(n);
          numbList.add(num);
        }

        int maxPositive = Collections.max(numbList);
        int minNegative = Collections.min(numbList);

        if (maxPositive <= 100 && minNegative >= -100) {
          int result = numbList.stream().mapToInt(i->i).sum();
          memory.put("Test " + n, result);
          n++;
        }
      }
    }
  }

  public static void showMemory() {
    if (!memory.isEmpty()) {
      for (Map.Entry<String, Integer> entry : memory.entrySet()) {
        System.out.println(entry.getKey());
        System.out.println(entry.getValue());
        System.out.println();
      }
    }
  }
}
