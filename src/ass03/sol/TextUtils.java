package ass03.sol;

import ass03.WordLen;
import ass03.WordPos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;

public class TextUtils {

	static List<WordLen> getWordsLength(String text) {
		return Arrays.asList(toWords(text)).stream()
				.map(x -> new WordLen(x,x.length()))
				.collect(toList());
	};
	
	static Optional<String> getWordWithMaxLen(String text){
		return Arrays.asList(toWords(text)).stream()
				.reduce((s1,s2) -> { 
					if (s1.length() < s2.length()) { 
						return s2; 
					} else {
						return s1; 
					}
				});
	}

	static long getWordFreq(String text, String word){
		return Arrays.asList(toWords(text)).stream()
				.filter( s -> s.equals(word))
				.count();
	}

	static List<WordPos> getWordsPos(String text){
		List<String> words = Arrays.asList(toWords(text)); 
		HashMap<String,WordPos> wordsMap = new HashMap<String,WordPos>();
		Counter c = new Counter();
		words.stream()
			.forEach(s -> {
				c.inc();
				WordPos pos = wordsMap.get(s);
				if (pos == null){
					wordsMap.put(s, new WordPosImpl(s,c.getValue()));
				} else {
					pos.getPos().add(c.getValue());
				}
			});
		return new ArrayList<WordPos>(wordsMap.values());
	}
	
	static private String[] toWords(String text){
		return text.split(" ");
	}

	/* private class used in the implementation */
	
	static class Counter {
		private int count;
		
		public Counter(){
			count = 0;
		}
		
		public void inc(){
			count++;
		}
		
		public int getValue(){
			return count;
		}
	}
	
	static class WordPosImpl implements WordPos {
		private String word;
		private List<Integer> pos;
		
		protected WordPosImpl(String word, int p){
			this.word = word;
			this.pos = new LinkedList<Integer>();
			pos.add(p);
		}
		
		@Override
		public String getWord() {
			return word;
		}

		@Override
		public List<Integer> getPos() {
			return pos;
		}
		
		public String toString(){
			return "{ word: "+word+" pos: "+pos+" }";
		}
	}
		
}
