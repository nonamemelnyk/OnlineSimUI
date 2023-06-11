package com.keepcode.testprj.service;

import com.keepcode.testprj.model.api.entity.Country;
import com.keepcode.testprj.model.api.entity.Phone;

import java.util.List;

public interface DataService {

    List<Phone> loadPhoneDataService();
    List<Phone> loadPhoneDataService(Integer country);

    List<Country> loadCountryDataService();

}
