package com.dzl.smbms.dao;

import java.util.List;
import java.util.Map;

import com.dzl.smbms.pojo.Bill;
import com.dzl.smbms.pojo.Provider;

/**
 * 账单接口
 * 
 * @author 微笑
 *
 */
public interface BillMapper {

	/**
	 * 按商品名（模糊查询） 供应商id 是否付款 进行查询 下面的订单
	 * 
	 * @param bill
	 * @return
	 */
	List<Provider> getBillListByProductNameAndIdAndisPayment(Bill bill);

	/**
	 * 按商品名（模糊查询） 供应商id 是否付款 进行查询 分页
	 * 
	 * @return
	 */
	public List<Bill> selectBillList(Map<String, Object> map) throws Exception;

	/**
	 * 通过条件查询-订单表记录数
	 * 
	 * @param map
	 * @return
	 */
	public int selectBillCount(Map<String, Object> map) throws Exception;

	int deleteByPrimaryKey(Integer id);

	int insert(Bill record);

	int insertSelective(Bill record);

	Bill selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Bill record);

	int updateByPrimaryKey(Bill record);
}