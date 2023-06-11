package com.keepcode.testprj.converter.ui;

import java.util.List;
import java.util.Vector;

public interface TableConvertor<T> {

    Vector<String> getColumnNames();
    Vector<Vector<Object>> getData(List<T> obj);
    Vector<Object> getData(T obj);
}
