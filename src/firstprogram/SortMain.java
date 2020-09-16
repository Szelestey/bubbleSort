package firstProgram;

/*
 * File: SortMain.java
 * Author: Alexander Szelestey
 * Date: April 12, 2020
 * Purpose: This class handles JVM warmup and sets the test cases as required to be evenly spaced.
 * References:
 * https://github.com/HeartOfGold523/Design-and-Analysis-of-Computer-Algorithms/tree/master/Project%201/Project1/src
 * https://github.com/andreweissen/Insertion_Sort_Performance_Benchmark
 * https://www.baeldung.com/java-jvm-warmup
 */

public class SortMain {
	static {

		// Declarations
		long startTime, endTime;

		startTime = System.nanoTime();
		ManualClassLoader.load();
		endTime = System.nanoTime();
		System.out.println("Warm up time: " + (endTime - startTime));
	}

	public static void main(String[] args) throws Exception {
		// Declarations
		int[] sizes;
		long startTime, endTime;

		// Intial JVM warmup
		startTime = System.nanoTime();
		ManualClassLoader.load();
		endTime = System.nanoTime();
		System.out.println("Total time: " + (endTime - startTime));

		sizes = new int[] { 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000 };
		new BenchmarkSorts(sizes);

	}
}
