package com.youyd.gather.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.gather.pojo.Gather;
import com.youyd.gather.service.GatherService;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.Result;
import com.youyd.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 活动控制层
 * @author: LGG
 * @create: 2018-09-26 15:59
 **/
@RestController
@RequestMapping("/gather")
public class GatherDao {

    @Autowired
    private GatherService gatherService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findGatherByCondition(Gather gather, QueryVO queryVO){
	    IPage<Gather> gatherByCondition = gatherService.findGatherByCondition(queryVO);
	    return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),gatherByCondition);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @Cacheable(value="gathering",key="#id")
    @RequestMapping(value = "/{gatherId}", method = RequestMethod.GET)
    public Result findGatherByPrimaryKey(@PathVariable String gatherId) {
        Gather result = gatherService.findGatherByPrimaryKey(gatherId);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg(),result);
    }


    /**
     * 增加
     *
     * @param gathering
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result insertGather(@RequestBody Gather gather) {
        gatherService.insertGather(gather);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
    }

    /**
     * 修改
     *
     * @param gathering
     */
    @CacheEvict(value="gathering",key="#gathering.id")
    @PutMapping
    public Result updateByPrimaryKeySelective(Gather gather) {
        gatherService.updateByPrimaryKeySelective(gather);
        return new Result(true,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
    }

    /**
     * 删除
     *
     * @param id
     */
    @CacheEvict(value="gathering",key="#id")
    @DeleteMapping
    public Result deleteByTweetsId(@RequestBody List ids){
	    boolean br = gatherService.deleteByPrimaryKey(ids);
	    return new Result(br,StatusCode.OK.getCode(),StatusCode.OK.getMsg());
    }

}
