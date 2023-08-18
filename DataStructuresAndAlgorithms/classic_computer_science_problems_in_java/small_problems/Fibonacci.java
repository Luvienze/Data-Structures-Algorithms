package small_problems;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Fibonacci {
	static Map<Integer, Integer> memo = new HashMap<>(Map.of(0, 0, 1, 1));

	private static int fib1(int n) // this will cause infinite loop
	{
		return fib1(n-1) + fib1(n-2);
	}
	
	private static int fib2(int n)  // this is a recursive call so calling large numbers like 40 cause long time to finish executing.
	{
		if(n < 2) {return n;}
		return fib2(n-1) + fib2(n-2);
	}
	private static int fib3(int n) // with memorization we can store the results and look them again instead of computing second time
	{
		if(!memo.containsKey(n))
		{
			//Memorization step
			memo.put(n, fib3(n-1) + fib3(n-2));
		}
		return memo.get(n);
	}
	private static int fib4(int n) // More performant option. It is a iterative approach.
	{
		int last = 0;
		int  next = 1;
		for(int i=0; i < n; i++)
		{
			int oldLast = last;
			last = next;
			next = oldLast + next;
		}
		return last;
	}
	private int last =0, next = 1;
									//This time it will output the entire sequence up to limited value.
	public IntStream stream() {
		return IntStream.generate(() ->{
			int oldLast = last;
			last = next;
			next = oldLast + next;
			return oldLast;
		});
	}

	public static void main(String[] args)
	{
		Fibonacci fib5 = new Fibonacci();
		//System.out.println(fib1(5));
		System.out.println(fib2(3));
		
		long start = System.currentTimeMillis();
		System.out.println(fib3(550));
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("Time: " + time + " ms.");
		
		start = System.currentTimeMillis();
		System.out.println(fib4(550));
		end = System.currentTimeMillis();
		time = end - start;
		System.out.println("Time: " + time + " ms.");
		
		start = System.currentTimeMillis();
		fib5.stream().limit(41).forEachOrdered(System.out::println);
		end = System.currentTimeMillis();
		time = end - start;
		System.out.println("Time: " + time + " ms.");
		
	}

}
