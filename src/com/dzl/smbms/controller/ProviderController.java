package com.dzl.smbms.controller;

import com.alibaba.fastjson.JSONArray;
import com.dzl.smbms.pojo.Provider;
import com.dzl.smbms.pojo.User;
import com.dzl.smbms.service.ProviderService;
import com.dzl.smbms.tools.Constants;
import com.dzl.smbms.tools.PageSupport;
import com.mysql.jdbc.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    private Logger logger = Logger.getLogger(ProviderController.class);

    @Resource
    private ProviderService providerService;

    // 显示全部供应商信息
    @RequestMapping("/providerlist.html")
    public String providerlist(Model model, @RequestParam(value = "queryProName", required = false) String queryProName,
                               @RequestParam(value = "queryProCode", required = false) String queryProCode,
                               @RequestParam(value = "pageIndex", required = false) String pageIndex) {
        logger.debug("providerlist---------------------------------------");
        List<Provider> providerList = null;
        // 设置页面容量
        int pageSize = Constants.pageSize;
        // 当前页码
        int currentPageNo = 1;
        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                return "redirect:/provider/syserror.html";
            }
        }
        // 总数量（表）
        int totalCount = providerService.selectProviderCount(queryProName, queryProCode);
        // 总页数
        PageSupport pages = new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        int totalPageCount = pages.getTotalPageCount();
        // 控制首页和尾页
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }
        providerList = providerService.getProviderList(queryProName, queryProCode, currentPageNo, pageSize);
        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProName", queryProName);
        model.addAttribute("queryProCode", queryProCode);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "jsp/providerlist";
    }

    // 错误
    @RequestMapping(value = "/syserror.html")
    public String sysError() {
        return "syserror";
    }

    // 添加供应商
    @RequestMapping("/provideradd.html")
    public String provideradd() {
        return "jsp/provideradd";
    }

    // 根据id查询指定供应商信息 
    @RequestMapping(value = "/provider.do", params = "method=view")
    public String view(@RequestParam("proid") String proid, Map<String, Object> map) {
        Provider provider = providerService.selectByPrimaryKey(Integer.valueOf(proid));
        map.put("provider", provider);
        return "jsp/providerview";
    }

    // 根据id查询指定供应商信息 跳修改页面
    @RequestMapping(value = "/provider.do", params = "method=modify")
    public String providermodify(@RequestParam("proid") String proid, Map<String, Object> map) {
        Provider provider = providerService.selectByPrimaryKey(Integer.valueOf(proid));
        map.put("provider", provider);
        return "jsp/providermodify";
    }

    // 修改供应商
    @RequestMapping(value = "/providermodifysave.html", method = RequestMethod.POST)
    public String modifyProviderSave(Provider provider, HttpSession session) {
        logger.debug("modifyProviderSave providerId===================== " + provider.getId());
        provider.setModifyBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
        provider.setModifyDate(new Date());
        if (providerService.updateByPrimaryKeySelective(provider)>0) {
            return "redirect:/provider/providerlist.html";
        }
        return "jsp/providermodify";
    }

    // 根据id删除指定供应商信息
    @ResponseBody
    @RequestMapping(value="/delProvider.html",method = RequestMethod.GET)
    public String delProviderById(@RequestParam("proid")String proid) {
        System.err.println("+++++++++++++++++++++++++++++++"+proid);
        Map<String, String> resultMap = new HashMap<String, String>();
        if (StringUtils.isNullOrEmpty(proid)) {
            resultMap.put("delResult", "notexist");
        } else {
            if (providerService.deleteByPrimaryKey(Integer.parseInt(proid))>0) {
                System.err.println("+++++++++++++++++++++++++++++++成功");
                resultMap.put("delResult", "true");
            }else {
                System.err.println("+++++++++++++++++++++++++++++++失败");
                resultMap.put("delResult", "false");
            }
        }
        return JSONArray.toJSONString(resultMap);
    }

    //添加供应商信息
    @RequestMapping(value = "/addprovider.html",method = RequestMethod.POST)
    public String addprovider(Provider provider,HttpSession session) {
        User user=(User)session.getAttribute(Constants.USER_SESSION);
        provider.setCreatedBy(user.getId());
        provider.setCreationDate(new Date());
        if (providerService.insert(provider)>0){
            return "redirect:/provider/providerlist.html";
        }else {
        	return "jsp/provideradd";
		}
    }
    
    //验证供应商编码是否存在
    @ResponseBody
	@RequestMapping(value = "/ucexist.html")
	public String ucexist(@RequestParam String proCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		Provider provider = providerService.selectProviderCodeExist(proCode);
		if (provider != null) {
			map.put("proCode", "exist");
		}
		return JSONArray.toJSONString(map);
	}
    
}
