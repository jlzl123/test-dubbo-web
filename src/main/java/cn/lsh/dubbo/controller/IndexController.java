package cn.lsh.dubbo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lsh.dubbo.bean.UserBean;
import cn.lsh.dubbo.registry.service.TestRegistryService;
import cn.lsh.dubbo.registry.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private TestRegistryService testRegistryService;
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/index", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String index(HttpServletRequest request,HttpServletResponse response){
	
		String name="";
		name=request.getParameter("name");
		if(name!=null){
			return testRegistryService.hello(name);
		}
		return null;
	} 
	
	@RequestMapping(value="/list")// 由于设置了@ResponseBody,要把对象转换成json格式，缺少转换依赖的jar包
	public @ResponseBody List<UserBean> getUsers(HttpServletRequest request,
			HttpServletResponse response){
        List<UserBean> list=null;
		list=userService.list();
		return list;
	}
}
