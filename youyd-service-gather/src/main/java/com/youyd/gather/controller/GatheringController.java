package com.youyd.gather.controller;

import com.youyd.gather.pojo.Gathering;
import com.youyd.gather.service.GatheringService;
import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/gathering")
public class GatheringController {

    @Autowired
    private GatheringService gatheringService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findGatheringByCondition() {
        List<Gathering> result = gatheringService.findGatheringByCondition();
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @Cacheable(value="gathering",key="#id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findGatheringByPrimaryKey(@PathVariable String id) {
        Gathering result = gatheringService.findGatheringByPrimaryKey(id);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
    }


    /**
     * 增加
     *
     * @param gathering
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result insertGathering(@RequestBody Gathering gathering) {
        gatheringService.insertGathering(gathering);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

    /**
     * 修改
     *
     * @param gathering
     */
    @CacheEvict(value="gathering",key="#gathering.id")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result updateByPrimaryKeySelective(@RequestBody Gathering gathering, @PathVariable String id) {
        gathering.setId(id);
        gatheringService.updateByPrimaryKeySelective(gathering);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

    /**
     * 删除
     *
     * @param id
     */
    @CacheEvict(value="gathering",key="#id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        gatheringService.deleteByPrimaryKey(id);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),null);
    }

}
