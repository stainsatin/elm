package com.neusoft.elmboot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.neusoft.elmboot.po.Business;
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
	
}
