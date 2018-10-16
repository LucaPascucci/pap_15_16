package ass03;

import java.util.List;
import java.util.Optional;

public class TextUtilTest {

    public static void main(String[] args) {

        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut" +
                " labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi" +
                " ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum" +
                " dolore eu fugiat nulla pariatur.";

        String word = "in";

        System.out.println("Words Length: ");
        List<WordLen> wordLenList = TextUtils.getWordsLength(text);
        wordLenList.forEach(wl -> System.out.println("Word: " + wl.getWord() + " -> length: " +  wl.getLength()));

        Optional<String> longest_word = TextUtils.getWordWithMaxLen(text);
        System.out.println("\nLongest word: " + (longest_word.isPresent()? longest_word.get() : "Nothing"));

        System.out.println("\n" + word + " -> occurrence: " + TextUtils.getWordFreq(text, word));

        System.out.println("\nWords Pos: ");
        List<WordPos> wordPosList = TextUtils.getWordsPos(text);
        wordPosList.forEach(wp -> System.out.println("Word: " +  wp.getWord() + " -> Pos: " + wp.getPos()));

    }
}
