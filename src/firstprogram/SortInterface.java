package firstProgram;

/*
 * File: SortInterface.java
 * Author: Alexander Szelestey
 * Date: April 12, 2020
 * Purpose: The interface is for the count and time sort algorithms
 * References:
 * https://github.com/HeartOfGold523/Design-and-Analysis-of-Computer-Algorithms/tree/master/Project%201/Project1/src
 */

public interface SortInterface {
	int[] iterativeSort(int[] array) throws UnsortedException;

	int[] recursiveSort(int[] array) throws UnsortedException;

	int getCount();

	long getTime();
}
