package com.codeway.base.service.backstage;

import com.codeway.pojo.QueryVO;
import com.codeway.pojo.base.LoginLog;
import com.querydsl.core.QueryResults;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginLogServiceTest {

    @Autowired
    private LoginLogService loginLogService;

    @Test
    public void findLoginLogByCondition() {
        QueryResults<LoginLog> dictByCondition = loginLogService.findLoginLogByCondition(new LoginLog(), new QueryVO());
        Assert.assertTrue(dictByCondition.getTotal() > 0);
    }

    @Test
    public void findById() {
        LoginLog byId = loginLogService.findById("1141704085730828289");
        Assert.assertNotNull(byId);
    }

    @Test
    public void save() {
        LoginLog loginLog = new LoginLog();
        loginLog.setId("1200629646627049472");
        loginLog.setBrowser("maxxxxx");
        loginLogService.save(loginLog);
    }

    @Test
    public void deleteBatch() {
        loginLogService.deleteBatch(Arrays.asList("1171411986963582977", "1171314643899600897"));
    }
}
