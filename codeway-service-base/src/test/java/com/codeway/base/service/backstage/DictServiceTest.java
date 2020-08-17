package com.codeway.base.service.backstage;

import com.codeway.model.QueryVO;
import com.codeway.model.dto.base.DictDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictServiceTest {

    @Autowired
    private DictService dictService;

    @Test
    public void findDictByCondition() {
	    QueryVO queryVO = new QueryVO();
	    Page<DictDto> dictByCondition = dictService.findDictByCondition(new DictDto(), null);
        Assert.assertTrue(dictByCondition.getTotalElements() > 0);
    }

    @Test
    public void findDictById() {
	    DictDto dictByCondition = dictService.findDictById("1133978689470599168");
        System.out.println(dictByCondition);
    }

    @Test
    public void saveOrUpdate() {
//        DictDto dictById = dictService.findDictById("1");
//        dictById.setName("foo");
//        dictService.saveOrUpdate(dictById);

	    DictDto insertDict = new DictDto();
        insertDict.setName("bar");
        insertDict.setCode("001");
        insertDict.setParentId("0");
        insertDict.setDescription("0");
        insertDict.setType("0");
        dictService.saveOrUpdate(insertDict);
    }

    @Test
    public void deleteBatch() {
        dictService.deleteBatch(Arrays.asList("1133978690313654272", "1133978690737278976"));
    }

    @Test
    public void findIdNameTypeByParentId() {

	    DictDto dict = new DictDto();
        dict.setId("1133978877252415931");
	    List<DictDto> idNameTypeByParentId = dictService.findIdNameTypeByParentId(dict);

        Assert.assertNotNull(idNameTypeByParentId);
    }
}
