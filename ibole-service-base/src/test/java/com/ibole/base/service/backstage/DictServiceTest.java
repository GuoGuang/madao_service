package com.ibole.base.service.backstage;

import com.ibole.pojo.QueryVO;
import com.ibole.pojo.base.Dict;
import com.querydsl.core.QueryResults;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        QueryResults<Dict> dictByCondition = dictService.findDictByCondition(new Dict(), queryVO);
        Assert.assertTrue(dictByCondition.getTotal() > 0);
    }

    @Test
    public void findDictById() {
        Dict dictByCondition = dictService.findDictById("1133978689470599168");
        System.out.println(dictByCondition);
    }

    @Test
    public void saveOrUpdate() {
//        Dict dictById = dictService.findDictById("1");
//        dictById.setName("foo");
//        dictService.saveOrUpdate(dictById);

        Dict insertDict = new Dict();
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

        Dict dict = new Dict();
        dict.setId("1133978877252415931");
        List<Dict> idNameTypeByParentId = dictService.findIdNameTypeByParentId(dict);

        Assert.assertNotNull(idNameTypeByParentId);
    }
}
