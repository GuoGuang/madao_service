package com.codeway.user.service;

import com.codeway.model.QueryVO;
import com.codeway.model.dto.user.RoleDto;
import com.codeway.model.pojo.user.Role;
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
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void findRuleByCondition() {
	    QueryVO queryVO = new QueryVO();
	    QueryResults<Role> dictByCondition = roleService.findRuleByCondition(new RoleDto(), queryVO);
        Assert.assertTrue(dictByCondition.getTotal() > 0);
    }

    @Test
    public void findRoleById() {
	    RoleDto roleById = roleService.findRoleById("1119477963140296706");
        Assert.assertNotNull(roleById);
    }

    @Test
    public void deleteByIds() {
        roleService.deleteByIds(Arrays.asList("1200691721256701952"));
    }

    @Test
    public void saveOrUpdate() {
	    RoleDto role = new RoleDto();
	    role.setRoleName("bar");
	    role.setCode("bar");
	    roleService.saveOrUpdate(role);
    }

    @Test
    public void findUsersOfRole() {
    }
}
