package net.samuelcampos.collections_capacity;

import net.samuelcampos.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionsCapacity {

    public static void main(String[] args) {
        int arraySize = 50_000_000;

        List<Integer> objectList = new ArrayList<>(arraySize);
        for (int i = 0; i < arraySize; i++) {
            objectList.add(i);
        }

        Utils.waitForEnter("(Start VisualVM, attach to this process and press Enter)");

//        List<Integer> reverseList = new ArrayList<>(objectList.size());
        List<Integer> reverseList = new ArrayList<>();

        Utils.probeExecutionTime(() -> {
            for (int i = objectList.size() - 1; i >= 0; i--) {
                reverseList.add(objectList.get(i));
            }
        });

        Utils.waitForEnter();
    }
}
