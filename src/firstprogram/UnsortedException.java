package firstProgram;

/*
 * File: UnsortedException.java
 * Author: Alexander Szelestey
 * Date: April 12, 2020
 * Purpose: Catch any exceptions were input/output may not have been properly sorted.
 * References:
 * https://github.com/HeartOfGold523/Design-and-Analysis-of-Computer-Algorithms/tree/master/Project%201/Project1/src
 */

public class UnsortedException extends Exception {
	private static final long serialVersionUID = 1L;

	public UnsortedException(String message) {
		super(message);
	}
}
