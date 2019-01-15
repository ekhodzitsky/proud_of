package csssr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * There is a string consisting of words.
 * All words in it are separated by one space.
 * You need to convert the string into a data structure that groups words by the first letter of the word.
 * Then output only the groups containing more than one element.
 * Groups should be sorted alphabetically by all letters in the word.
 * Words within the group should be sorted in descending order (by the number of characters);
 * if the number of characters is equal, then sort in alphabetical order.
 *
 * @author Evgeny Khodzitskiy (m1ndcoderr@gmail.com)
 * @since 02.02.2018
 */
public class StringHandler {
    /**
     * Main method to pass the text quest of csssr.ru
     * Transforms a string in a specific way.
     *
     * @param s - string to transform.
     * @return string result.
     */
    public String transform(String s) {
        String result;
        List<String> words = new ArrayList<>();
        List<ArrayList<String>> divided;
        Collections.addAll(words, Pattern.compile("\\s+")
                .splitAsStream(s).sorted().collect(Collectors.joining(" ")).split(" "));
        removeSingleWords(words);
        if (!words.isEmpty()) {
            divided = divide(words);
            sort(divided);
            result = stringArrayOfLists(divided);
        } else {
            result = "";
        }
        return result;
    }

    /**
     * Arranges the necessary punctuation.
     *
     * @param lists list of lists to operate with.
     * @return string result.
     */
    private String stringArrayOfLists(List<ArrayList<String>> lists) {
        String result = "[";
        for (int i = 0; i != lists.size() - 1; i++) {
            result = result + lists.get(i).get(0).charAt(0) + "=" + stringList(lists.get(i)) + "],";
        }
        result = result + lists.get(lists.size() - 1).get(0).charAt(0)
                + "=" + stringList(lists.get(lists.size() - 1)) + "]]";
        return result;
    }

    /**
     * Makes square brackets inside an array.
     *
     * @param list list to operate with.
     * @return string result.
     */
    private String stringList(ArrayList<String> list) {
        String result = "[";
        for (int i = 0; i != list.size() - 1; i++) {
            result = result + list.get(i) + ", ";
        }
        result = result + list.get(list.size() - 1);
        return result;
    }

    /**
     * Sorts words by length.
     *
     * @param list list to sort.
     */
    private void sort(List<ArrayList<String>> list) {
        for (ArrayList<String> array : list) {
            Collections.sort(array, (o1, o2) -> o2.length() - o1.length());
        }
    }

    /**
     * Divides words into arrays by first letter of each word.
     *
     * @param list list to be divided.
     * @return array with lists of grouped words.
     */
    private List<ArrayList<String>> divide(List<String> list) {
        List<ArrayList<String>> lists = new ArrayList<>(list.size());
        lists.add(new ArrayList<>());
        lists.get(0).add(list.get(0));
        for (int i = 1; i != list.size(); i++) {
            String prev = list.get(i - 1);
            String curr = list.get(i);
            if (prev.charAt(0) == curr.charAt(0)) {
                lists.get(lists.size() - 1).add(curr);
            } else {
                lists.add(new ArrayList<>());
                lists.get(lists.size() - 1).add(curr);
            }
        }
        return lists;
    }

    /**
     * Removes single words that start with a certain letter.
     *
     * @param list list with words.
     */
    private void removeSingleWords(List<String> list) {
        List<String> singles = new ArrayList<>();
        for (int i = 1; i != list.size() - 1; i++) {
            char prev = list.get(i - 1).charAt(0);
            char curr = list.get(i).charAt(0);
            char next = list.get(i + 1).charAt(0);
            if (curr != prev && curr != next) {
                singles.add(list.get(i));
            }
        }
        if (list.get(0).charAt(0) != list.get(1).charAt(0)) {
            singles.add(list.get(0));
        }
        if (list.get(list.size() - 1).charAt(0) != list.get(list.size() - 2).charAt(0)) {
            singles.add(list.get(list.size() - 1));
        }
        list.removeAll(singles);
    }
}
