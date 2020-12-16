package com.madaoo.base.service.backstage;

import com.madaoo.model.dto.base.LoginLogDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginLogServiceTest {

	@Autowired
	private com.madaoo.base.service.backstage.LoginLogService loginLogService;

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

}
