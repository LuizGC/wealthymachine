package com.wealthy.machine.core.util.technicalanlysis;

import java.util.Collections;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class EratosthenesSieve implements PrimeNumberFinder {
	public SortedSet<Integer> findPrimeNumber(int limit) {
		var primeNumbers = new TreeSet<Integer>();
		if (limit > 2) {
			var nonPrimeNumbers = new HashSet<Integer>();
			var i = 2;
			while (i < limit) {
				var j = i;
				if (!nonPrimeNumbers.contains(j)) {
					while (j < limit) {
						j += i;
						nonPrimeNumbers.add(j);
					}
					primeNumbers.add(i);
				}
				i++;
			}
		}
		return Collections.unmodifiableSortedSet(primeNumbers);
	}
}