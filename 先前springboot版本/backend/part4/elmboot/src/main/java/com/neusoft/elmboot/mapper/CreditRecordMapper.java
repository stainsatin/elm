package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.CreditRecord;
import com.neusoft.elmboot.po.UsableCredit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CreditRecordMapper {
    @Select("select COUNT(*) from creditrecord where createTime>#{today} and userId=#{userId} and ruleCode=#{ruleCode}")
    public int todaySignRecord(String userId,Integer ruleCode,String today);

    @Select("select IFNULL(sum(credit),0) from usablecredit where deleted=0 and userId=#{userId}")
    public int queryAvailableCredit(String userId,String currentTime);

    @Select("select * from creditrecord where userId=#{userId}")
    public List<CreditRecord> queryAllCredit(String userId);
    @Update("update usablecredit set deleted=1 where expiredTime < #{currentTime} and deleted=0 and userId=#{userId}")
    public int updataQueryAvailableCredit(String userId,String currentTime);
    @Update("update usablecredit set deleted=1 where id=#{id}")
    public int consumeCredit(Integer id);
    @Update("update usablecredit set credit=#{credit} where id=#{id}")
    public int updateCredit(Integer id,Integer credit);
    
    public int insertCreditRecord(CreditRecord creditRecord);
    @Insert("insert into usablecredit values(null,#{userId},#{recordId},#{credit},#{createTime},#{expiredTime},0)")
    public int insertUsableCredit(String userId,Integer recordId,Integer credit,String createTime,String expiredTime);
    @Select("select * from usablecredit where userId=#{userId} and deleted=0 order by expiredTime")
    public List<UsableCredit> listUsableCredit(String userId);
    @Insert("insert into reducecredit values(null,#{userId},#{recordId},#{usableId},#{credit},#{createTime},#{expiredTime})")
    public int insertReducecredit(String userId,Integer recordId,Integer usableId,Integer credit,String createTime,String expiredTime);
}
