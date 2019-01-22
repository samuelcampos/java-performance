package net.samuelcampos.collections_capacity;

import net.samuelcampos.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class CollectionsBenchmark {

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
    public ArrayList<Integer> withoutInitialCapacity() {
        final ArrayList<Integer> destinationList = new ArrayList<>();
        reverseList(destinationList);

        return destinationList;
    }

    @Benchmark
    public ArrayList<Integer> withInitialCapacity() {
        final ArrayList<Integer> destinationList = new ArrayList<>(originalList.size());
        reverseList(destinationList);

        return destinationList;
    }

    private void reverseList(List<Integer> destinationList) {
        for (int i = originalList.size() - 1; i >= 0; i--) {
            destinationList.add(originalList.get(i));
        }
    }

    public static void main(String[] args) throws Exception {
        Utils.runBenchmark(CollectionsBenchmark.class);
    }
}
