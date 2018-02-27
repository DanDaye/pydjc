package com.wd.pydjc.bsd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.wd.pydjc.bsd.model.MeasType;

@Mapper
public interface MeasTypeDao {

	@Select("select * from bsd_meas_type t ")
	List<MeasType> listAll();
	
	
	List<MeasType> getChildList(@Param("params") Map<String, Object> params);

}
