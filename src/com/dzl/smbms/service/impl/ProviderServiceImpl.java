package com.dzl.smbms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.dzl.smbms.dao.ProviderMapper;
import com.dzl.smbms.pojo.Provider;
import com.dzl.smbms.service.ProviderService;

/**
 * 供应商业务实现
 * @author 微笑
 *
 */
@Service
public class ProviderServiceImpl implements ProviderService {
	
	@Resource(name="providerMapper")
	private ProviderMapper providerMapper;
	
	@Override //查询全部供应商信息
	public List<Provider> getProviderList(String proName, String proCode, int currentPageNo, int pageSize) {
		List<Provider> providerList=null;
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("proName", proName);
			map.put("proCode", proCode);
			map.put("pageSize", pageSize);
			map.put("currentPageNo", (currentPageNo-1)*pageSize);
			providerList= providerMapper.selectProviderList(map);
			for (Provider provider : providerList) {
				System.err.println(provider.getProName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return providerList;
	}

	@Override  //通过条件查询-供应商表记录数
	public int selectProviderCount(String proName, String proCode) {
		int count=0;
		try {
			count=providerMapper.selectProviderCount(proName, proCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override //查询全部供应商名称
	public List<Provider> selectProviderName() {
		List<Provider> providerList=null;
		try {
			providerList=providerMapper.selectProviderName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return providerList;
	}

	@Override
	public Provider selectProviderCodeExist(String proCode) {
		Provider provider=null;
		try {
			provider=providerMapper.selectProviderCodeExist(proCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return provider;
	}

	
	
	@Override //根据id删除供应商信息
	public int deleteByPrimaryKey(Integer id) {
		int count=0;
		try {
			if (providerMapper.deleteByPrimaryKey(id)==1) {
				count=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int insert(Provider record) {
		int count=0;
		try {
			if (providerMapper.insert(record)==1) {
				count=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int insertSelective(Provider record) {
		int count=0;
		try {
			if (providerMapper.insertSelective(record)==1) {
				count=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Provider selectByPrimaryKey(Integer id) {
		Provider provider=null;
		try {
			provider = providerMapper.selectByPrimaryKey(id); //调用dao方法实现查询
		} catch (Exception e) {
			e.printStackTrace();
		}
		return provider;
	}

	@Override
	public int updateByPrimaryKeySelective(Provider record) {
		int count=0;
		try {
			if (providerMapper.updateByPrimaryKeySelective(record)==1) {
				count=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}

	@Override
	public int updateByPrimaryKey(Provider record) {
		int count=0;
		try {
			if (providerMapper.updateByPrimaryKey(record)==1) {
				count=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}

	
	
	

}
