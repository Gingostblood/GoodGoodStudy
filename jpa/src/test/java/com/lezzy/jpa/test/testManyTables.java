package com.lezzy.jpa.test;

import com.lezzy.jpa.dto.ViewInfo;
import com.lezzy.jpa.entity.AddressInfo;
import com.lezzy.jpa.jpa.AddressJPA;
import com.lezzy.jpa.jpa.UserInfoJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class testManyTables {
    @Autowired
    private UserInfoJPA userInfoJPA;
    @Autowired
    private AddressJPA addressJPA;

    @Test
    public void testAddressInfo() {
        List<AddressInfo> all = addressJPA.findAll();
        System.out.println(all);
    }

    @Test
    public void testTablesQuery() {
        List<ViewInfo> viewInfo = userInfoJPA.findViewInfo();
        System.out.println(viewInfo);
    }
}
