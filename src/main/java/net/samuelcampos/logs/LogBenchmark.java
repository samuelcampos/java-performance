package net.samuelcampos.logs;

import lombok.extern.slf4j.Slf4j;
import net.samuelcampos.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 4, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Slf4j
public class LogBenchmark {

    @Param({ "100000" })
    int size;

    private int[] randomNumbers;

    @Setup
    public void setup() {
        Random random = new Random();
        randomNumbers = new int[]{
                random.nextInt(),
                random.nextInt(),
                random.nextInt()
        };
    }

    @Benchmark
    public void concatenatingStrings() {
        for (int i = 0; i < size; i++) {
            log.debug("Line: " + i + " is not used? " + randomNumbers[i % randomNumbers.length]);
        }
    }

    @Benchmark
    public void parameterizedMessage() {
        for (int i = 0; i < size; i++) {
            log.debug("Line: {} is not used? {}", i, randomNumbers[i % randomNumbers.length]);
        }
    }

    public static void main(String[] args) throws Exception {
        Utils.runBenchmark(LogBenchmark.class);
    }
}
