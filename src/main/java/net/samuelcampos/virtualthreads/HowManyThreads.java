package net.samuelcampos.virtualthreads;

import net.samuelcampos.utils.Utils;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * https://developer.okta.com/blog/2022/08/26/state-of-java-project-loom
 */
public class HowManyThreads {

    public static void main(String[] args) throws IOException {

        Utils.waitForEnter("(Start VisualVM, attach to this process and press Enter)");


        var counter = new AtomicInteger();
        while (true) {
            //new Thread(() -> {
                Thread.startVirtualThread(() -> {
                    int count = counter.incrementAndGet();
                    System.out.println("Thread count = " + count);
                    LockSupport.park();
                });
//            }).start();
            }

        }
}
