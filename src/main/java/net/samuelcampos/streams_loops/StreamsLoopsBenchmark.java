package net.samuelcampos.streams_loops;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.samuelcampos.utils.Utils;
import org.openjdk.jmh.annotations.*;

/**
 * Try to find an element that not exists on the List.
 *
 * This class was adapted from: https://gist.github.com/mmimica/f7f7c8033820dd95dd449cf58e0ad58b
 */
@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class StreamsLoopsBenchmark {
    private List<Integer> list = new ArrayList<>();

    @Param({ "1000", "100000" })
    private int size;

    @Setup
    public void setup() {
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    @Benchmark
    public int parallel_stream() {
        return list.parallelStream()
                .filter(i -> i == -1)
                .findAny()
                .orElse(0);
    }

    @Benchmark
    public int stream() {
        return list.stream()
                .filter(i -> i == -1)
                .findAny()
                .orElse(0);
    }

    @Benchmark
    public int loop() {
        for (Integer i : list) {
            if (i == -1) {
                return i;
            }
        }
        return 0;
    }

    @Benchmark
    public int loop_classical() {
        int listSize = list.size();

        for (int i = 0; i < listSize; i++) {
            if (list.get(i) == -1) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        Utils.runBenchmark(StreamsLoopsBenchmark.class);
    }
}