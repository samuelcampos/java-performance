package net.samuelcampos.memoizer;

import net.samuelcampos.utils.Utils;

import java.math.BigInteger;
import java.util.function.Function;

public class FactorialCalculation {

    public static void main(String[] args) {

//        Function<Integer, BigInteger> memoizedFactorial = Memoizer.memoize(FactorialCalculation::factorial, false);

        Utils.probeExecutionTime(() -> {
            for (int i = 0; i < 5; i++) {
                BigInteger factorial = factorial(100_000);
//                BigInteger factorial = memoizedFactorial.apply(100_000);

                System.out.println(i + ": " + factorial.toString().substring(0, 10) + "...");
            }

        });

        Utils.waitForEnter();
    }


    public static BigInteger factorial(int number) {
        BigInteger result = BigInteger.valueOf(1);

        for (long factor = 2; factor <= number; factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }

        return result;
    }
}
