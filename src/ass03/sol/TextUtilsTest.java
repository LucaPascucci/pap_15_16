package ass03.sol;

import java.util.Optional;

public class TextUtilsTest {
	public static void main(String[] args) {
		String text = "aa bb aa b ccc b dddd ccc aa";

		TextUtils.getWordsLength(text).forEach(System.out::println);
	
		Optional<String> max1 = TextUtils.getWordWithMaxLen("   ");
		System.out.println(max1);

		Optional<String> max2 = TextUtils.getWordWithMaxLen(text);
		System.out.println(max2.get());
		
		System.out.println(TextUtils.getWordFreq(text, "xxx"));
		System.out.println(TextUtils.getWordFreq(text, "aa"));
		
		TextUtils.getWordsPos("   ").forEach(System.out::println);
		TextUtils.getWordsPos(text).forEach(System.out::println);
	}
	

}
