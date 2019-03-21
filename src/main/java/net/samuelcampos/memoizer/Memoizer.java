package net.samuelcampos.memoizer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * A class to build memoizer {@link Function functions} with a single input argument.
 * This memoizer function will remember all the input arguments and respective results forever.
 *
 * @param <T> Input argument type.
 * @param <U> Return type.
 */
public class Memoizer<T, U> {

    private final Map<T, U> cache;

    private Memoizer(boolean threadSafe) {
        this.cache = threadSafe ? new ConcurrentHashMap<>() : new HashMap<>();
    }

    private Function<T, U> doMemoize(final Function<T, U> function) {
        return input -> cache.computeIfAbsent(input, function);
    }

    /**
     * Returns a Function which caches the instance retrieved during the first call to {@link Function#apply(Object)}
     * with a given input argument and returns that value on subsequent calls.
     *
     * @param function   The function to memoize.
     * @param threadSafe Where the memoization should be Thread-Safe.
     * @param <T>        The function input type.
     * @param <U>        The function output type.
     * @return The memoize function.
     */
    public static <T, U> Function<T, U> memoize(final Function<T, U> function, boolean threadSafe) {
        return new Memoizer<T, U>(threadSafe).doMemoize(function);
    }

    /**
     * Same as {@link #memoize(Function, boolean)} but Thread-Safe.
     *
     * @see #memoize(Function, boolean)
     */
    public static <T, U> Function<T, U> memoize(final Function<T, U> function) {
        return new Memoizer<T, U>(true).doMemoize(function);
    }
}
