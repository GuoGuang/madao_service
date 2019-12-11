package com.ibole.user.service;

import com.ibole.config.CustomPageRequest;
import com.ibole.pojo.QueryVO;
import com.ibole.pojo.user.Role;
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
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void findRuleByCondition() {
        QueryVO queryVO = new QueryVO();
        queryVO.setPageable(new CustomPageRequest(1, 10));
        Page<Role> dictByCondition = roleService.findRuleByCondition(new Role(), queryVO);
        Assert.assertTrue(dictByCondition.getTotalElements() > 0);
    }

    @Test
    public void findRoleById() {
        Role roleById = roleService.findRoleById("1119477963140296706");
        Assert.assertNotNull(roleById);
    }

    @Test
    public void deleteByIds() {
        roleService.deleteByIds(Arrays.asList("1200691721256701952"));
    }

    @Test
    public void saveOrUpdate() {
        Role role = new Role();
        role.setRoleName("bar");
        role.setRoleCode("bar");
        roleService.saveOrUpdate(role);
    }

    @Test
    public void findUsersOfRole() {
    }
}
