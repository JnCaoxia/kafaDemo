/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.task;

import com.alibaba.fastjson.JSON;
import com.sunney.service.KafkaService;
import com.sunney.service.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 类ProducerTasl.java的实现描述：TODO 类实现描述
 * 
 * @author nazi
 */
@Component
public class ProducerTast {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    KafkaService     kafkaService;

    @Scheduled(fixedRate = 2000)
    public void taskStart() {
        UserDto userDto = new UserDto();
        userDto.setUserId(110);
        userDto.setGmtCeate(new Date());
        userDto.setUserName("韩梅梅");

        UserDto userDto1 = new UserDto();
        userDto1.setUserId(111);
        userDto1.setGmtCeate(new Date());
        userDto1.setUserName("李雷");
        List<Long> longList = new ArrayList<>();
        longList.add(1L);
        longList.add(2L);
        userDto1.setUserList(longList);
        kafkaService.sendUserInfo("myTopic", JSON.toJSONString(userDto));
        kafkaService.sendUserInfo("myTopic", JSON.toJSONString(userDto1));
        System.out.println("=============================:email");
    }

    /**
     * 随机获取发送邮箱
     * 
     * @return
     */
    private String getemailSender() {
        String[] sender = new String[] { "mytopic", "sunneytopic" };
        Random ra = new Random();
        int val = ra.nextInt(sender.length);
        return sender[val];
    }

}
