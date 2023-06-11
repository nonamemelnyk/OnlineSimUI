package com.keepcode.testprj.converter.ui;

import com.keepcode.testprj.model.api.entity.Phone;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class PhoneTableConvertor implements TableConvertor<Phone>{

    @Override
    public Vector<String> getColumnNames() {
        return new Vector<>(Arrays.asList("number", "country", "updated_at",
                "data_humans", "full_number", "country_text", "maxdate", "status"));
    }

    @Override
    public Vector<Vector<Object>> getData(List<Phone> phones) {
        var result = new Vector<Vector<Object>>();
        if(phones == null || phones.isEmpty()) return result;
        for(var phone : phones){
            result.add(getData(phone));
        }
        return result;
    }

    @Override
    public Vector<Object> getData(Phone phone) {
        return new Vector<>(Arrays.asList(phone.getNumber(), phone.getCountry(), phone.getUpdateAt(),
                phone.getDataHumans(), phone.getFullNumber(), phone.getCountryText(), phone.getMaxdate(), phone.getStatus()));
    }
}
