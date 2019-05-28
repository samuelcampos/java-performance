package net.samuelcampos.primitives_wrapper;

import net.samuelcampos.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@Fork(1)
public class PrimitivesWrappersBenchmark {

    @Param({"4000000"})
    int size;

    @Benchmark
    public Integer[] withWrappers() {
        Integer[] array = new Integer[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        return array;
    }

    @Benchmark
    public int[] withPrimitives() {
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        return array;
    }

    public static void main(String[] args) throws Exception {
        Utils.runBenchmark(PrimitivesWrappersBenchmark.class);
    }
}
