package csssr;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
 * @since 03.02.2018
 */
public class StringHandlerTest {
    /**
     * A group of words.
     */
    private static final String FIRST = "сапог сарай арбуз болт бокс биржа";
    /**
     * A group of words.
     */
    private static final String SECOND = "Пони пани пончик пальчик молоко ухо мальчик мандарин";
    /**
     * A group of words.
     */
    private static final String THIRD = "дивный давний перец давай давно добро воскресенье василий";
    /**
     * A group of words.
     */
    private static final String FOURTH = "ухо горло нос кератин протеин яд халеный";
    /**
     * A group of words.
     */
    private static final String FIFTH = "один овал огурец оставаться ост-индийский ответ";

    /**
     * Our string operator.
     */
    private static final StringHandler sh = new StringHandler();

    /**
     * Passing quest.
     */
    @Test
    public void transformFirst() {
        assertThat(sh.transform(FIRST), is("[б=[биржа, бокс, болт],с=[сапог, сарай]]"));
    }

    /**
     * Sorts a group of words.
     */
    @Test
    public void transformSecond() {
        assertThat(sh.transform(SECOND), is("[м=[мандарин, мальчик, молоко],п=[пальчик, пончик, пани]]"));
    }

    /**
     * Sorts a group of words.
     */
    @Test
    public void transformThird() {
        assertThat(sh.transform(THIRD), is("[в=[воскресенье, василий],д=[давний, дивный, давай, давно, добро]]"));
    }

    /**
     * All words will be deleted. There is nothing to return.
     */
    @Test
    public void transformFourth() {
        assertThat(sh.transform(FOURTH), is(""));
    }

    /**
     * Returns just one group of words which begins from one letter "o".
     */
    @Test
    public void transformFifth() {
        assertThat(sh.transform(FIFTH), is("[о=[ост-индийский, оставаться, огурец, ответ, овал, один]]"));
    }

}
