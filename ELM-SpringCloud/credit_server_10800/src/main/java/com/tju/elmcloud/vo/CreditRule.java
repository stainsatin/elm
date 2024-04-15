package com.tju.elmcloud.vo;

public class CreditRule {
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



    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCredit() {
        return credit;
    }

    public Double getFormula() {
        return formula;
    }

    public void setFormula(Double formula) {
        this.formula = formula;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getDailyCap() {
        return dailyCap;
    }

    public void setDailyCap(Integer dailyCap) {
        this.dailyCap = dailyCap;
    }

    public Integer getTotCap() {
        return totCap;
    }

    public void setTotCap(Integer totCap) {
        this.totCap = totCap;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getLifespan() {
        return lifespan;
    }

    public void setLifespan(Integer lifespan) {
        this.lifespan = lifespan;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
