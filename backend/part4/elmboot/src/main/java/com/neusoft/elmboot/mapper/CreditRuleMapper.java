package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.CreditRulePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CreditRuleMapper {

    @Select("select * from creditrule where id=#{id} and state=1")
    public CreditRulePo getRule(Integer id);
    @Select("select * from creditrule")
    public List<CreditRulePo> queryAllCreditRule();
    @Update("update creditrule set ruleCode=#{ruleCode} ,type=#{type},priority=#{priority},credit=#{credit},formula=#{formula},dailyCap=#{dailyCap},totCap=#{totCap},startTime=#{startTime},endTime=#{endTime} where id=#{id}")
    public Integer updateCreditRule(CreditRulePo creditRulePo);
}
