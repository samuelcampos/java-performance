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
        System.out.println("(Press Enter)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
    }

    public static void probeExecutionTime(Runnable runner) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        runner.run();

        stopwatch.stop();
        System.out.println("Time took (ms): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

}
