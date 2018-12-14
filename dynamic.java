//https://stackoverflow.com/questions/18590901/check-if-a-string-contains-numbers-java
//https://www.geeksforgeeks.org/longest-repeated-subsequence/

import java.io.*;
import java.util.*;

//Dynamic Programming
class dynamic {
	// Function to find LRS UsingDP of substrings A[0..p-1], A[0..q-1]
	public static String findLRS(String A, int p, int q, int[][] L) {
		// if we have reached the end of either sequence
		// return empty string
		if (p == 0 || q == 0) {
			return "";
		}
		// if characters at index p and q matches and index is different
		if (A.charAt(p - 1) == A.charAt(q - 1) && p != q) {
			return findLRS(A, p - 1, q - 1, L) + A.charAt(p - 1);
		} else
		// else if characters at index p and q don't match
		{
			if (L[p - 1][q] > L[p][q - 1]) {
				return findLRS(A, p - 1, q, L);
			} else {
				return findLRS(A, p, q - 1, L);
			}
		}
	}

	// returns the greatest of two numbers
	public static int max(int i, int j) {
		return i > j ? i : j;
	}

	// Function to fill lookup table by finding the length of LRSUsingDP
	// of substring lookupTable[0..n-1]
	public static void getLRSLength(String inputString, int[][] lookupTable) {
		// building table in bottom-up manner
		int i, j;

		// fill the lookup table in bottom-up manner
		for (i = 1; i <= inputString.length(); i++) {
			for (j = 1; j <= inputString.length(); j++) {

				if (inputString.charAt(i - 1) == inputString.charAt(j - 1)
						&& i != j) {
					lookupTable[i][j] = lookupTable[i - 1][j - 1] + 1;
				}
				// else if characters at index i and j are different
				else {
					lookupTable[i][j] = max(lookupTable[i - 1][j],
							lookupTable[i][j - 1]);
				}
			}
		}
	}

	public static void main(String[] args) {
		long startTime, endTime;
		int i, j, index = 0;
		if (args.length < 1) {
			System.err.println("Invalid Input");
			System.exit(1);
		}
		// Input from command line
		String inputString = args[0];

		// Invalid Input if the input are digits, special characters
		if (inputString.matches(".*\\d+.*")) {
			System.out.println("Invalid Input");
		} else if (inputString.matches("[$&+,:;=?@#|'<>.^*()%!-]")) {
			System.out.println("Invalid Input");
		} else {

			int[][] lookupTable = new int[inputString.length() + 1][inputString
					.length() + 1];

			// Fill the lookup table
			getLRSLength(inputString, lookupTable);
			String longestRepeatedSeq = findLRS(inputString,
					inputString.length(), inputString.length(), lookupTable);
			System.out.println("Output:");

			String str = "";

			str = str + longestRepeatedSeq.charAt(0);
			index = 0;
			for (i = 0; i < longestRepeatedSeq.length(); i++) {

				for (j = 0; j < str.length(); j++) {
					if (str.charAt(j) == longestRepeatedSeq.charAt(i))
						index = 1;
				}
				if (index == 0) {

					str = str + longestRepeatedSeq.charAt(i);
				}
				index = 0;
			}
			System.out.println(str);

		}
	}

}
