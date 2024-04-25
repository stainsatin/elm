package com.neusoft.elmboot.po;

public class CreditRecord {
    private Integer id;
    private String userId;
    private Integer ruleCode;
    private Integer eventId;
    private Integer credit;
    private String createTime;
    private String expiredTime;

    public CreditRecord(int id,String userId,int ruleCode,Integer eventId,int credit,String createTime,String expiredTime){
        this.id=id;
        this.ruleCode=ruleCode;
        this.userId=userId;
        this.createTime=createTime;
        if(expiredTime==null)
            this.expiredTime=expiredTime;
        else
            this.expiredTime=null;
        this.credit=credit;
        if(eventId!=null)
            this.eventId = eventId;
        else
            this.eventId=null;
    }

    public CreditRecord(int ruleCode,String userId,int credit,String createTime,String expiredTime){
        this.ruleCode=ruleCode;
        this.userId=userId;
        this.createTime=createTime;
        this.expiredTime=expiredTime;
        this.credit=credit;
    }
    public CreditRecord(int ruleCode,String userId,int credit,String createTime,String expiredTime,int eventId){
        this.ruleCode=ruleCode;
        this.userId=userId;
        this.createTime=createTime;
        this.expiredTime=expiredTime;
        this.credit=credit;
        this.eventId=eventId;
    }
    public CreditRecord(int ruleCode,String userId,int credit,String createTime,int eventId){
        this.ruleCode=ruleCode;
        this.userId=userId;
        this.createTime=createTime;
        this.credit=credit;
        this.eventId=eventId;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(int ruleCode) {
        this.ruleCode = ruleCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }
}
