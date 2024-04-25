package com.neusoft.elmboot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.neusoft.elmboot.po.Business;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BusinessMapper {
	@Select("select * from business where orderTypeId=#{orderTypeId} order by businessId")
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
	
	@Select("select * from business where businessId=#{businessId}")
	public Business getBusinessById(Integer businessId);
	
	@Select("select * from business where businessName like CONCAT('%',#{businessName},'%')")
	public List<Business> listBusinessByName(String businessName);

	@Select("select * from business where businessAddress like CONCAT('%',#{businessAddress},'%')")
	public List<Business> listBusinessByAddress(String businessAddress);

	@Update("update business set hot=#{hot} where businessId=#{businessId}")
	public int updateBusinessHot(Integer businessId,Integer hot);

	@Select("select * from business where orderTypeId=#{orderTypeId} order by hot desc")
	public List<Business> listBusinessByOrderTypeIdByHot(Integer orderTypeId);
}
