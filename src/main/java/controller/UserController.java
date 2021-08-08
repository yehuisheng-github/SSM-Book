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
	 * 	ʵ���û���¼����
	 */
	@RequestMapping(value="/login.request",method=RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request) {
		
		ModelAndView mv=new ModelAndView();	
		
		//��ȡ�ͻ�������Ĳ���,��������Ĳ��������User������
		User user=ParamUtils.getValueUtils(request.getParameterMap(), new User());			
		//�û����������ѯ�û���Ϣ�������ѯʧ�ܣ��û���ϢΪ0
		List<User> loginUser=userService.login(user);
		user=loginUser.get(0);
		System.out.println("��ѯ���ݿ⣺"+user);
		
		//����û����������Ƿ���ȷ
		if(loginUser.size()==0) {					
			//���磬��¼��Ϣ���󣬽�������Ϣ���浽request����
			mv.addObject("msg", "�û�����������󣡵�¼ʧ�ܣ�");
			//�����Եı�����Ϣ���浽session��
			request.getSession().setAttribute("username", user.getUsername());
			//��ת����¼ҳ��
			mv.setViewName("forward:/page/login.jsp");
		}else if(loginUser.size()==1){
			request.getSession().setAttribute("user",user);
			mv.setViewName("forward:/index.jsp");
		}
		
		return mv;
	}
	
	/**
	 * 	ʵ���û�ע�Ṧ��
	 */
	@RequestMapping(value="/regist.request",method=RequestMethod.POST)
	@Transactional
	@Scope("prototype")
	public ModelAndView doRegist(HttpServletRequest request) {
		
		ModelAndView mv=new ModelAndView();
		//��ȡ�ͻ�������Ĳ���,��������Ĳ��������User������
		User user=ParamUtils.getValueUtils(request.getParameterMap(), new User());
		//��ȡ������ɵ���֤��
		String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
		//��ȡ��ɾ��session�������֤�룬��֤ÿһ�δ�sessionȡ����֤�붼�����µ�
		request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
		
		//�����֤���Ƿ���ȷ
		if(token!=null && token.equalsIgnoreCase(user.getCode())) {
					
			//����û����Ƿ���ȷ
			if(userService.existsUsername(user.getUsername())) {	
				//�Զ��������Ϣ�������浽�������
				mv.addObject("msg", "�û����Ѵ���");
				//��������ֵ�������
				mv.addObject("username", user.getUsername());
				mv.addObject("email", user.getEmail());
				//ת����ע��ҳ��	
				mv.setViewName("forward:/page/regist.jsp");
						
			}else {
				//����userService��ע����Ϣ���浽���ݿ�
				userService.registUser(user);
				//�ض��򵽵�¼ҳ��
				mv.setViewName("redirect:/page/login.jsp");
			}
					
		}else { 
			//�Զ��������Ϣ�������浽�������
			mv.addObject("msg", "��֤�����");
			//��������ֵ�������
			mv.addObject("username", user.getUsername());
			mv.addObject("email", user.getEmail());
			//ת����ע��ҳ��	
			mv.setViewName("forward:/page/regist.jsp");
		}
		
		return mv;
	} 
	
	/**
	 * 	�˳���¼
	 */
	@RequestMapping("/logout.request")
	public ModelAndView doLogout(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		request.getSession().removeAttribute("user");
		mv.setViewName("redirect:/");
		return mv;
	}
}
