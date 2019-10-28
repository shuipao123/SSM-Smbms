package com.dzl.smbms.controller;

import com.alibaba.fastjson.JSONArray;
import com.dzl.smbms.pojo.Bill;
import com.dzl.smbms.pojo.Provider;
import com.dzl.smbms.pojo.User;
import com.dzl.smbms.service.BillService;
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
@RequestMapping("/bill")
public class BillController {

    private Logger logger = Logger.getLogger(ProviderController.class);

    @Resource
    private BillService billService;

    @Resource
    private ProviderService providerService;

    //显示全部订单

    @RequestMapping("/billlist.html")
    public String billlist(Model model,
                           @RequestParam(value = "queryProductName", required = false) String queryProductName,
                           @RequestParam(value = "queryProviderId", required = false) String queryProviderId,
                           @RequestParam(value = "queryIsPayment", required = false) String queryIsPayment,
                           @RequestParam(value = "pageIndex", required = false) String pageIndex) {
        System.err.println("queryProductName:" + queryProductName);
        System.err.println("queryProviderId:" + queryProviderId);
        System.err.println("queryIsPayment:" + queryIsPayment);
        System.err.println("pageIndex:" + pageIndex);
        int _queryProviderId = 0;
        int _queryIsPayment = 0;
        List <Bill> billList = null;
        // 设置页面容量
        int pageSize = Constants.pageSize;
        // 当前页码
        int currentPageNo = 1;
        if (queryProductName == null) {
            queryProductName = "";
        }
        if (queryProviderId != null && !queryProviderId.equals("")) {
            _queryProviderId = Integer.parseInt(queryProviderId);
        }
        if (queryIsPayment != null && !queryIsPayment.equals("")) {
            _queryIsPayment = Integer.parseInt(queryIsPayment);
        }
        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                return "redirect:/bill/syserror.html";
            }
        }
        // 总数量（表）
        int totalCount = billService.selectBillCount(queryProductName, _queryProviderId, _queryIsPayment);
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
        billList = billService.selectBillList(queryProductName, _queryProviderId, _queryIsPayment, currentPageNo, pageSize);
        model.addAttribute("billList", billList);
        List <Provider> providerList = null;
        providerList = providerService.selectProviderName();
        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProductName", queryProductName);
        model.addAttribute("queryProviderId", queryProviderId);
        model.addAttribute("queryIsPayment", queryIsPayment);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "jsp/billlist";
    }

    @RequestMapping(value = "/syserror.html")
    public String sysError() {
        return "syserror";
    }

    //根据id查询指定订单信息
    @RequestMapping(value = "/bill.do", params = "method=view")
    public String view(@RequestParam String billid, Model model) {
        Bill bill = billService.selectByPrimaryKey(Integer.valueOf(billid));
        model.addAttribute("bill", bill);
        return "jsp/billview";
    }

    //删除订单
    @ResponseBody
    @RequestMapping(value = "/bill.do", params = "method=delbill")
    public String delBillById(@RequestParam String billid) {
        Map <String, String> resultMap = new HashMap <String, String>();
        if (StringUtils.isNullOrEmpty(billid)) {
            resultMap.put("delResult", "notexist");
        } else {
            if (billService.deleteByPrimaryKey(Integer.parseInt(billid))>0)
                resultMap.put("delResult", "true");
            else
                resultMap.put("delResult", "false");
        }
        return JSONArray.toJSONString(resultMap);
    }

    //修改订单信息
    @RequestMapping(value = "/bill.do", params = "method=modify")
    public String updateBillInfo(@RequestParam String billid, Model model) {
        Bill bill = billService.selectByPrimaryKey(Integer.valueOf(billid));
        model.addAttribute("bill", bill);
        return "jsp/billmodify";
    }

    //获得全部供应商
    @ResponseBody
    @RequestMapping(value = "/bill.do",params = "method=getproviderlist",produces = "text/html; charset=UTF-8")
    public String getproviderlist(){
        List <Provider> providerList = providerService.selectProviderName();
        return  JSONArray.toJSONString(providerList);
    }

    //添加订单
    @RequestMapping("billadd.html")
    public String billadd() {
        return "jsp/billadd";
    }

    /**
     * 修改订单信息
     *
     * @param bill
     * @param session
     * @return
     */
    @RequestMapping(value = "/modifyBillSave.html", method = RequestMethod.POST)
    public String modifyBillSave(Bill bill, HttpSession session) {
        logger.debug("modifyBillSave billid===================== " + bill.getId());
        bill.setModifyBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
        bill.setModifyDate(new Date());
        if (billService.updateByPrimaryKeySelective(bill)>0) {
            return "redirect:/bill/billlist.html";
        }else {
            System.err.println("222");
            return "jsp/billmodify";
        }
    }

    //添加订单信息
    @RequestMapping(value = "/addbill.html",method = RequestMethod.POST)
    public String addBill(Bill bill, HttpSession session) {
        bill.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
        bill.setCreationDate(new Date());
        if (billService.insert(bill)>0) {
            return "redirect:/bill/billlist.html";
        }
        return "jsp/billadd";
    }
}
