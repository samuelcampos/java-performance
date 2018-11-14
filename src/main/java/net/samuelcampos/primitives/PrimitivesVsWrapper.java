package net.samuelcampos.primitives;

import net.samuelcampos.utils.Utils;


public class PrimitivesVsWrapper {

    public static void main(String[] args) {

//        Object[] array = new Object[40_000_000];
        int[] array = new int[40_000_000];

        Utils.probeExecutionTime(() -> {
            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }
        });

        Utils.waitForEnter();
    }

}
