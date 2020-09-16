package firstProgram;

/*
 * File: BenchmarkSorts.java
 * Author: Alexander Szelestey
 * Date: April 12, 2020
 * Purpose: Main class that creates two output files
 * References:
 * https://github.com/HeartOfGold523/Design-and-Analysis-of-Computer-Algorithms/tree/master/Project%201/Project1/src
 * https://stackoverflow.com/questions/33144667/concatenating-two-arrays-with-alternating-values
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import javafx.scene.chart.BubbleChart;

public class BenchmarkSorts {
	private final int NUMBER_OF_RUNS = 50;

	private int[] iterative;
	private int[] recursive;
	private int iterativeIndex = 0;
	private int recursiveIndex = 0;
	private int[] iterativeCountLog = new int[NUMBER_OF_RUNS];
	private int[] recursiveCountLog = new int[NUMBER_OF_RUNS];
	private long[] iterativeTimeLog = new long[NUMBER_OF_RUNS];
	private long[] recursiveTimeLog = new long[NUMBER_OF_RUNS];

	private BubbleSort bubbleSort = new BubbleSort();

	public BenchmarkSorts(int[] sizes) {
		// Creates benchmarks based on the input array size
		IntStream.range(0, sizes.length).forEach(i -> new BenchmarkSorts(sizes[i]));
	}

	private BenchmarkSorts(int n) {

		// Outer loop 50 times (NUMBER_OF_RUNS)
		IntStream.range(0, NUMBER_OF_RUNS).forEach(i -> {
			iterative = new int[n];
			recursive = new int[n];
			// Inner loop based on the array size (n)
			IntStream.range(0, n).forEach(j -> {
				Random random = new Random();
				iterative[j] = random.nextInt(1000);
				recursive[j] = random.nextInt(1000);

			});

			// Runs the sort and produces output if an UnsortedException is found
			try {
				runSorts();
			} catch (UnsortedException e) {
				System.out.println(e.getMessage());
			}
		});
		displayReport(n);
	}

	private void runSorts() throws UnsortedException {

		// Runs iterative sort
		bubbleSort.iterativeSort(iterative);
		iterativeCountLog[iterativeIndex] = bubbleSort.getCount();
		iterativeTimeLog[iterativeIndex] = bubbleSort.getTime();
		iterativeIndex++;

		// Runs recursive sort
		bubbleSort.recursiveSort(recursive);
		recursiveCountLog[recursiveIndex] = bubbleSort.getCount();
		recursiveTimeLog[recursiveIndex] = bubbleSort.getTime();
		recursiveIndex++;
	}

	public void displayReport(int arraySize) {

		int[] iterativeConcat = new int[iterativeCountLog.length * 2];
		int iCIndex = 0;
		for (int i = 0; i < iterativeCountLog.length; i++) {
			iterativeConcat[iCIndex++] = iterativeCountLog[i];
			iterativeConcat[iCIndex++] = (int) iterativeTimeLog[i];
		}

		int[] recursiveConcat = new int[recursiveCountLog.length * 2];
		int rCIndex = 0;
		for (int i = 0; i < recursiveCountLog.length; i++) {
			recursiveConcat[rCIndex++] = recursiveCountLog[i];
			recursiveConcat[rCIndex++] = (int) recursiveTimeLog[i];
		}

		// Produces output
		String iterative = (arraySize + " "
				+ Arrays.toString(iterativeConcat).replace("[", "").replace("]", "").replace(",", ""));

		String recursive = (arraySize + " "
				+ Arrays.toString(recursiveConcat).replace("[", "").replace("]", "").replace(",", ""));

		try {
			FileOutputStream fos = new FileOutputStream("Iterative.txt", true);
			PrintWriter pw = new PrintWriter(fos);
			pw.println(iterative);
			pw.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {

			FileOutputStream fos = new FileOutputStream("Recursive.txt", true);
			PrintWriter pw = new PrintWriter(fos);
			pw.println(recursive);
			pw.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
