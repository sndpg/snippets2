package org.psc.streams;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamLab {

    public static <I, O> Stream<O> filterCast(I inputElement, Class<O> outputType) {
        return outputType.isInstance(inputElement) ? Stream.of(outputType.cast(inputElement)) : Stream.empty();
    }

    public static <I, O> BiConsumer<I, Consumer<O>> filterCast(Class<O> outputType) {
        return (i, c) -> {
            if (outputType.isInstance(i)) {
                c.accept(outputType.cast(i));
            }
        };
    }

    public static <T> Function<T, IndexedValue<T>> withIndex(){
        AtomicInteger index = new AtomicInteger(-1);
        return t -> new IndexedValue<>(index.incrementAndGet(), t);
    }

    public record IndexedValue<T>(int index, T value) {
    }

}
