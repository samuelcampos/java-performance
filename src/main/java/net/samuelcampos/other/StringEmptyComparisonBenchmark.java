package net.samuelcampos.other;

import joptsimple.internal.Strings;
import net.samuelcampos.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class StringEmptyComparisonBenchmark {


    @Benchmark
    public boolean testIsEmpty() {
        return "123==456".isEmpty();
    }

    @Benchmark
    public boolean testEqualsEmpty() {
        return "123==456".equals(Strings.EMPTY);
    }

    public static void main(String[] args) throws Exception {
        Utils.runBenchmark(StringEmptyComparisonBenchmark.class);
    }
}
