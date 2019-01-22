package net.samuelcampos.memoizer;

import net.samuelcampos.utils.Memoizer;
import net.samuelcampos.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.All})
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class FactorialMemoizerBenchmark {

    Function<Integer, BigInteger> memoizedFactorial;

    @Setup
    public void setup() {
        memoizedFactorial = Memoizer.memoize(FactorialMemoizerBenchmark::factorial, false);
    }

    @Benchmark
    public BigInteger memoizedFactorial() {
        return memoizedFactorial.apply(100_000);
    }

    @Benchmark
    public BigInteger plainFactorial() {
        return factorial(100_000);
    }

    private static BigInteger factorial(int number) {
        BigInteger result = BigInteger.valueOf(1);

        for (long factor = 2; factor <= number; factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        Utils.runBenchmark(FactorialMemoizerBenchmark.class);
    }
}
