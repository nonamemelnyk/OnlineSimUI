package com.keepcode.testprj.util.error;

@FunctionalInterface
public interface ExceptionSupplier<T> {
    T get() throws Exception;
}
