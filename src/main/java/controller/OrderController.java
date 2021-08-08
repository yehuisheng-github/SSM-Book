package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.Cart;
import pojo.Order;
import pojo.OrderItem;
import pojo.User;
import service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 	结算功能实现
	 */
	@RequestMapping("/add.request")
	@Scope("prototype")
	public ModelAndView doAdd(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User loginUser = (User) request.getSession().getAttribute("user");
        Integer userId = loginUser.getId();
        String orderId = orderService.createOrder(cart, userId);
        request.getSession().setAttribute("orderId",orderId);
        mv.setViewName("redirect:/client/checkout.jsp");
		return mv;
	}
	
	/*
	 * 	订单管理页面
	 */
	@RequestMapping("/manager/{userId}")
	public ModelAndView orderManager(HttpServletRequest request,@PathVariable("userId")String userId) {
		ModelAndView mv=new ModelAndView();
		List<Order> list = orderService.queryOrder(userId);
		mv.addObject("order", list);
        mv.setViewName("forward:/manager/orderManager.jsp");
		return mv;
	}
	
	/**
	 * 	订单详情
	 */
	@RequestMapping("/detail/{orderId}")
	public ModelAndView orderDetail(HttpServletRequest request,@PathVariable("orderId")String orderId) {
		ModelAndView mv=new ModelAndView();
		List<OrderItem> list = orderService.queryOrderItem(orderId);
		mv.addObject("orderItem", list);
        mv.setViewName("forward:/manager/orderDetail.jsp");
		return mv;
	}
}
