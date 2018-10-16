package ass03;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TextUtils {

    private final static String REGEX = "\\W+";

    /*
        Questa funzione, dato un testo, determina la lista delle parole e la relativa lunghezza
        (la classe WordLen fornita nel materiale è utile per rappresentare la tupla <parola, lunghezza della parola>)
     */
	static List<WordLen> getWordsLength(String text) {

		List<WordLen> wordLenList = new ArrayList<>();
		List<String> words = Arrays.asList(text.split(REGEX));
		words.forEach(w -> wordLenList.add(new WordLen(w,w.length())));
		return wordLenList;
	}

    //Questa funzione, dato un testo, determina la parola di lunghezza maggiore.
	static Optional<String> getWordWithMaxLen(String text){

        List<String> words = Arrays.asList(text.split(REGEX));
        //risolto con stream e la funzione "max" abbinata ad un "comparator" sulla lunghezza delle stringhe
        return words.stream().max(comparing(String::length));
	}

    /*
        Questa funzione, dato un testo e una parola, determina la frequenza (numero occorrenze) della parola nel testo specificato.
     */
	static int getWordFreq(String text, String word){

        List<String> words = Arrays.asList(text.split(REGEX));
        //risolto con filtro + lambda per identificare le parole uguali e tramite count trovo la frequenza
		return (int) words.stream().filter(w -> w.equals(word)).count();
	}

    /*
        Questa funzione determina, dato un testo, per ogni parola distinta presente nel testo,
        la lista delle posizioni in cui essa compare. Si assuma come prima posizione la numero 1).
        WordPos è una interfaccia fornita nel materiale.
     */
	static List<WordPos> getWordsPos(String text){

        List<WordPos> wordPosList = new ArrayList<>();
        List<String> words = Arrays.asList(text.split(REGEX));

        //utilizzo distinct per eliminare i duplicati nel testo e ciclare
        words.stream().distinct().forEach(w -> wordPosList.add(new WordPos() {
            //implemento l'interfaccia WordPos
            @Override
            public String getWord() {
                //collego alla nuova istanza la parola w che sto aggiungendo
                return w;
            }

            @Override
            public List<Integer> getPos() {
                //Creo uno IntStream che itera TUTTE le parole nel testo e memorizza tutte le posizioni dove è presente una parola uguale a 'w'
                return IntStream.range(0, words.size()).filter(i -> words.get(i).equals(w)).map(i -> i + 1).boxed().collect(toList());
            }
        }));

		return wordPosList;
	}
	
}