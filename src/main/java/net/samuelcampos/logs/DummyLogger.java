package net.samuelcampos.logs;

import lombok.extern.slf4j.Slf4j;
import net.samuelcampos.utils.Utils;

import java.util.Random;

@Slf4j
public class DummyLogger {


    public static void main(String[] args) {
        Random random = new Random();
        int[] randomNumbers = new int[] {
                random.nextInt(),
                random.nextInt(),
                random.nextInt()
        };

        Utils.waitForEnter("(Start VisualVM, attach to this process and press Enter)");

        Utils.probeExecutionTime(() -> {
            for (int i = 0; i < 100_000_000; i++) {
                log.debug("Line: " + i + " is not used? " + randomNumbers[i % randomNumbers.length]);
//                log.debug("Line: {} is not used? {}", i, randomNumbers[i % randomNumbers.length]);
            }
        });

        Utils.waitForEnter();
    }
}
