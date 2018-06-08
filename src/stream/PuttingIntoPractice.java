package stream;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice{
    public static void main(String ...args){    

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

		List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300), 
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),	
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );	
        
        
        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).

        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);
        System.out.println("------------------------------------");
        // Query 2: What are all the unique cities where the traders work?
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
        System.out.println("------------------------------------");

        // Query 3: Find all traders from Cambridge and sort them by name.
        transactions.stream()
                        .map(t -> t.getTrader())
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(Comparator.comparing(Trader::getName))
                        .forEach(System.out::println);
        System.out.println("------------------------------------");
        
        // Query 4: Return a string of all traders’ names sorted alphabetically.
        System.out.println(transactions.stream().map( t -> t.getTrader().getName())
                .distinct().sorted()
                .reduce("",(s1,s2) -> s1 + s2));

        System.out.println("------------------------------------");

        
        // Query 5: Are there any trader based in Milan?
        System.out.println(transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan")));
        System.out.println("------------------------------------");
        
        
        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        transactions.stream().map(t -> t.getTrader())
                .filter(trader -> trader.getCity().equals("Milan"))
                .forEach(trader -> trader.setCity("Cambridge"));

        System.out.println(transactions);
        System.out.println("------------------------------------");
        // Query 7: What's the highest value in all the transactions?
        // stream -> IntStream변환해서 max()함수를 사용
        System.out.println(transactions.stream()
                            .mapToInt(Transaction::getValue) //IntStream<Integer>
                            .max() // OptionalInt
                            .getAsInt());


    }
}