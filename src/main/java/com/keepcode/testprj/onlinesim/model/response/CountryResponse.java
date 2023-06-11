package com.keepcode.testprj.onlinesim.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Jacksonized
public class CountryResponse {

    private Integer country;
    @JsonAlias("country_text")
    private String countryText;

//    @JsonAlias(value = "country_text")
//    public void setCountryText(String countryText) {
//        this.countryText = countryText;
//    }
}
