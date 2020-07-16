package com.gingost.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.fastjson.JSON;
import com.gingost.easyexcel.domain.Citydo;
import com.gingost.easyexcel.repository.CitydoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author:lezzy
 * @Date:2020/4/10 14:57
 */

public class CitydoListener extends AnalysisEventListener<Citydo> {
    private CitydoRepository citydoRepository;
    private static final int LONG_SIZE = 10;
    private static final Logger LOGGER = LoggerFactory.getLogger(CitydoListener.class);
    List<Citydo> list = new ArrayList<>();

    public CitydoListener(CitydoRepository citydoRepository) {
        this.citydoRepository = citydoRepository;
    }

    @Override
    public void invoke(Citydo citydo, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据：{}", JSON.toJSONString(citydo));
        list.add(citydo);
        if (list.size() >= LONG_SIZE) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        LOGGER.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        LOGGER.info("所有数据解析完成");
    }

    private void saveData() {
        LOGGER.info("解析到{}条数据", list.size());
        citydoRepository.saveAll(list);
        LOGGER.info("数据库插入完毕");
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        LOGGER.info("读取到了一条额外的信息:{}", JSON.toJSONString(extra));
        switch (extra.getType()) {
            case COMMENT:
                LOGGER.info("这是一条批注，在{}行，{}列,内容是{}", extra.getRowIndex(), extra.getColumnIndex(), extra.getText());
                break;
            case MERGE:
                LOGGER.info("这是一个合并的单元格，从第{}行，{}列到第{}行，{}列,内容是:{}",
                        extra.getFirstRowIndex(), extra.getFirstColumnIndex(), extra.getLastRowIndex(), extra.getLastColumnIndex(), extra.getText());
        }
    }
}
