package net.samuelcampos.primitives;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.samuelcampos.utils.Utils;
import org.openjdk.jmh.annotations.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 2)
@Measurement(iterations = 3, time = 1)
@Fork(1)
public class LoadJsonBenchmark {

    @Param({"1"})
    int size;

    private static String jsonString;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public LoadJsonBenchmark() {
        ClassLoader classLoader = getClass().getClassLoader();

        // This JSON file has ~ 16 MB
        try (BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("big.json")))) {
            jsonString = br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    @Benchmark
    public List<PrimitiveTypes> withPrimitives() {
        return loadJson(new TypeReference<List<PrimitiveTypes>>() {});
    }

    @Benchmark
    public List<WrapperTypes> withWrappers() {
        return loadJson(new TypeReference<List<WrapperTypes>>() {});
    }

    @SneakyThrows
    private <T> List<T> loadJson(TypeReference typeReference) {
        List<T> resultList = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            resultList.add(objectMapper.readValue(jsonString, typeReference));
        }

        return resultList;
    }

    public final static class PrimitiveTypes {
        public String guid;
        public boolean isActive;
        public short age;
        public int socialSecurityNumber;
    }

    public final static class WrapperTypes {
        public String guid;
        public Boolean isActive;
        public Short age;
        public Integer socialSecurityNumber;
    }

    public static void main(String[] args) throws Exception {
        Utils.runBenchmark(LoadJsonBenchmark.class);
    }

//    public static void main(String[] args) throws Exception {
//        LoadJsonBenchmark benchmark = new LoadJsonBenchmark();
//        benchmark.size = 100;
//        benchmark.setup();
//
//        Utils.waitForEnter("(Start VisualVM, attach to this process and press Enter)");
//
//        List<?> resultList = benchmark.loadJson(new TypeReference<List<PrimitiveTypes>>() {});
////        List<?> resultList = benchmark.loadJson(new TypeReference<List<WrapperTypes>>() {});
//        Utils.waitForEnter();
//
//
////        List<PrimitiveTypes> resultList = new ArrayList<>();
////        Random random = new Random();
////        for (int i = 0; i < 200_000; i++) {
////            PrimitiveTypes pt = new PrimitiveTypes();
////            pt.guid = "id_" + i;
////            pt.isActive = random.nextBoolean();
////            pt.age = (short) random.nextInt(16);
////            pt.socialSecurityNumber = random.nextInt();
////            resultList.add(pt);
////        }
////
////        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("big.json"))) {
////            new ObjectMapper().writeValue(outputStream, resultList);
////        }
//    }
}
