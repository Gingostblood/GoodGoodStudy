package com.gingost.easyexcel.repository;

import com.gingost.easyexcel.domain.Citydo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:lezzy
 * @Date:2020/4/10 14:52
 */
public interface CitydoRepository extends JpaRepository<Citydo,Integer>, JpaSpecificationExecutor<Citydo> {
}
