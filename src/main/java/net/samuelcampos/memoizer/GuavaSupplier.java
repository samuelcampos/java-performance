package net.samuelcampos.memoizer;

import com.google.common.base.Suppliers;
import net.samuelcampos.utils.Utils;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * Guava's Suppliers usage example:
 * https://google.github.io/guava/releases/27.0.1-jre/api/docs/com/google/common/base/Suppliers.html
 */
public class GuavaSupplier {


    public static void main(String[] args) throws IOException {

        Supplier<Integer> integerSupplier = Suppliers.memoize(() -> {
            try {
                return getExpensiveIO();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        // Check Suppliers.memoizeWithExpiration() method

        Utils.probeExecutionTime(() -> {
            for (int i = 0; i < 5; i++) {
//                int value = getExpensiveIO();
                int value = integerSupplier.get();

                System.out.println(i + ": " + value);
            }
        });

        Utils.waitForEnter();
    }


    private static int getExpensiveIO() throws InterruptedException {
        Thread.sleep(2000);
        return 100;
    }

}
