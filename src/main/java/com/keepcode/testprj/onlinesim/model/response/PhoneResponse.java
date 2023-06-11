package com.keepcode.testprj.onlinesim.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Jacksonized
public class PhoneResponse {

    private String number;
    private Integer country;
    private Date updatedAt;
    @JsonAlias("data_humans")
    private String dataHumans;
    @JsonAlias(value = "full_number")
    private String fullNumber;
    @JsonAlias("country_text")
    private String countryText;
    private Date maxdate;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonAlias("updated_at")
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty
    @JsonFormat
    public Date getUpdateAt() {
        return this.updatedAt;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonAlias("maxdate")
    public void setMaxdate(Date maxdate) {
        this.maxdate = maxdate;
    }

    @JsonProperty("maxdate")
    @JsonFormat
    public Date getMaxDate() {
        return this.maxdate;
    }
}
