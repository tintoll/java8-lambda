package lambda.functional;
import java.util.function.Function;

public class FunctionAndThenCompose {

	public static void main(String[] args) {
		
		//Function
		Function<Integer,Integer> f = x -> x + 1;
		Function<Integer,Integer> g = x -> x * 2;
		
		Function<Integer,Integer> h = f.andThen(g);
		
		System.out.println(h.apply(2)); //6
		//Function 
		Function<Integer,Integer> i = x -> x + 1;
		Function<Integer,Integer> j = x -> x * 2;
		
		Function<Integer,Integer> k = i.compose(j);
		
		System.out.println(k.apply(2)); //5
		
	}

}
