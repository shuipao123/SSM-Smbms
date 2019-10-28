package com.dzl.smbms.pojo;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 * 
 * @author 微笑
 *
 */
public class Bill {
	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 账单编码
	 */
	private String billCode;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 商品描述
	 */
	private String productDesc;

	/**
	 * 商品单位
	 */
	private String productUnit;

	/**
	 * 商品数量
	 */
	private Integer productCount;

	/**
	 * 商品总额
	 */
	private Integer totalPrice;

	/**
	 * 是否支付（1：未支付 2：已支付）
	 */
	private Integer isPayment;

	/**
	 * 创建者（userId）
	 */
	private Integer createdBy;

	/**
	 * 创建时间
	 */
	private Date creationDate;

	/**
	 * 更新者（userId）
	 */
	private Integer modifyBy;

	/**
	 * 更新时间
	 */
	private Date modifyDate;

	/**
	 * 供应商ID
	 */
	private Integer providerId;
	
	
	private List<Provider> providers;

	
	private String proName;
	
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(Integer isPayment) {
		this.isPayment = isPayment;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
}
