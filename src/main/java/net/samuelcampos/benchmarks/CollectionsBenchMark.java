package net.samuelcampos.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class CollectionsBenchMark {

    @Param({"10000000"})
    int size;

    private List<Integer> originalList;

    @Setup
    public void setup() {
        originalList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            originalList.add(i);
        }
    }

    @Benchmark
    public void withoutInitialCapacity() {
        reverseList(new ArrayList<>());
    }

    @Benchmark
    public void withInitialCapacity() {
        reverseList(new ArrayList<>(size));
    }

    private void reverseList(List<Integer> destinationList) {
        for (int i = originalList.size() - 1; i >= 0; i--) {
            destinationList.add(originalList.get(i));
        }
    }
}
