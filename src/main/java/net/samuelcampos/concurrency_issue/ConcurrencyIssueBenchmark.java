package net.samuelcampos.concurrency_issue;

import net.samuelcampos.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.util.Random;

@State(Scope.Benchmark)
public class ConcurrencyIssueBenchmark {

    ConfigManager configManager;
    Random random;

    @Setup
    public void setup() {
        configManager = new ConfigManager();
        configManager.updateConfig("default", 0);

        random = new Random();
    }

    @Group("concurrencyIssue")
    @GroupThreads(5)
    @Benchmark
    public Integer getDefaultConfig() {
        return configManager.getConfig("default") + 1;
    }

    @Group("concurrencyIssue")
    @GroupThreads(2)
    @Benchmark
    public Integer updateDefaultConfig() {
        return configManager.updateConfig("default", random.nextInt());
    }

    public static void main(String[] args) throws Exception {
        Utils.runBenchmark(ConcurrencyIssueBenchmark.class);
    }
}
