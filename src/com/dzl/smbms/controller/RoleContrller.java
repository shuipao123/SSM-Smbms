package com.dzl.smbms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dzl.smbms.pojo.Role;
import com.dzl.smbms.pojo.User;
import com.dzl.smbms.service.RoleService;
import com.dzl.smbms.service.UserService;
import com.dzl.smbms.tools.Constants;
import com.mysql.jdbc.StringUtils;

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
@RequestMapping("/role")
public class RoleContrller {

	@Resource
	private RoleService roleService;
	
	@Resource
	private UserService userService;

	@RequestMapping("/rolelist.html")
	public String rolelist(Model mod) {
		List<Role> roleList = roleService.selectRoleList();
		mod.addAttribute("roleList", roleList);
		return "jsp/rolelist";
	}

	// 根据id删除指定角色信息
	@ResponseBody
	@RequestMapping(value = "/role.do", params = "method=delrole", method = RequestMethod.GET)
	public String delProviderById(@RequestParam("roleid") String roleid) {
		System.err.println("+++++++++++++++++++++++++++++++" + roleid);
		Map<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(roleid)) {
			resultMap.put("delResult", "notexist");
		} else {
			// 判断角色下是否还有其它表数据
			List<User> user= userService.getRoleIdByUserInfo(Integer.parseInt(roleid));
			if (user.size()==0) {
				if (roleService.deleteByPrimaryKey(Integer.parseInt(roleid)) > 0) {
					System.err.println("+++++++++++++++++++++++++++++++成功");
					resultMap.put("delResult", "true");
				} else {
					System.err.println("+++++++++++++++++++++++++++++++失败");
					resultMap.put("delResult", "false");
				}
			}else {
				resultMap.put("delResult", "false1");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}

	// 根据id查询指定角色信息
	@ResponseBody
	@RequestMapping(value = "/role.do", params = "method=view", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String viewProviderById(@RequestParam("roleid") String roleid) {
		System.err.println("+++++++++++++++++++++++++++++++" + roleid);
		Role role = roleService.selectByPrimaryKey(Integer.parseInt(roleid));
		return JSON.toJSONString(role);
	}

	// 根据id查询指定角色信息  显示进行修改
	@RequestMapping(value = "/role.do", params = "method=modify")
	public String modifyProviderById(@RequestParam("roleid") String roleid,Model model) {
		System.err.println("+++++++++++++++++++++++++++++++" + roleid);
		Role role = roleService.selectByPrimaryKey(Integer.parseInt(roleid));
		model.addAttribute("role", role);
		return "jsp/rolemodify";
	}
	
	//修改角色信息
	@RequestMapping(value="/roleupdate.html",method=RequestMethod.POST)
	public String roleupdate(Role role,HttpSession session) {
		User user=(User)session.getAttribute(Constants.USER_SESSION);
		System.err.println("--------------------"+user);
		System.err.println("--------------------"+user.getId());
		role.setModifyBy(user.getId());
		role.setModifyDate(new Date());
		if (roleService.updateByPrimaryKeySelective(role)>0) {
			 return "redirect:/role/rolelist.html";
		}
		return "jsp/rolemodify";
	}
	
	//跳转到添加角色页面
	@RequestMapping(value = "/roleadd.html")
	public String roleadd() {
		return "jsp/roleadd";
	}

	@RequestMapping(value = "/addrole.html",method=RequestMethod.POST)
	public String addrole(Role role,HttpSession session) {
		 	User user=(User)session.getAttribute(Constants.USER_SESSION);
		 	role.setCreatedBy(user.getId());
		 	role.setCreationDate(new Date());
	        if (roleService.insert(role)>0){
	            return "redirect:/role/rolelist.html";
	        }else {
	        	return "jsp/roleadd";
			}
	}
}
