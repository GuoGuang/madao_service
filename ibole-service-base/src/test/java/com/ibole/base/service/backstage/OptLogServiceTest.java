package com.ibole.base.service.backstage;

import com.ibole.config.CustomPageRequest;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.OptLog;
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
public class OptLogServiceTest {

    @Autowired
    private OptLogService optLogService;


    @Test
    public void findOptLogByCondition() {
        QueryVO queryVO = new QueryVO();
        queryVO.setPageable(new CustomPageRequest(1, 10));
        Page<OptLog> dictByCondition = optLogService.findOptLogByCondition(new OptLog(), queryVO);
        Assert.assertTrue(dictByCondition.getTotalElements() > 0);
    }

    @Test
    public void findOptLogByPrimaryKey() {
        OptLog byId = optLogService.findById("1157967746300456962");
        Assert.assertNotNull(byId);
    }

    @Test
    public void insertOptLog() {
        OptLog optLog = new OptLog();
        optLog.setId("1200629646627049472");
        optLog.setBrowser("maxxxxx");
        optLogService.insertOptLog(optLog);
    }

    @Test
    public void deleteBatch() {
        optLogService.deleteBatch(Arrays.asList("1157660486592466945", "1157672932308783105"));
    }
}
