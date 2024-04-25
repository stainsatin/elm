package com.neusoft.elmboot.po;

import lombok.Data;

@Data
public class CreditRulePo {
    private Integer id;
    private String ruleCode;
    private Integer type;
    private Integer priority;
    private Integer credit;
    private Double formula;
    private Integer dailyCap;
    private Integer totCap;
    private String startTime;
    private String endTime;
    private Integer lifespan;
    private Integer state;

    public CreditRulePo(Integer id,String ruleCode,Integer type,Integer priority,Integer credit,Double formula,Integer dailyCap,Integer totCap,String startTime,String endTime,Integer lifespan,Integer state){
        this.id=id;
        this.ruleCode=ruleCode;
        this.type=type;
        this.priority=priority;
        this.credit=credit;
        this.formula=formula;
        this.dailyCap=dailyCap;
        this.totCap=totCap;
        this.startTime=startTime;
        this.endTime=endTime;
        this.lifespan=lifespan;
        this.state=state;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public Integer getType() {
        return type;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getCredit() {
        return credit;
    }

    public Integer getId() {
        return id;
    }

    public Double getFormula() {
        return formula;
    }

    public Integer getDailyCap() {
        return dailyCap;
    }

    public Integer getTotCap() {
        return totCap;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public Integer getLifespan() {
        return lifespan;
    }

    public Integer getState() {
        return state;
    }
}
