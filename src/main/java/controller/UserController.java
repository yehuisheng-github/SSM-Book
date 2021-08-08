package controller;
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pojo.User;
import service.UserService;
import utils.ParamUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 	实现用户登录功能
	 */
	@RequestMapping(value="/login.request",method=RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request) {
		
		ModelAndView mv=new ModelAndView();	
		
		//获取客户端请求的参数,并将请求的参数存放在User对象中
		User user=ParamUtils.getValueUtils(request.getParameterMap(), new User());			
		//用户名和密码查询用户信息，假如查询失败，用户信息为0
		List<User> loginUser=userService.login(user);
		user=loginUser.get(0);
		System.out.println("查询数据库："+user);
		
		//检查用户名和密码是否正确
		if(loginUser.size()==0) {					
			//假如，登录信息错误，将错误信息保存到request域中
			mv.addObject("msg", "用户名或密码错误！登录失败！");
			//将回显的表单项信息保存到session中
			request.getSession().setAttribute("username", user.getUsername());
			//跳转到登录页面
			mv.setViewName("forward:/page/login.jsp");
		}else if(loginUser.size()==1){
			request.getSession().setAttribute("user",user);
			mv.setViewName("forward:/index.jsp");
		}
		
		return mv;
	}
	
	/**
	 * 	实现用户注册功能
	 */
	@RequestMapping(value="/regist.request",method=RequestMethod.POST)
	@Transactional
	@Scope("prototype")
	public ModelAndView doRegist(HttpServletRequest request) {
		
		ModelAndView mv=new ModelAndView();
		//获取客户端请求的参数,并将请求的参数存放在User对象中
		User user=ParamUtils.getValueUtils(request.getParameterMap(), new User());
		//获取插件生成的验证码
		String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
		//获取后删除session对象的验证码，保证每一次从session取的验证码都是最新的
		request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
		
		//检查验证码是否正确
		if(token!=null && token.equalsIgnoreCase(user.getCode())) {
					
			//检查用户名是否正确
			if(userService.existsUsername(user.getUsername())) {	
				//自定义错误信息，并保存到域对象当中
				mv.addObject("msg", "用户名已存在");
				//保存表单项的值到域对象
				mv.addObject("username", user.getUsername());
				mv.addObject("email", user.getEmail());
				//转发到注册页面	
				mv.setViewName("forward:/page/regist.jsp");
						
			}else {
				//调用userService将注册信息保存到数据库
				userService.registUser(user);
				//重定向到登录页面
				mv.setViewName("redirect:/page/login.jsp");
			}
					
		}else { 
			//自定义错误信息，并保存到域对象当中
			mv.addObject("msg", "验证码错误");
			//保存表单项的值到域对象
			mv.addObject("username", user.getUsername());
			mv.addObject("email", user.getEmail());
			//转发到注册页面	
			mv.setViewName("forward:/page/regist.jsp");
		}
		
		return mv;
	} 
	
	/**
	 * 	退出登录
	 */
	@RequestMapping("/logout.request")
	public ModelAndView doLogout(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		request.getSession().removeAttribute("user");
		mv.setViewName("redirect:/");
		return mv;
	}
}
