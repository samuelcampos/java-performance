package net.samuelcampos.primitives_wrapper;

import net.samuelcampos.utils.Utils;

public class PrimitivesVsWrapper {

    public static void main(String[] args) {
        Utils.waitForEnter("(Start VisualVM, attach to this process and press Enter)");

        Integer[] array = new Integer[40_000_000];
//        int[] array = new int[40_000_000];

        Utils.probeExecutionTime(() -> {
            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }
        });

        Utils.waitForEnter();
        System.out.println(array);
    }

}
