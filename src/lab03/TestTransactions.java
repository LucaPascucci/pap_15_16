package lab03;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by Luca on 02/04/16.
 */
public class TestTransactions {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));


        //Compute the set of transactions in 2011, sorted by value
        List<Transaction> transactions_2011 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        //Compute the set of all distinct cities where traders work
        List<String> cites = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());

        //Traders coming from Cambridge, sorted by name
        List<Trader> Cambridge_traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        //Compute the string including the name of all the treaders, sorted
        String names = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (a,b) -> a + " "+ b);

        //Check for traders from Milan
        boolean milanBased = transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        //Printing all values about transactions from traders living in Cambridge
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("milan"))
                .forEach(t -> System.out.println(t.getValue()));

        //Compute the max value of all transactions
        Optional<Integer> maxValue = transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::max);

        //Find the transaction with the min value
        Optional<Transaction> minTrans = transactions.stream()
                .reduce((t1,t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        Optional<Transaction> smallest = transactions.stream()
                .min(comparing(Transaction::getValue));
    }
}
