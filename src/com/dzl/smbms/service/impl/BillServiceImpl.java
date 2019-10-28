package com.dzl.smbms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dzl.smbms.dao.BillMapper;
import com.dzl.smbms.pojo.Bill;
import com.dzl.smbms.pojo.Provider;
import com.dzl.smbms.service.BillService;

@Service
public class BillServiceImpl implements BillService {
	
	@Resource(name="billMapper")
	private BillMapper billMapper;
	
	@Override
	public List<Provider> getBillListByProductNameAndIdAndisPayment(Bill bill) {
		try {
			return billMapper.getBillListByProductNameAndIdAndisPayment(bill); // 调用dao方法实现查询
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Bill> selectBillList(String productName, int providerId, int isPayment, int currentPageNo,
			int pageSize) {
		List<Bill> billList=null;
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("productName", productName);
			map.put("providerId", providerId);
			map.put("isPayment", isPayment);
			map.put("pageSize", pageSize);
			map.put("currentPageNo", (currentPageNo-1)*pageSize);
			billList= billMapper.selectBillList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return billList;
	}

	@Override 
	public int selectBillCount(String productName, int providerId, int isPayment) {
		int count=0;
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("productName", productName);
			map.put("providerId", providerId);
			map.put("isPayment", isPayment);
			count=billMapper.selectBillCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		int count=0;
		try {
			if (billMapper.deleteByPrimaryKey(id)==1) {
				count=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}

	@Override
	public int insert(Bill record) {
		int count=0;
		try {
			if (billMapper.insert(record)==1) {
				count=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}

	@Override
	public int insertSelective(Bill record) {
		int count=0;
		try {
			if (billMapper.insertSelective(record)==1) {
				count=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}

	@Override
	public Bill selectByPrimaryKey(Integer id) {
		try {
			return billMapper.selectByPrimaryKey(id); // 调用dao方法实现查询
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(Bill record) {
		int count=0;
		try {
			if (billMapper.updateByPrimaryKeySelective(record)==1) {
				count=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}

	@Override
	public int updateByPrimaryKey(Bill record) {
		int count=0;
		try {
			if (billMapper.updateByPrimaryKey(record)==1) {
				count=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}


}
