/*
package com.gingost.easyexcel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.gingost.easyexcel.domain.DemoExcel;
import com.gingost.easyexcel.domain.SimpleWrite;
import com.gingost.easyexcel.repository.ExcelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

*/
/**
 * @author:lezzy
 * @Date:2020/4/13 18:22
 *//*

@SpringBootTest
public class SimpleWriteTest {
    private static String prefix = "C:/Users/TJliz/Desktop/easyExcel/";
    private static String suxfix = ".xls";
    @Autowired
    private ExcelRepository excelRepository;

    */
/*private List getData() {

        List<DemoExcel> demoExcelList = excelRepository.findAll();
        List<SimpleWrite> list = new ArrayList<>();
        for (DemoExcel demoExcel : demoExcelList) {
            SimpleWrite simpleWrite = new SimpleWrite(demoExcel);
            list.add(simpleWrite);
        }
        return list;
    }*//*


    @Test
    public void testWrite() {


 */
/*       ExcelWriter excelWriter = EasyExcel.write(filePath, SimpleWrite.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("citydo").build();
        excelWriter.write(list, writeSheet);
        excelWriter.finish();*//*

        //会自动关闭流
        EasyExcel.write(prefix + "testTiele" + suxfix, SimpleWrite.class).sheet("hello").doWrite(getData());
    }

    @Test
    public void IgnoreProperty() {
        Set<String> ignoreProperty = new HashSet<>();
        ignoreProperty.add("time");
        EasyExcel.write(prefix + "Notime" + suxfix, SimpleWrite.class).sheet("hello").excludeColumnFiledNames(ignoreProperty).doWrite(getData());
    }
    @Test
    public void repeatedWrite() {
        // 方法1 如果写到同一个sheet 只要创建一次writeSheet
        String fileName = "";
        ExcelWriter excelWriter = EasyExcel.write(fileName, SimpleWrite.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
        for (int i = 0; i < 5; i++) {
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            excelWriter.write(getData(), writeSheet);
        }
        /// 如果不用默认的写入第一个sheet的连点方式，千万别忘记finish 会帮忙关闭流
        excelWriter.finish();


        // 方法2 如果写到不同的sheet 同一个对象，需要new多个writeSheet
        excelWriter = EasyExcel.write(fileName, SimpleWrite.class).build();
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
            WriteSheet writeSheet1 = EasyExcel.writerSheet(i, "模板" + i).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            excelWriter.write(getData(), writeSheet1);
        }
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();

        // 方法3 如果写到不同的sheet 不同的对象
        fileName = "";
        // 这里 指定文件
        excelWriter = EasyExcel.write(fileName).build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
            writeSheet = EasyExcel.writerSheet(i, "模板" + i).head(SimpleWrite.class).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            excelWriter.write(getData(), writeSheet);
        }
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }
}
*/
