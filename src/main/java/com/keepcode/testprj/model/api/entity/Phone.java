package com.keepcode.testprj.model.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    private String number;
    private Integer country;
    private Date updateAt;
    private String dataHumans;
    private String fullNumber;
    private String countryText;
    private Date maxdate;
    private String status;
}
