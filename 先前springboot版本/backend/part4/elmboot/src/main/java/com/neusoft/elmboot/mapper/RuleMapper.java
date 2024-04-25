package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.CreditRulePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RuleMapper {

    @Select("select * from creditrule where id=#{ruleId}")
    CreditRulePo getRule(Integer ruleId);
}
