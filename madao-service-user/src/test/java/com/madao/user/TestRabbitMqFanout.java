package com.madao.user;

import com.madao.utils.RabbitUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * RabbitMqFanout测试
 *
 * @author GuoGuang
 * @created by guoguang0536@gmail.com / 1831682775@qq.com
 * @date 2021/06/03/ 10:05:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRabbitMqFanout {

    @Autowired
    private RabbitUtil rabbitUtil;

    @Test
    public void testRabbitFanout() {
        rabbitUtil.sendFanoutToQueue("{\"id\":\"1\",\"name\":\"2\"}", "productLine", "");
    }
}
