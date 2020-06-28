package com.gingost.security.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DateVo {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
