package com.madao.base.service.backstage;

import com.madao.model.QueryVO;
import com.madao.model.dto.base.OptLogDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OptLogServiceTest {

    @Autowired
    private com.madao.base.service.backstage.OptLogService optLogService;

    @Test
    public void findOptLogByCondition() {
        QueryVO queryVO = new QueryVO();
        Page<OptLogDto> dictByCondition = optLogService.findOptLogByCondition(new OptLogDto(), null);
        Assert.assertTrue(dictByCondition.getTotalElements() > 0);
    }

    @Test
    public void findOptLogByPrimaryKey() {
        OptLogDto byId = optLogService.findById("1157967746300456962");
        Assert.assertNotNull(byId);
    }
}
