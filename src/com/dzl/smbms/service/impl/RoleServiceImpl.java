package com.dzl.smbms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dzl.smbms.dao.RoleMapper;
import com.dzl.smbms.pojo.Role;
import com.dzl.smbms.pojo.User;
import com.dzl.smbms.service.RoleService;
import com.dzl.smbms.service.UserService;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource(name = "roleMapper")
	private RoleMapper roleMapper;

	@Override
	public List<Role> selectRoleList() {
		List<Role> roleList = null;
		try {
			roleList = roleMapper.selectRoleList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleList;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		int count = 0;
		try {
			if (roleMapper.deleteByPrimaryKey(id) == 1) {
				count = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int insert(Role record) {
		int count = 0;
		try {
			if (roleMapper.insert(record) == 1) {
				count = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int insertSelective(Role record) {
		int count = 0;
		if (roleMapper.insertSelective(record) == 1) {
			count = 1;
		}
		return count;
	}

	@Override
	public Role selectByPrimaryKey(Integer id) {
		Role role = null;
		try {
			role = roleMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		int count = 0;
		try {
			if (roleMapper.updateByPrimaryKeySelective(record) == 1) {
				count = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		int count = 0;
		try {
			if (roleMapper.updateByPrimaryKey(record) == 1) {
				count = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
