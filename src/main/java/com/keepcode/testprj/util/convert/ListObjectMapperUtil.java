package com.keepcode.testprj.util.convert;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keepcode.testprj.converter.constant.AppProperties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListObjectMapperUtil {

    public static <T, F> List<T> convertList(List<F> list, Class<T> wclass) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return Optional.ofNullable(list)
                .orElse(Collections.emptyList())
                .stream()
                .map(obj -> mapper.convertValue(obj, wclass))
                .collect(Collectors.toList());
    }
}
