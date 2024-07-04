package com.madao.base.service.backstage;

import com.madao.base.mapper.DictMapper;
import com.madao.base.repository.DictRepository;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.model.dto.base.DictDto;
import com.madao.model.entity.base.Dict;
import com.madao.utils.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典接口实现
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictService {

	private final DictRepository dictRepository;
	private final DictMapper dictMapper;

	/**
	 * 条件查询字典
	 *
	 * @param dictDto 字典实体
	 * @return List
	 */
	public Page<DictDto> findDictByCondition(DictDto dictDto, Pageable pageable) {
		Specification<Dict> condition = (root, query, builder) -> {
			List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotEmpty(dictDto.getName())) {
				predicates.add(builder.like(root.get("name"), "%" + dictDto.getName() + "%"));
			}
			if (StringUtils.isNotEmpty(dictDto.getParentId())) {
				predicates.add(builder.equal(root.get("parentId"), dictDto.getParentId()));
			}
			return query.where(predicates.toArray(new jakarta.persistence.criteria.Predicate[0])).getRestriction();
		};
		return dictRepository.findAll(condition, pageable).map(dictMapper::toDto);
	}

	/**
	 * 按照字典类型获取树形字典
	 *
	 * @param dictDto 字典实体
	 * @return List
	 */
	public List<DictDto> fetchDictTreeList(DictDto dictDto) {
		return dictRepository.findAllByType(dictDto.getType())
				.map(dictMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public DictDto findDictById(String resId) {
		return dictRepository.findById(resId)
				.map(dictMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}

	public void saveOrUpdate(DictDto dictDto) {
		if (StringUtils.isNotBlank(dictDto.getId())) {
			Dict tempDict = dictRepository.findById(dictDto.getId()).orElseThrow(ResourceNotFoundException::new);
			BeanUtil.copyProperties(tempDict, dictDto);
		}
		dictRepository.save(dictMapper.toEntity(dictDto));
	}

	public void deleteBatch(List<String> resId) {
		dictRepository.deleteBatch(resId);
	}

	/**
	 * 获取组字典类型，所有根节点
	 *
	 * @param dictDto 资源实体
	 * @return JsonData
	 */
	public List<DictDto> findIdNameTypeByParentId(DictDto dictDto) {
		log.info("参数：{}", dictDto);
		return dictRepository.findByParentId("0")
				.map(dictMapper::toDto)
				.orElseThrow(ResourceNotFoundException::new);
	}
}
