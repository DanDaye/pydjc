package com.wd.pydjc.sys.model;

import com.wd.pydjc.sys.model.BaseEntity;

/**
 * 客户
 * 
 */
public class Customer extends BaseEntity<Long> {

	private static final long serialVersionUID = -6525908145032868837L;

	private String name;
	private String nameAbbr;
	private String nameEn;
	private String code;
	private String address;
	private String tel;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameAbbr() {
		return nameAbbr;
	}
	public void setNameAbbr(String nameAbbr) {
		this.nameAbbr = nameAbbr;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
