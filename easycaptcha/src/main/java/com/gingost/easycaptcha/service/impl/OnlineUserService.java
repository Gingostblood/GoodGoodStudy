package com.gingost.easycaptcha.service.impl;

import antlr.StringUtils;
import com.gingost.easycaptcha.config.PropertiesConfig;
import com.gingost.easycaptcha.domain.User;
import com.gingost.easycaptcha.utli.RedisUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author mm
 * @Date 2019年10月26日21:56:27
 */
@Service
@Slf4j
@AllArgsConstructor
public class OnlineUserService {

    private final PropertiesConfig properties;
    private RedisUtils redisUtils;

    /**
     * 保存在线用户信息
     */
    public void save(User user, String token) {
        redisUtils.set(properties.getOnlineKey() + token, user, properties.getTokenValidityInSeconds() / 1000);
    }

    public User getOne(String s) {
        return (User) redisUtils.get(s);
    }
//
//    /**
//     * 查询全部数据
//     * @param filter /
//     * @param pageable /
//     * @return /
//     */
//    public Map<String,Object> getAll(String filter, Pageable pageable){
//        List<User> onlineUsers = getAll(filter);
//        return PageUtil.toPage(
//                PageUtil.toPage(pageable.getPageNumber(),pageable.getPageSize(),onlineUsers),
//                onlineUsers.size()
//        );
//    }
//
//    /**
//     * 查询全部数据，不分页
//     * @param filter /
//     * @return /
//     */
//    public List<OnlineUser> getAll(String filter){
//        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
//        Collections.reverse(keys);
//        List<OnlineUser> onlineUsers = new ArrayList<>();
//        for (String key : keys) {
//            OnlineUser onlineUser = (OnlineUser) redisUtils.get(key);
//            if(StringUtils.isNotBlank(filter)){
//                if(onlineUser.toString().contains(filter)){
//                    onlineUsers.add(onlineUser);
//                }
//            } else {
//                onlineUsers.add(onlineUser);
//            }
//        }
//        onlineUsers.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
//        return onlineUsers;
//    }
//
//    /**
//     * 踢出用户
//     * @param key /
//     * @throws Exception /
//     */
//    public void kickOut(String key) throws Exception {
//        key = properties.getOnlineKey() + EncryptUtils.desDecrypt(key);
//        redisUtils.del(key);
//    }
//
//    /**
//     * 退出登录
//     * @param token /
//     */
//    public void logout(String token) {
//        String key = properties.getOnlineKey() + token;
//        redisUtils.del(key);
//    }
//
//    /**
//     * 导出
//     * @param all /
//     * @param response /
//     * @throws IOException /
//     */
//    public void download(List<OnlineUser> all, HttpServletResponse response) throws IOException {
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (OnlineUser user : all) {
//            Map<String,Object> map = new LinkedHashMap<>();
//            map.put("用户名", user.getUserName());
//            map.put("岗位", user.getJob());
//            map.put("登录IP", user.getIp());
//            map.put("登录地点", user.getAddress());
//            map.put("浏览器", user.getBrowser());
//            map.put("登录日期", user.getLoginTime());
//            list.add(map);
//        }
//        FileUtil.downloadExcel(list, response);
//    }
//
//    /**
//     * 查询用户
//     * @param key /
//     * @return /
//     */
//    public OnlineUser getOne(String key) {
//        return (OnlineUser)redisUtils.get(key);
//    }
//
//    /**
//     * 检测用户是否在之前已经登录，已经登录踢下线
//     * @param userName 用户名
//     */
//    public void checkLoginOnUser(String userName, String igoreToken){
//        List<OnlineUser> onlineUsers = getAll(userName);
//        if(onlineUsers ==null || onlineUsers.isEmpty()){
//            return;
//        }
//        for(OnlineUser onlineUser:onlineUsers){
//            if(onlineUser.getUserName().equals(userName)){
//                try {
//                    String token =EncryptUtils.desDecrypt(onlineUser.getKey());
//                    if(StringUtils.isNotBlank(igoreToken)&&!igoreToken.equals(token)){
//                        this.kickOut(onlineUser.getKey());
//                    }else if(StringUtils.isBlank(igoreToken)){
//                        this.kickOut(onlineUser.getKey());
//                    }
//                } catch (Exception e) {
//                    log.error("checkUser is error",e);
//                }
//            }
//        }
//    }

}
