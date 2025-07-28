package net.samuelcampos.virtualthreads;

import net.samuelcampos.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class VirtualThreadsBenchmark {

    @Param({"2000"})
    int numberOfThreads;


    @Benchmark
    public void withVirtualThreads() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, numberOfThreads).forEach(i -> {
                executor.submit(() -> factorialUsingForLoop(20000));
            });
        }
    }

    @Benchmark
    public void withStandardThreads() {
        try (var executor = Executors.newFixedThreadPool(numberOfThreads)) {
            IntStream.range(0, numberOfThreads).forEach(i -> {
                executor.submit(() -> factorialUsingForLoop(20000));
            });
        }
    }

    public long factorialUsingForLoop(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public static void main(String[] args) throws Exception {
        Utils.runBenchmark(VirtualThreadsBenchmark.class);
    }
}
