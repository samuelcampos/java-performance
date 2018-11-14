package net.samuelcampos.collections_capacity;

import net.samuelcampos.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionsCapacity {

    public static void main(String[] args) {
        Utils.waitForEnter();

        int arraySize = 50_000_000;

//        List<Integer> objectList = new ArrayList<>(arraySize);
        List<Integer> objectList = new ArrayList<>();

        Utils.probeExecutionTime(() -> {
            for (int i = 0; i < arraySize; i++) {
                objectList.add(i);
            }
        });

//        List<Integer> reverseList = new ArrayList<>(objectList.size());
        List<Object> reverseList = new ArrayList<>();

        Utils.probeExecutionTime(() -> {
            for (int i = objectList.size() - 1; i >= 0; i--) {
                reverseList.add(objectList.get(i));
            }
        });

        Utils.waitForEnter();
    }
}
