package com.wd.pydjc.elec.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wd.pydjc.bsd.model.MeasPoint;
import com.wd.pydjc.bsd.service.MeasPointService;
import com.wd.pydjc.common.utils.DateUtil;
import com.wd.pydjc.elec.service.CommonElectricService;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;

/**
 * 总体用能
 * @author zhulz
 */
@Api(tags = "总体用能")
@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private MeasPointService measPointService;
	
	@Autowired
	private CommonElectricService commonElectricService;
	
	private static SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@GetMapping("/chart")
	public JSONObject chart(String date) {
		
		

		JSONObject rows = new JSONObject();
		
		Map<Long,Map<String,BigDecimal>> chartMap = commonElectricService.getElectiricMeanByTime(DateUtil.getStartTime(""),DateUtil.getEndTime(""),"P","10m");
		Map<Long,Map<String,BigDecimal>> lastChartMap = commonElectricService.getElectiricMeanByTime(DateUtil.getStartTime(DateUtil.getLastDay()),DateUtil.getEndTime(DateUtil.getLastDay()),"P","10m");
		
		/**
		 * 负荷测点,通过配置得到总的测点信息
		 */
		List<MeasPoint> measPointList = measPointService.getDeviceTotleMeasPoint("P");

		/**
		 * 测点循环
		 */
		if(measPointList != null && measPointList.size()  > 0){
	          /**
	           * 当天
	           * 通过time 来计算一天的 每隔十分钟的数据
	           */
			  List<BigDecimal> listDate = new ArrayList<BigDecimal>();
	          List<String> listTime = new ArrayList<String>();
	          Long startTimeLong = DateUtil.getStartTimeLong(date);
	          for(long n = startTimeLong; n<= new Date().getTime(); n = n + 600000 ){
	        	  String key = formatTime.format(new Date(n));
	        	  
	        	  BigDecimal value = new BigDecimal(0);
	        	  for (int j = 0; j < measPointList.size(); j++) {
	        		  MeasPoint measPoint = measPointList.get(j);
	        		  Map<String,BigDecimal> mapObj = chartMap.get(measPoint.getId());
	        		  if(mapObj != null && mapObj.get(key) != null){
	        			  value = value.add(mapObj.get(key)); //循环加所有测点的数据
	        		  }
	        	  }
	        	  
	        	  listTime.add(key);
	        	  listDate.add(value);
	          }
	          
	          rows.put("time", listTime);
	          rows.put("value", listDate);
	          
	       
	          /**
	           * 前一天
	           * 通过time 来计算一天的 每隔十分钟的数据
	           */
	          List<BigDecimal> listDateLast = new ArrayList<BigDecimal>();
	          List<String> listTimeLast = new ArrayList<String>();
	          Long lastStartTimeLong = DateUtil.getStartTimeLong(formatDay.format(DateUtil.calculateByDate(new Date(),-1)));
	          long endTime = (lastStartTimeLong + 60 * 1000 * 60 * 24);
	          for(long n=lastStartTimeLong; n<= endTime; n = n + 600000 ){
	        	  String key = formatTime.format(new Date(n));

	        	  BigDecimal value = new BigDecimal(0);
	        	  for (int j = 0; j < measPointList.size(); j++) {
	        		  MeasPoint measPoint = measPointList.get(j);
	        		  Map<String,BigDecimal> mapObj = lastChartMap.get(measPoint.getId());
	        		  if(mapObj != null && mapObj.get(key) != null){
	        			  value = value.add(mapObj.get(key)); //循环加所有测点的数据
	        		  }
	        	  }
	        	  
	        	  listTimeLast.add(key);
	        	  listDateLast.add(value);
	          }
	          rows.put("lastTime", listTimeLast);
	          rows.put("lastValue", listDateLast);
		}
		
		List<Integer> listData = new ArrayList<Integer>();
		List<Integer> epLabel = new ArrayList<Integer>();
		for (int i = 0; i < 24 ; i++) {
			for (int j = 0; j < 6; j++) {
				listData.add(i);
			}
			epLabel.add(i);
		}
		rows.put("label", listData);
		rows.put("epLabel", epLabel);
		
		return rows;
	}
	
	
	
	@GetMapping("/chartEp")
	public JSONObject chartEp(Long deviceId,String date) {
		
		JSONObject rows = new JSONObject();
		
		Calendar cale = Calendar.getInstance();  
        String firstday;  
        // 获取前月的第一天  
        cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        firstday = formatDay.format(cale.getTime()); 
		Map<Long,Map<String,BigDecimal>> chartMap = commonElectricService.getElectiricEp(DateUtil.getMonthFirstDay(firstday), DateUtil.getMonthLastDay(firstday), "1440m");
		
	    //上个月
		String lastday;  
		Calendar lastCale = Calendar.getInstance();  
		lastCale.add(Calendar.MONTH, -1);  
		lastCale.set(Calendar.DAY_OF_MONTH, 1);  
		lastday = formatDay.format(lastCale.getTime());  
		Map<Long,Map<String,BigDecimal>> lastChartMap = commonElectricService.getElectiricEp(DateUtil.getMonthFirstDay(lastday), DateUtil.getMonthLastDay(lastday), "1440m");
	
		/**
		 * 有功电度,通过配置得到总的测点信息
		 */
		List<MeasPoint> measPointList = measPointService.getDeviceTotleMeasPoint("Ep");
	
		/**
		 * 测点循环
		 */
		if(measPointList != null && measPointList.size()  > 0){
			
			 //当月
			 List<BigDecimal> listDate = new ArrayList<BigDecimal>();
			 Calendar c = Calendar.getInstance();
			 int day = c.get(Calendar.DAY_OF_MONTH);
			 for (int i = 1; i <= day ; i++) {
				 
				 cale.set(Calendar.DAY_OF_MONTH, i);  
				 String key = formatDay.format(cale.getTime()) + " 00:00:00";
				 
				 BigDecimal value = new BigDecimal(0);
				 for (int j = 0; j < measPointList.size(); j++) {
					 MeasPoint measPoint = measPointList.get(j);
					
					 Map<String,BigDecimal> mapObj = chartMap.get(measPoint.getId());
					 
					 if(mapObj != null && mapObj.get(key) != null){
						 value = value.add(mapObj.get(key)) ;
					 }
				 }
				 listDate.add(value);
			 }
			 rows.put("currentData", listDate);
			 
			 
			 //上月
			 List<BigDecimal> lastData = new ArrayList<BigDecimal>();
			 for (int i = 1; i <= 31 ; i++) {
				 
				 lastCale.set(Calendar.DAY_OF_MONTH, i);  
				 String key = formatDay.format(lastCale.getTime()) + " 00:00:00";
				 
				 BigDecimal value = new BigDecimal(0);
				 for (int j = 0; j < measPointList.size(); j++) {
					 MeasPoint measPoint = measPointList.get(j);
					
					 Map<String,BigDecimal> mapObj = lastChartMap.get(measPoint.getId());
					 
					 if(mapObj != null && mapObj.get(key) != null){
						 value = value.add(mapObj.get(key)) ;
					 }
				 }
				 listDate.add(value);
			 }
			 rows.put("lastData", lastData);
		}

		List<Integer> label = new ArrayList<Integer>();
		for (int i = 1; i <= 31 ; i++) {
			label.add(i);
		}
		rows.put("label", label);
		
		return rows;
	}

}
