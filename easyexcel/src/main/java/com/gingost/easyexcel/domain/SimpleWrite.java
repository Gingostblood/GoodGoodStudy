package com.gingost.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author:lezzy
 * @Date:2020/4/13 10:54
 */
@Data
@NoArgsConstructor
public class SimpleWrite {
    @ExcelIgnore
    private Integer id;

    @ExcelProperty({"主标题", "学生姓名"})
    private String name;

    @ExcelProperty({"主标题", "期末成绩"})
    private Double data;

    @ExcelProperty({"主标题", "统计时间"})
    private Date time;

    /*public SimpleWrite(DemoExcel demoExcel){
        this.id=demoExcel.getId();
        this.name=demoExcel.getStr();
        this.data=demoExcel.getData();
        this.time=demoExcel.getDate();
    }*/
}
