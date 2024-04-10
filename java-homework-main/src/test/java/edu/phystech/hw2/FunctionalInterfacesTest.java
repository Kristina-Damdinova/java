package edu.phystech.hw2;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

class ToUpperCaseOperator implements UnaryOperator<String> {
    @Override
    public String apply(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length();++i){
            if((int)arr[i] >= 97 && (int)arr[i] <= 122){
                arr[i] = (char)((int)arr[i] - 32);
            }
        }
        String str = new String(arr);
        return str;
    }
}

// Возвращает модуль максимума из двух модулей чисел
class AbsMaxOperator implements BinaryOperator<Integer> {

    @Override
    public Integer apply(Integer integer, Integer integer2) {
        return Math.max(Math.abs(integer),Math.abs(integer2));
    }
}

class StringLengthMoreThan5 implements Predicate<String> {

    @Override
    public boolean test(String s) {
        if (s.length() <= 5) {
            return false;
        }
        return true;
    }
}


// Проверяет, является ли число квадратом
class IsNumberASquareOfAnotherNumber implements Predicate<Integer> {

    @Override
    public boolean test(Integer integer) {
        double root = Math.sqrt(integer);
        if (root%1 == 0){
            return true;
        }
        return false;
    }
}

// Возвращает четные числа, начиная с from включительно, если в from нечетное число, то начиная с первого четного с from
@RunWith(Enclosed.class)
class EvenNumberSupplier implements Supplier<Integer> {
    private int currentNumber;

    public EvenNumberSupplier(int from) {
        this.currentNumber = from % 2 == 0 ? from : from + 1;
    }

    @Override
    public Integer get() {
        int numberToReturn = currentNumber;
        currentNumber += 2;
        return numberToReturn;
    }
}

public class FunctionalInterfacesTest {

    @Test
    public void unaryOperatorTest() {
        var result = new ArrayList<>(List.of("abC", "edf"));
        result.replaceAll(new ToUpperCaseOperator());
        Assertions.assertEquals(List.of("ABC", "EDF"), result);
    }

    @Test
    public void binaryOperatorTest() {
        var result = Stream.of(2, 3, 1, -10).reduce(4, new AbsMaxOperator());
        Assertions.assertEquals(10, result);
    }

    @Test
    public void predicateTest() {
        Assertions.assertEquals(
                Stream.of("a", "bb", "ccc", "1234567", "aaaaaaaaa").filter(new StringLengthMoreThan5()).toList(),
                List.of("1234567", "aaaaaaaaa")
        );

        Assertions.assertEquals(
                Stream.of(1, 4, 5, 10, 16, 25).filter(new IsNumberASquareOfAnotherNumber()).toList(),
                List.of(1, 4, 16, 25)
        );
    }

    @Test
    public void supplierTest() {
        var evenNumberSupplier = new EvenNumberSupplier(0);
        Stream.of(0, 2, 4, 6, 8, 10).forEach(number -> Assertions.assertEquals(number, evenNumberSupplier.get()));

        var anotherSupplier = new EvenNumberSupplier(11);
        Stream.of(12, 14, 16, 18, 20, 22).forEach(number -> Assertions.assertEquals(number, anotherSupplier.get()));
    }
}