import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileAnalyzer {
    /*
    Написати програму, яка приймає на вхід текстовий файл, і повертає наступну
    інформацію
    - Найкоротше слово і кількість його повторень в тексті. Якщо в тексті є декілька,
    однаково коротких слів, то повертати те, яке раніше по алфавіту. Наприклад,
    між словами “Олег” та “Анна” потрібно вибрати “Анна”
    - Найдовше слово і кількість його повторень в тексті. Якщо в тексті є декілька,
    однаково довгих слів, то повертати те, яке пізніше по алфавіту. Наприклад, між
    словами “Олег” та “Анна” потрібно вибрати “Олег”.
    - Регістр не враховувати
     */
    public static void main(String[] args) {
        List<String> allWords = new ArrayList<>();
        List<String> shortestWordsList = new ArrayList<>();
        List<String> longestWordsList = new ArrayList<>();
        final String path = "src/TolstoiVoinaIMir1.txt";

        try {
            FileInputStream inputStream = new FileInputStream(path);
            Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
            scanner.useDelimiter("  ");
            while (scanner.hasNext()) {
                String tempString = scanner.next();
                tempString = tempString.replaceAll("[\\[\\],.!&?\"<>'{}()*+@#$|/=:;—«»]", " ").replaceAll("[\\p{Digit}]", " ");
                StringBuilder word = new StringBuilder();
                for (int i = 0; i < tempString.length(); i++) {
                    Character ch = tempString.charAt(i);
                    if ((Character.isAlphabetic(ch)) || (ch == '-')) {
                        word.append(ch);
                    } else if (Character.isSpaceChar(ch) || word.length() >= 1) {
                        allWords.add(word.toString());
                        word.delete(0, word.length());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Integer minLengthOfWord = 1;
        getShortestWords(allWords, shortestWordsList, minLengthOfWord);
        while (shortestWordsList.isEmpty()) {
            minLengthOfWord++;
            getShortestWords(allWords, shortestWordsList, minLengthOfWord);
        }
        Collections.sort(shortestWordsList);
        int countOfShortWords = 0;
        for (int i = 0; i < shortestWordsList.size(); i++) {
            if (shortestWordsList.get(i).equals(shortestWordsList.get(0))) {
                countOfShortWords++;
            }
        }
        System.out.println("The shortest word in War And Piece Tom 1 is: \"" + shortestWordsList.get(0) + "\" it occurs " + countOfShortWords + " times");

        Integer maxLengthOfWord = 40;
        getLongestWords(allWords, longestWordsList, maxLengthOfWord);
        while (longestWordsList.isEmpty()) {
            maxLengthOfWord--;
            getLongestWords(allWords, longestWordsList, maxLengthOfWord);
        }
        int countOfLongWords = 0;
        for (int i = 0; i < longestWordsList.size(); i++) {
            if (longestWordsList.get(i).equals(longestWordsList.get(longestWordsList.size() - 1))) {
                countOfLongWords++;
            }
        }
        System.out.println("The longest word in War And Piece Tom 1 is: \"" + longestWordsList.get(longestWordsList.size() - 1) + "\" it occurs " + countOfLongWords + " times");
    }

    private static void getShortestWords(List<String> allWords, List<String> shortestWordList, Integer minLengthOfWord) {
        for (String word : allWords) {
            if ((word.length() == minLengthOfWord) && !word.equals("-") && word.length() != 0) {
                shortestWordList.add(word.toLowerCase());
            }
        }
    }

    private static void getLongestWords(List<String> allWords, List<String> longestWordList, Integer maxLengthOfWord) {
        for (String word : allWords) {
            if ((word.length() == maxLengthOfWord) && !word.equals("-") && word.length() != 0) {
                longestWordList.add(word.toLowerCase());
            }
        }
    }
}

