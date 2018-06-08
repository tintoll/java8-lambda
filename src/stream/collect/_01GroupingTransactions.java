package stream.collect;

import java.util.*;

import static java.util.stream.Collectors.*;

public class _01GroupingTransactions {

    public static List<Transaction> transactions = 
    		Arrays.asList(new Transaction(Currency.EUR, 1500.0),
                          new Transaction(Currency.USD, 2300.0),
                          new Transaction(Currency.GBP, 9900.0),
                          new Transaction(Currency.EUR, 1100.0),
                          new Transaction(Currency.JPY, 7800.0),
                          new Transaction(Currency.CHF, 6700.0),
                          new Transaction(Currency.EUR, 5600.0),
                          new Transaction(Currency.USD, 4500.0),
                          new Transaction(Currency.CHF, 3400.0),
                          new Transaction(Currency.GBP, 3200.0),
                          new Transaction(Currency.USD, 4600.0),
                          new Transaction(Currency.JPY, 5700.0),
                          new Transaction(Currency.EUR, 6800.0) );

    public static void main(String ... args) {
        // 1. Currency 별로 그룹핑
        groupImperatively();
        groupFunctionally();

        // 2. Currency 별로 그룹핑하고, 해당 트랜잭션의 값을 합산
        // groupingBy(currency, summingDouble())
        System.out.println(transactions.stream()
                            .collect( groupingBy(Transaction::getCurrency,
                                                summingDouble(Transaction::getValue)) ) );


        // 3. Currency 별로 그룹핑하고, 해당 트랜잭션의 값이 5000초과인 경우를 구분해서 분류하기
        // groupingBy(currency, partitioningBy())
        System.out.println(transactions.stream()
                .collect( groupingBy(Transaction::getCurrency,
                        partitioningBy(t -> t.getValue() > 5000)) ) );

    }
    //Java 7
    private static void groupImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        System.out.println(transactionsByCurrencies);
    }

    //Java8 groupingBy 사용
    private static void groupFunctionally() {
        // Transaction을 Currency 별로 Grouping
        Map<Currency, List<Transaction>> txByCurrencies =
                transactions.stream()
                            .collect(groupingBy(Transaction::getCurrency));

        System.out.println(txByCurrencies);
    }

    public static class Transaction {
        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }
}
