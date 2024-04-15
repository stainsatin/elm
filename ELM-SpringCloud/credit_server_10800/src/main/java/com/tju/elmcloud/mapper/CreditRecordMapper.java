package com.tju.elmcloud.mapper;

import com.tju.elmcloud.po.CreditRecord;
import com.tju.elmcloud.po.UsableCredit;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CreditRecordMapper {
    @Select("select COUNT(*) from creditrecord where createTime>#{today} and userId=#{userId} and ruleCode=#{ruleCode}")
    public Integer todaySignRecord(@Param("userId") String userId,@Param("ruleCode") int ruleCode,@Param("today") String today);

    @Select("select sum(credit) from usablecredit where deleted=0 and userId=#{userId}")
    public Integer queryAvailableCredit(@Param("userId") String userId,@Param("currentTime") String currentTime);

    @Select("select * from creditrecord where userId=#{userId}")
    public List<CreditRecord> queryAllCredit(@Param("userId") String userId);
    @Update("update usablecredit set deleted=1 where expiredTime < #{currentTime} and deleted=0 and userId=#{userId}")
    public Integer updataQueryAvailableCredit(@Param("userId") String userId,@Param("currentTime") String currentTime);
    @Update("update usablecredit set deleted=1 where id=#{id}")
    public Integer consumeCredit(@Param("id") int id);
    @Update("update usablecredit set credit=#{credit} where id=#{id}")
    public Integer updateCredit(@Param("id") int id,@Param("credit") int credit);
    public Integer insertCreditRecord(CreditRecord creditRecord);
    @Insert("insert into usablecredit values(null,#{userId},#{recordId},#{credit},#{createTime},#{expiredTime},0)")
    public Integer insertUsableCredit(@Param("userId") String userId,@Param("recordId") int recordId,
                                      @Param("credit") int credit,@Param("createTime") String createTime,
                                      @Param("expiredTime") String expiredTime);
    @Select("select * from usablecredit where userId=#{userId} and deleted=0 order by expiredTime")
    public List<UsableCredit> listUsableCredit(@Param("userId") String userId);
    @Insert("insert into reducecredit values(null,#{userId},#{recordId},#{usableId},#{credit},#{createTime},#{expiredTime})")
    public Integer insertReducecredit(@Param("userId") String userId,@Param("recordId") int recordId,
                                      @Param("usableId") int usableId,@Param("credit") int credit,
                                      @Param("createTime") String createTime,@Param("expiredTime") String expiredTime);
}
