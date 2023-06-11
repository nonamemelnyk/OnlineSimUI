package com.keepcode.testprj.util.http;

import com.keepcode.testprj.exception.TestprjRequestRuntimeException;
import com.keepcode.testprj.util.error.ExceptionSupplier;
import com.keepcode.testprj.util.request.JsonBodyHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static com.keepcode.testprj.util.error.ErrorHierarchyWrapper.anyExceptionRequestLoggerAndChecker;

public abstract class DataLoader {

    public <T> T getQueryDefaultWithLog(String url, Class<T> wclass) {
        return anyExceptionRequestLoggerAndChecker((ExceptionSupplier<T>) () -> {
                    var client = HttpClient.newHttpClient();
                    var request = HttpRequest.newBuilder(URI.create(url)).build();
                    return client.send(request, new JsonBodyHandler<>(wclass)).body().get();
                },
                TestprjRequestRuntimeException::new);
    }

}
