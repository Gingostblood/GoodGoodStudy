package com.gingost.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


/**
 * @author:lezzy
 * @Date:2020/4/10 14:46
 */
@Entity
@Table(name = "citydo")
@Data
public class Citydo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @ExcelProperty(index = 0)
    @Column(name = "row_id")
    private String rowId;

    @ExcelProperty(index = 1)
    @Column(name = "dept")
    private String dept;

    @ExcelProperty(index = 2)
    @Column(name = "sys")
    private String sys;

    @ExcelProperty(index = 3)
    @Column(name = "app")
    private String app;

    @ExcelProperty(index = 4)
    @Column(name = "data_dept")
    private String dataDept;

    @ExcelProperty(index = 5)
    @Column(name = "data")
    private String data;

    @ExcelProperty(index = 6)
    @Column(name = "coment")
    private String coment;

}
