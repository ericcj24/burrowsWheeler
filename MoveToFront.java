package com.princeton.algorithms2.week5;


import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

	private static int R = 256;

	// apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
    		char[] asciiarr = new char[R];
    		for (char i=0; i<256; i++) {
    			asciiarr[i] = i;
    		}

    		while (!BinaryStdIn.isEmpty()) {
    			char c = BinaryStdIn.readChar();
    			// searc that char
    			char prev = asciiarr[0];
    			int mark=-1;
    			for (char j=0; j<R; j++) {
    				if (asciiarr[j] == c) {
    					asciiarr[0] = c;
    					asciiarr[j] = prev;
    					mark=j;
    					break;
    				} else {
    					char temp = asciiarr[j];
    					asciiarr[j] = prev;
    					prev = temp;
    				}
    			}

    			BinaryStdOut.write((char)mark);
    		}
    		BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
    		char[] chars = new char[R];
		for (char i=0; i<R; i++) {
			chars[i] = i;
		}

		while (!BinaryStdIn.isEmpty()) {
			char c = BinaryStdIn.readChar();
			BinaryStdOut.write(chars[c], 8);

			// search that char
			char prev = chars[0];
			for (char j=0; j<R; j++) {
				if (j == c) {
					chars[0] = chars[c];
					chars[j] = prev;
					break;
				} else {
					char temp = chars[j];
					chars[j] = prev;
					prev = temp;
				}
			}
		}
		BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
    		if (args==null) {
    			throw new IllegalArgumentException();
    		}
    		if (args[0] == null) {
    			throw new IllegalArgumentException();
    		}

    		if (args[0].equals("-")) {
    			MoveToFront.encode();
    		} else if (args[0].equals("+")) {
    			MoveToFront.decode();
    		} else {
    			throw new IllegalArgumentException();
    		}
    }

}