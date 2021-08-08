package controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.BookService;
import utils.ConvertUtils;
import utils.ParamUtils;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private BookService bookService;
	
	/**
	 * 	AJAX请求：添加图书到购物车中
	 * 	ResponseBody：调用@ResponseBody注解把转为JSON格式的数据输出给浏览器
	 * 
	 * 	没有转换器bug解决：添加method=RequestMethod.GET
	 */
	@RequestMapping(value="/add.request",method=RequestMethod.GET)
	@ResponseBody
	public void doAdd(HttpServletRequest request,Integer id) {
		//1、获取添加到购物车的图书信息
        Book book = bookService.queryBookById(id);
        //2、根据图书的信息生成一个购物车项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //3、获取或创建该session对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        
        //设置该session的存活时间为一周
        HttpSession session=(HttpSession) request.getSession();
        session.setMaxInactiveInterval(60*60*24*7);
//      System.out.println("session对象："+session+"，存活时间："+session.getMaxInactiveInterval()+"秒。");
        
        if (cart == null) {
        	//session对象为空，则第一次使用，需要创建一个购物车，放到对象中
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        //4、把购物车项添加到购物车中
        cart.addItem(cartItem);
        //5、最后添加的商品名字存放session对象中
        request.getSession().setAttribute("lastName",cartItem.getName());
	}
	
	/**
	 * 	AJAX请求：删除购物车中的商品项
	 */
	@RequestMapping(value="/delete.request",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(HttpServletRequest request) {
		int id = ConvertUtils.paraInt(request.getParameter("id"), -1);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
        	cart.deleteItem(id);
        	//假如删除的图书是最后一次加入购物车的图书，刷新lastName
        	Book book = bookService.queryBookById(id);
        	if(book.getName().equals(request.getSession().getAttribute("lastName"))) {
        		request.getSession().setAttribute("lastName",null);
        	}
        }
        return "删除成功！";
	}
	
	/**
	 * 	AJAX请求：清空购物车
	 */
	@RequestMapping(value="/clear.request",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String doClear(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
        	cart.clear();
        }
        return "已清空购物车！";
	}
	
	/**
	 * 	AJAX请求：修改购物车中的商品数量
	 */
	@RequestMapping(value="/update.request")
	@ResponseBody
	public void doUpdate(HttpServletRequest request) {
		int id = ConvertUtils.paraInt(request.getParameter("id"), -1);
		
		//如果count出现小数，对变量的值取整
		int count = ParamUtils.reUpdate(request.getParameter("count"));
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
	        cart.updateCount(id,count);
	    }
	}
}
