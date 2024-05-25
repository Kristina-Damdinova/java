package edu.phystech.hw3;

import java.util.HashSet;
import java.util.Set;

public class SetUtils {
    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> unionOfSets = new HashSet<>(s1);
        unionOfSets.addAll(s2);
        return unionOfSets;
    }

    public static <E> Set<E> intersection(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> intersectionOfSets = new HashSet<>(s1);
        intersectionOfSets.retainAll(s2);
        return intersectionOfSets;
    }

    public static <E> Set<E> difference(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> differenceOfSets = new HashSet<>(s1);
        differenceOfSets.removeAll(s2);
        return differenceOfSets;
    }

    public static <E> Set<E> symmetricDifference(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> symmetricDifferenceOfSets = new HashSet<>(s1);
        Set<E> symmetricDifferenceOfSets1 = new HashSet<>(s2);

        symmetricDifferenceOfSets.removeAll(s2);
        symmetricDifferenceOfSets1.removeAll(s1);

        symmetricDifferenceOfSets.addAll(symmetricDifferenceOfSets1);

        return symmetricDifferenceOfSets;
    }

}
