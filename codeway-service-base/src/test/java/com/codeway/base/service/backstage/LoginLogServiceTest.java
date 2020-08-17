package com.codeway.base.service.backstage;

import com.codeway.model.dto.base.LoginLogDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginLogServiceTest {

    @Autowired
    private LoginLogService loginLogService;

    @Test
    public void findLoginLogByCondition() {
	    Page<LoginLogDto> dictByCondition = loginLogService.findLoginLogByCondition(new LoginLogDto(), null);
        Assert.assertTrue(dictByCondition.getTotalElements() > 0);
    }

    @Test
    public void findById() {
	    LoginLogDto byId = loginLogService.findById("1141704085730828289");
        Assert.assertNotNull(byId);
    }

    @Test
    public void save() {
	    LoginLogDto loginLog = new LoginLogDto();
        loginLog.setId("1200629646627049472");
        loginLog.setBrowser("maxxxxx");
        loginLogService.save(loginLog);
    }

    @Test
    public void deleteBatch() {
        loginLogService.deleteBatch(Arrays.asList("1171411986963582977", "1171314643899600897"));
    }
}
