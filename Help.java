import java.util.*;

/**
 * Created by Tomas Polacek on 23-Jan-18.
 * TDDD95 - Ex1: Help!
 */
public class Help {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            int j_max = Integer.parseInt(scanner.nextLine());

            for (int j = 0; j < j_max; j++) {

                String[] pattern1 = scanner.nextLine().split(" ");
                String[] pattern2 = scanner.nextLine().split(" ");

                boolean foundKey = true;
                boolean foundValue;
                boolean matchable = pattern1.length == pattern2.length;

                while (matchable && foundKey) {

                    foundKey = false;
                    foundValue = false;

                    for (int i = 0; i < pattern1.length; i++) {

                        if (!isKey(pattern1[i]) || !isKey(pattern2[i])) {
                            if (!isKey(pattern1[i]) && !isKey(pattern2[i]) && !pattern1[i].equals(pattern2[i])) {
                                matchable = false;
                                break;
                            } else if (isKey(pattern1[i]) && !isKey(pattern2[i])) {
                                matchPattern(pattern1, pattern1[i], pattern2[i]);
                                foundKey = true;
                                foundValue = true;
                            } else if (!isKey(pattern1[i]) && isKey(pattern2[i])) {
                                matchPattern(pattern2, pattern2[i], pattern1[i]);
                                foundKey = true;
                                foundValue = true;
                            }
                        } else {
                            foundKey = true;
                        }

                    }

                    if (foundKey && !foundValue) {
                        for (int i = 0; i < pattern1.length; i++) {
                            if (isKey(pattern1[i])) {
                                matchPattern(pattern1, pattern1[i], extractKey(pattern1[i]));
                                break;
                            }
                        }
                    }

                }

                if (matchable) {
                    System.out.println(buildStringFromArray(pattern1));
                } else {
                    System.out.println("-");
                }

            }
        }

    }

    private static boolean isKey(String s) {
        return s.matches("<(.*)>");
    }

    private static String extractKey(String s) {
        return s.replaceAll("[<>]", "");
    }

    private static void matchPattern(String[] pattern, String key, String value) {
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i].equals(key)) {
                pattern[i] = value;
            }
        }
    }

    private static String buildStringFromArray(String[] array) {
        String builtString = "";
        for (int i = 0; i < array.length; i++) {
            if (array[i].matches("<(.*)>")) {
                builtString += "a";
            } else {
                builtString += array[i];
            }
            if (i != array.length-1) {
                builtString += " ";
            }
        }
        return builtString;
    }

}
