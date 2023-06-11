package com.keepcode.testprj.converter.ui;

import com.keepcode.testprj.model.api.entity.Country;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class CountryTableConvertor implements TableConvertor<Country> {

    @Override
    public Vector<String> getColumnNames() {
        return new Vector<>(Arrays.asList("country", "countryText"));
    }

    @Override
    public Vector<Vector<Object>> getData(List<Country> obj) {
        var result = new Vector<Vector<Object>>();
        if(obj == null || obj.isEmpty()) return result;
        for(var country : obj){
            result.add(getData(country));
        }
        return result;
    }

    @Override
    public Vector<Object> getData(Country obj) {
        return new Vector<>(Arrays.asList(obj.getCountry(), obj.getCountryText()));
    }
}
