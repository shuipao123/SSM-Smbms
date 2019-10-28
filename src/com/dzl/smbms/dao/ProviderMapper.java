package com.dzl.smbms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dzl.smbms.pojo.Provider;

/**
 * 供应商接口
 * 
 * @author 微笑
 *
 */
public interface ProviderMapper {

	/**
	 * 查询全部供应商信息  分页
	 * 
	 * @return
	 */
	public List<Provider>  selectProviderList(Map<String, Object> map)throws Exception;

	/**
	 * 通过条件查询-供应商表记录数
	 * @param proName
	 * @param proCode
	 * @return
	 */
	public int selectProviderCount(@Param("proName")String proName,@Param("proCode") String proCode)throws Exception;
	
	/**
	 * 查询全部供应商名称
	 * 
	 * @return
	 */
	public List<Provider>  selectProviderName()throws Exception;
	
	/**
	 * 供应商编码验证是否存在
	 * @param proCode
	 * @return
	 */
	public Provider selectProviderCodeExist(String proCode)throws Exception;
	
	/**
	 * 根据供应商id删除指定供应商信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(Integer id)throws Exception;

	/**
	 * 为供应商添加一条完整数据
	 * 
	 * @param record
	 * @return
	 */
	public int insert(Provider record)throws Exception;

	/**
	 * 为供应商添加一条数据 可选择性
	 * 
	 * @param record
	 * @return
	 */
	public int insertSelective(Provider record);

	/**
	 * 根据供应商id查询指定供应商信息
	 * 
	 * @param id
	 * @return
	 */
	public Provider selectByPrimaryKey(Integer id)throws Exception;

	/**
	 * 根据id修改供应商不为空的字段
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(Provider record);
	
	/**
	 * 根据id修改供应商 如为空的字段在数据库中置为NULL。
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(Provider record);
}