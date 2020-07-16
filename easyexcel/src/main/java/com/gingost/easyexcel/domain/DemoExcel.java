package com.gingost.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * @author:lezzy
 * @Date:2020/4/7 14:27
 */
@Entity
@Table(name = "test_car_excel")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class DemoExcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;


    @Column(name = "line1")
    @ExcelProperty(value = "标题", index = 0)
    private String title;

    @Column(name = "line2")
    @ExcelProperty(value = {"时间", "test1"}, index = 1)
    private String test1;

    @Column(name = "line3")
    @ExcelProperty(value = {"时间", "test2"}, index = 2)
    private String test2;

    @ExcelProperty(value = "数据", index = 3)
    @Column(name = "line4")
    private String data;


}
