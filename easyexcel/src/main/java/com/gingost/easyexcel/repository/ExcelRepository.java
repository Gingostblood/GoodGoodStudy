package com.gingost.easyexcel.repository;

import com.gingost.easyexcel.domain.DemoExcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:lezzy
 * @Date:2020/4/9 15:02
 */
public interface ExcelRepository extends JpaRepository<DemoExcel, Integer>, JpaSpecificationExecutor<DemoExcel> {
}

