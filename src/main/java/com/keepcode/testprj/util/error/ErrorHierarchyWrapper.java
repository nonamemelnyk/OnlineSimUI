package com.keepcode.testprj.util.error;

import com.keepcode.testprj.exception.TestprjRuntimeException;

import java.util.function.Function;
import java.util.function.Supplier;

public class ErrorHierarchyWrapper {
    public static <T> T anyRequestLoggerAndChecker(Supplier<T> supplier,
                                                   Function<Exception, ? extends TestprjRuntimeException> createException) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw createException.apply(e);
        }
    }

    public static <T> T anyExceptionRequestLoggerAndChecker(ExceptionSupplier<T> supplier,
                                                   Function<Exception, ? extends TestprjRuntimeException> createException) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw createException.apply(e);
        }
    }

}
