package com.gingost.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.fastjson.JSON;
import com.gingost.easyexcel.domain.DemoExcel;
import com.gingost.easyexcel.repository.ExcelRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author:lezzy
 * @Date:2020/4/9 15:03
 */
//此类不能被spring管理


public class DemoListener extends AnalysisEventListener<DemoExcel> {
    private ExcelRepository excelRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoListener.class);
    private static final int BATCH_COUNT = 300;
    int i=0;
    List<DemoExcel> list = new ArrayList<>();

    public DemoListener(ExcelRepository excelRepository) {
        this.excelRepository = excelRepository;
    }

    @Override
    public void invoke(DemoExcel demoExcel, AnalysisContext analysisContext) {
        i++;
        if(i<=2){
            return;
        }
        if(demoExcel.getTitle() == null){
            return;
        }
        System.out.println("=========================="+demoExcel);
        LOGGER.info("解析到的数据{}", JSON.toJSONString(demoExcel));
        list.add(demoExcel);
        if (list.size() >= BATCH_COUNT) {
            saveList();
            list.clear();
        }
    }

    //确保剩余的数据不会被遗漏
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveList();
        LOGGER.info("所有数据解析完毕");
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        LOGGER.info("读取到了一条额外信息:{}", JSON.toJSONString(extra));
        switch (extra.getType()) {
            case MERGE:
                LOGGER.info("额外信息覆盖了一个区间,在firstRowIndex:{},firstColumnIndex;{},lastRowIndex:{},lastColumnIndex:{}",
                        extra.getFirstRowIndex(), extra.getFirstColumnIndex(), extra.getLastRowIndex(),
                        extra.getLastColumnIndex());
                break;
        }
    }

    private void saveList() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        excelRepository.saveAll(list);
        LOGGER.info("存储数据库成功！");

    }

    //解析表头数据
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        LOGGER.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }

}
