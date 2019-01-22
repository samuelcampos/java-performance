package net.samuelcampos.logs;

import lombok.extern.slf4j.Slf4j;
import net.samuelcampos.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 4, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 4, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Slf4j
public class LogBenchmark {

    @Param({ "100000" })
    int size;

    private Random random;

    @Setup
    public void setup() {
        random = new Random();
    }

    @Benchmark
    public void concatenatingStrings() {
        int randNumber = 0;

        for (int i = 0; i < size; i++) {
            randNumber += random.nextInt();

            log.debug("Line: " + i + " is not used? " + randNumber);
        }
    }

    @Benchmark
    public void parameterizedMessage() {
        int randNumber = 0;

        for (int i = 0; i < size; i++) {
            randNumber += random.nextInt();

            log.debug("Line: {} is not used? {}", i, randNumber);
        }
    }

    @Benchmark
    public void parameterizedMessageIf() {
        int randNumber = 0;

        for (int i = 0; i < size; i++) {
            randNumber += random.nextInt();

            if (log.isDebugEnabled()) {
                log.debug("Line: {} is not used? {}", i, randNumber);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Utils.runBenchmark(LogBenchmark.class);
    }
}
