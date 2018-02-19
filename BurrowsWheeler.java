package com.princeton.algorithms2.week5;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

	// apply Burrows-Wheeler transform, reading from standard input and writing to standard output
	public static void transform() {

		String s = BinaryStdIn.readString();
		CircularSuffixArray csa = new CircularSuffixArray(s);

		int zeroIdx = -1;
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<csa.length(); i++) {
			if (csa.index(i)==0) {
				zeroIdx = i;
			}
			int idx = csa.index(i)-1;
			if (idx<0) {
				idx=csa.length()-1;
			}
			sb.append(s.charAt(idx));
		}

		BinaryStdOut.write(zeroIdx);
		BinaryStdOut.write(sb.toString());

		BinaryStdOut.close();

	}

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
	public static void inverseTransform() {

		int first = BinaryStdIn.readInt();
		String t = BinaryStdIn.readString();
		int length = t.length();
		int[] next = new int[length];

		int R = 256;
		int[] count = new int[R+1];
		for (int i=0; i<length; i++) {
			count[t.charAt(i)+1]++;
		}

		for (int i=0; i<R; i++) {
			count[i+1] = count[i]+count[i+1];
		}

		for (int i=0; i<length; i++) {
			next[count[t.charAt(i)]++] = i;
		}

		StringBuilder sb = new StringBuilder();
		int idx = first;
		for (int i=0; i<length; i++) {
			sb.append(t.charAt(next[idx]));
			idx = next[idx];
		}

		BinaryStdOut.write(sb.toString());
		BinaryStdOut.close();
	}

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
	public static void main(String[] args) {
		if (args==null) {
			throw new IllegalArgumentException();
		}
		if (args[0] == null) {
			throw new IllegalArgumentException();
		}

		if (args[0].equals("-")) {
			BurrowsWheeler.transform();
		} else if (args[0].equals("+")) {
			BurrowsWheeler.inverseTransform();
		} else {
			throw new IllegalArgumentException();
		}
	}
}