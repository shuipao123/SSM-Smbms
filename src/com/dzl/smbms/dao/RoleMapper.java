package com.dzl.smbms.dao;

import java.util.List;

import com.dzl.smbms.pojo.Role;

/**
 * 角色接口
 * 
 * @author 微笑
 *
 */
public interface RoleMapper {
	
	/**
	 * 查询全部角色
	 * @return
	 */
	public List<Role> selectRoleList()throws Exception;
	
	/**
	 * 根据角色id删除指定用户信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id)throws Exception;

	/**
	 * 添加一条完整数据
	 * 
	 * @param record
	 * @return
	 */
	int insert(Role record);

	/**
	 * 为角色添加一条数据 可选择性
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(Role record);

	/**
	 * 根据角色id查询指定用户信息
	 * 
	 * @param id
	 * @return
	 */
	Role selectByPrimaryKey(Integer id);

	/**
	 * 根据角色id修改用户不为空的字段信息
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Role record);

	/**
	 * 根据id修改角色信息 如为空的字段在数据库中置为NULL。
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Role record);
	
	
	
	
}