package firstProgram;

/*
 * File: ManualClassLoader.java
 * Author: Alexander Szelestey
 * Date: April 12, 2020
 * Purpose: This class has a static method that will be executed at least
 * 500000 times as soon as application starts and with each execution.
 * References:
 * https://github.com/andreweissen/Insertion_Sort_Performance_Benchmark
 * https://www.baeldung.com/java-jvm-warmup
 */

final class ManualClassLoader {

	protected static void load() {

		// Declaration
		Dummy dummy;

		//Warming up with this loop
		for (int i = 0; i < 500000; i++) {
			dummy = new Dummy();
			dummy.noop();
		}
	}
}