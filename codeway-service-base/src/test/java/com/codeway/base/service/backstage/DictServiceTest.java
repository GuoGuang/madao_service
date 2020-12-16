package com.madaoo.base.service.backstage;

import com.madaoo.model.QueryVO;
import com.madaoo.model.dto.base.DictDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictServiceTest {

	@Autowired
	private com.madaoo.base.service.backstage.DictService dictService;

    @Test
    public void findDictByCondition() {
	    QueryVO queryVO = new QueryVO();
	    Page<DictDto> dictByCondition = dictService.findDictByCondition(new DictDto(), null);
        Assert.assertTrue(dictByCondition.getTotalElements() > 0);
    }

    @Test
    public void findIdNameTypeByParentId() {

	    DictDto dict = new DictDto();
        dict.setId("1133978877252415931");
	    List<DictDto> idNameTypeByParentId = dictService.findIdNameTypeByParentId(dict);

        Assert.assertNotNull(idNameTypeByParentId);
    }
}
