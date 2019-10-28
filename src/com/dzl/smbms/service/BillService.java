package com.dzl.smbms.service;

import java.util.List;

import com.dzl.smbms.pojo.Bill;
import com.dzl.smbms.pojo.Provider;

public interface BillService {

	/**
	 * 按商品名（模糊查询） 供应商id 是否付款 进行查询
	 * 
	 * @param bill
	 * @return
	 */
	List<Provider> getBillListByProductNameAndIdAndisPayment(Bill bill);

	/**
	 * 按商品名（模糊查询） 供应商id 是否付款 进行查询 -------- 分页
	 * 
	 * @return
	 */
	public List<Bill> selectBillList(String productName, int providerId, int isPayment,int currentPageNo, int pageSize);

	/**
	 * 通过条件查询-订单表记录数
	 * 
	 * @param proName
	 * @param proCode
	 * @return
	 */
	public int selectBillCount(String productName, int providerId, int isPayment);
	
	/**
	 * 根据id删除订单信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 添加一条完整数据
	 * 
	 * @param record
	 * @return
	 */
	int insert(Bill record);

	/**
	 * 添加一条数据 可选择性 如果不输入 为空
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(Bill record);

	/**
	 * 根据id查询订单信息
	 * 
	 * @param id
	 * @return
	 */
	Bill selectByPrimaryKey(Integer id);

	/**
	 * 根据用户id修改用户不为空的字段信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Bill record);

	/**
	 * 根据用户id修改用户信息 如为空的字段在数据库中置为NULL。
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Bill record);
}
