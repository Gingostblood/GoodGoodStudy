package com.lezzy.jpa.dto;

import com.lezzy.jpa.entity.AddressInfo;

import com.lezzy.jpa.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
public class ViewInfo {
    private UserInfo userInfo;
    private AddressInfo addressInfo;

    public ViewInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        this.addressInfo = new AddressInfo();
    }

    public ViewInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
        this.userInfo = new UserInfo();
    }
}
