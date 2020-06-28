package com.gingost.easyexcel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.gingost.easyexcel.domain.DemoExcel;
import com.gingost.easyexcel.listener.DemoListener;
import com.gingost.easyexcel.repository.ExcelRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

/**
 * @author:lezzy
 * @Date:2020/4/10 9:27
 */
@SpringBootTest
public class ExcelTest {
    @Autowired
    private ExcelRepository excelRepository;

    //读取单个sheet
    //.headRowNumber(2)设置几行头
    @Test
    public void indexRead() {
        String fileName = "C:/Users/TJliz/Desktop/easyExcel.xlsx";
        EasyExcel.read(fileName, DemoExcel.class, new DemoListener(excelRepository)).sheet().headRowNumber(2).doRead();
    }

    //一次性读取全部类型相同的sheet
    @Test
    public void indexReadAll() {
        String fileName = "C:/Users/TJliz/Desktop/easyExcel.xlsx";
        ExcelReader excelReader = EasyExcel.read(fileName).build();
        ReadSheet readSheet1 = EasyExcel.readSheet(1).head(DemoExcel.class).registerReadListener(new DemoListener(excelRepository)).build();
        excelReader.read(readSheet1);
        excelReader.finish();
    }

    //读取类型相同的指定sheet
    //如果sheet的信息不同，那么需要不同的head(domain)和Listener(要和domain绑定)
    @Test
    public void indexSomeRead() {
        String fileName = "C:/Users/TJliz/Desktop/easyExcel.xlsx";
        ExcelReader excelReader = EasyExcel.read(fileName).build();
        //创建多个ReadSheet实例，最后由ExcelReader对象统一读取
        ReadSheet readSheet1 = EasyExcel.readSheet(0).head(DemoExcel.class).registerReadListener(new DemoListener(excelRepository)).headRowNumber(1).build();
        //ReadSheet readSheet2=EasyExcel.readSheet(2).head(DemoExcel.class).registerReadListener(new DemoListener(excelRepository)).build();
        // excelReader.read(readSheet1,readSheet2);
        excelReader.read(readSheet1);
        //切记要关闭通道，此方法会创建临时文件，如果不关闭容易内存溢出
        excelReader.finish();
    }

    @Test
    public void test5() {
        String fileName = "C:/Users/TJliz/Desktop/easyExcel.xlsx";
        EasyExcel.read(fileName, DemoExcel.class, new DemoListener(excelRepository)).extraRead(CellExtraTypeEnum.MERGE).sheet().headRowNumber(1).doRead();
    }

    @Test
    public void test6() {
        DemoExcel demoExcel = new DemoExcel();
        demoExcel.setData("1");
        excelRepository.save(demoExcel);
        System.out.println("====" + demoExcel.getId());
    }

    @Test
    public void test7() {
        String a = "2018.06";
        String b = "2019.08.12";
        String[] aArr = a.split("\\.");
        System.out.println(aArr.length);
    }
}
