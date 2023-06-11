package com.keepcode.testprj.onlinesim.loader;

import com.keepcode.testprj.onlinesim.model.response.CountryListResponse;
import com.keepcode.testprj.util.http.DataLoader;
import com.keepcode.testprj.onlinesim.model.response.PhoneListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.keepcode.testprj.converter.constant.AppProperties.APP_QUERIES_LOGGER_NAME;

public class OnlinesimDataLoader extends DataLoader {

    private static final Logger queriesLogger = LoggerFactory.getLogger(APP_QUERIES_LOGGER_NAME);
    private final String API_URL = "https://onlinesim.ru/api/";
    private final String GET_FREE_COUNTRY_LIST_API_METHOD = "getFreeCountryList";
    private final String GET_FREE_PHONE_LIST_API_METHOD = "getFreePhoneList";

    private final String GET_FREE_COUNTRY_LIST_FULL_URL = API_URL + GET_FREE_COUNTRY_LIST_API_METHOD;
    private final String GET_FREE_PHONE_LIST_FULL_URL = API_URL + GET_FREE_PHONE_LIST_API_METHOD;

    public CountryListResponse getFreeCountryList() {
        queriesLogger.info(GET_FREE_COUNTRY_LIST_FULL_URL);
        return getQueryDefaultWithLog(GET_FREE_COUNTRY_LIST_FULL_URL, CountryListResponse.class);
    }

    public PhoneListResponse getFreePhoneList(Integer country) {
        String url = country != null ? GET_FREE_PHONE_LIST_FULL_URL + "?country=" + country : GET_FREE_PHONE_LIST_FULL_URL;
        queriesLogger.info(url);
        return getQueryDefaultWithLog(url, PhoneListResponse.class);
    }

    public PhoneListResponse getFreePhoneList() {
        queriesLogger.info(GET_FREE_COUNTRY_LIST_FULL_URL);
        return getQueryDefaultWithLog(GET_FREE_PHONE_LIST_FULL_URL, PhoneListResponse.class);
    }

}
