package net.samuelcampos.benchmarks;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        args = new String[]{
                StreamsLoopsBenchmark.class.getSimpleName()
        };

        org.openjdk.jmh.Main.main(args);
    }
}
