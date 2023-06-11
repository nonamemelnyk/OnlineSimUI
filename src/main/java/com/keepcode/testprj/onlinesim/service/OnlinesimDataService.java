package com.keepcode.testprj.onlinesim.service;

import com.keepcode.testprj.onlinesim.loader.OnlinesimDataLoader;
import com.keepcode.testprj.model.api.entity.Country;
import com.keepcode.testprj.model.api.entity.Phone;
import com.keepcode.testprj.onlinesim.model.response.CountryListResponse;
import com.keepcode.testprj.onlinesim.model.response.PhoneListResponse;
import com.keepcode.testprj.service.DataService;
import com.keepcode.testprj.util.convert.ListObjectMapperUtil;

import java.util.List;


public class OnlinesimDataService implements DataService {

    private final OnlinesimDataLoader loader = new OnlinesimDataLoader();

    @Override
    public List<Phone> loadPhoneDataService() {
        PhoneListResponse response = loader.getFreePhoneList();
        return ListObjectMapperUtil.convertList(response.getNumbers(), Phone.class);
    }

    @Override
    public List<Phone> loadPhoneDataService(Integer country) {
        PhoneListResponse response = loader.getFreePhoneList(country);
        return ListObjectMapperUtil.convertList(response.getNumbers(), Phone.class);
    }

    @Override
    public List<Country> loadCountryDataService() {
        CountryListResponse response = loader.getFreeCountryList();
        return ListObjectMapperUtil.convertList(response.getCountries(), Country.class);
    }



}
