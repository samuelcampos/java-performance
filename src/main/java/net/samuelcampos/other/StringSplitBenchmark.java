package net.samuelcampos.other;

import net.samuelcampos.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class StringSplitBenchmark {

    private static final String inputStr = "123==456";

    // Precompiled regular expression
    private static final Pattern pattern = Pattern.compile("==");

    @Benchmark
    public String[] stringSplit() {
        return inputStr.split("==", 2);
    }

    @Benchmark
    public String[] patternSplit() {
        return pattern.split(inputStr, 2);
    }

    public static void main(String[] args) throws Exception {
        Utils.runBenchmark(StringSplitBenchmark.class);
    }
}
