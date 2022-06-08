package com.madao.user.service;

import com.madao.model.QueryVO;
import com.madao.model.dto.user.ResourceDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceServiceTest {

	@Autowired
	private ResourceService resourceService;

	@Test
	public void findResourceByCondition() {
		QueryVO queryVO = new QueryVO();
		List<ResourceDto> dictByCondition = resourceService.findResourceByCondition(new ResourceDto(), queryVO);
		Assert.assertTrue(dictByCondition.size() > 0);

	}

	@Test
	public void findResourceById() {
		ResourceDto resourceById = resourceService.findResourceById("1136504053649920002");
		Assert.assertNotNull(resourceById);
	}

	@Test
	public void findResourceByRoleIds() {
		Set<ResourceDto> resourceByRoleIds = resourceService.findResourceByRoleIds(List.of("1119477963140296706"));
		Assert.assertNotNull(resourceByRoleIds);
	}
}
