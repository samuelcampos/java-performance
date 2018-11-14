package net.samuelcampos.memoizer;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import lombok.SneakyThrows;
import net.samuelcampos.utils.Utils;

public class GuavaSupplier {


    public static void main(String[] args) {

//        Supplier<Integer> integerSupplier = Suppliers.memoize(GuavaSupplier::getExpensiveIO);

        Utils.probeExecutionTime(() -> {
            for (int i = 0; i < 5; i++) {
                int value = getExpensiveIO();
//                int value = integerSupplier.get();

                System.out.println(i + ": " + value);
            }
        });

        Utils.waitForEnter();
    }


    @SneakyThrows
    private static int getExpensiveIO() {
        Thread.sleep(2000);
        return 100;
    }

}
