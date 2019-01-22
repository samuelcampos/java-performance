package net.samuelcampos.utils;

import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;

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
        String[] args = new String[]{
                clazz.getSimpleName()
        };
        org.openjdk.jmh.Main.main(args);
    }
}
