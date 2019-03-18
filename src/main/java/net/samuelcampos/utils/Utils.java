package net.samuelcampos.utils;

import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;
import org.openjdk.jmh.profile.CompilerProfiler;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Utils {

    private Utils() {
    }

    @SneakyThrows
    public static void waitForEnter() {
        waitForEnter("(Press Enter)");
    }

    @SneakyThrows
    public static void waitForEnter(String message) {
        System.out.println(message);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
    }

    public static void probeExecutionTime(Runnable runner) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        runner.run();

        stopwatch.stop();
        System.out.println("Time took (ms): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    public static void runBenchmark(Class<?> clazz) throws Exception {
//        String[] args = new String[]{
//                clazz.getSimpleName()
//        };
//        org.openjdk.jmh.Main.main(args);

        org.openjdk.jmh.runner.options.Options opt = new OptionsBuilder()
                .include(clazz.getSimpleName())
//                .shouldDoGC(true)
                .resultFormat(ResultFormatType.JSON)
                .result("reports/" + clazz.getSimpleName() + ".json")
//                .addProfiler(StackProfiler.class)
                .addProfiler(CompilerProfiler.class)
                .jvmArgsAppend("-Djmh.stack.period=1")
//                .warmupIterations(5)
//                .measurementIterations(5)
//                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
