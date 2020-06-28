package com.gingost.easyexcel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.gingost.easyexcel.domain.Citydo;
import com.gingost.easyexcel.listener.CitydoListener;
import com.gingost.easyexcel.repository.CitydoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;

/**
 * @author:lezzy
 * @Date:2020/4/10 15:47
 */
@SpringBootTest
public class CitydoTest {
    @Autowired
    private CitydoRepository citydoRepository;

    @Test
    public void testCitydo() {
        String filePath = "C:/Users/TJliz/Desktop/test.xls";
        /*ExcelReader excelReader = EasyExcel.read(filePath).extraRead(CellExtraTypeEnum.COMMENT).extraRead(CellExtraTypeEnum.MERGE).build();
        ReadSheet readSheet=EasyExcel.readSheet(1).head(Citydo.class).registerReadListener(new  CitydoListener(citydoRepository)).build();
        excelReader.read(readSheet);
        excelReader.finish();
*/
       /* ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);*/

        ExcelReader excelReader = EasyExcel.read(filePath).extraRead(CellExtraTypeEnum.COMMENT).extraRead(CellExtraTypeEnum.MERGE).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).head(Citydo.class).headRowNumber(2).registerReadListener(new CitydoListener(citydoRepository)).build();
        excelReader.read(readSheet);
        excelReader.finish();
    }
}
