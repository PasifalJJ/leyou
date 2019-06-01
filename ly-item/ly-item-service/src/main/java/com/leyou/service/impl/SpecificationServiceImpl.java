package com.leyou.service.impl;

import com.leyou.common.enums.ExceptionsEnums;
import com.leyou.common.exception.LyException;
import com.leyou.item.pojo.Specification;
import com.leyou.mapper.SpecificationMapper;
import com.leyou.service.SpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;
    /**
     * 根据分类cid查询规格参数
     * @param cid
     * @return
     */
    @Override
    public String querySpecification(Long cid) {
        Specification specification = specificationMapper.selectByPrimaryKey(cid);
        if (specification==null){
            throw new LyException(ExceptionsEnums.CATEGORY_HAVE_NOT_SPECIFICATION);
        }
        String specifications = specification.getSpecifications();
        if (StringUtils.isBlank(specifications)){
            throw new LyException(ExceptionsEnums.CANNOT_FIND_SPECIFICATION);
        }
        return specifications;
    }

    @Override
    public void saveSpecification(Specification specification) {
        int i = specificationMapper.insert(specification);
        if (i!=1){
            throw new LyException(ExceptionsEnums.SAVE_SPECIFICATION_ERROR);
        }
    }

    /**
     * 更新产品分类信息
     * @param specification
     * @return
     */
    @Override
    public void updateSpecification(Specification specification) {
        int i = specificationMapper.updateByPrimaryKey(specification);
        if (i!=1){
            throw new LyException(ExceptionsEnums.UPDATE_SPECIFICATION_ERROR);
        }
    }
}
