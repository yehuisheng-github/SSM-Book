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
	 * 	AJAX�������ͼ�鵽���ﳵ��
	 * 	ResponseBody������@ResponseBodyע���תΪJSON��ʽ����������������
	 * 
	 * 	û��ת����bug��������method=RequestMethod.GET
	 */
	@RequestMapping(value="/add.request",method=RequestMethod.GET)
	@ResponseBody
	public void doAdd(HttpServletRequest request,Integer id) {
		//1����ȡ��ӵ����ﳵ��ͼ����Ϣ
        Book book = bookService.queryBookById(id);
        //2������ͼ�����Ϣ����һ�����ﳵ��
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //3����ȡ�򴴽���session����
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        
        //���ø�session�Ĵ��ʱ��Ϊһ��
        HttpSession session=(HttpSession) request.getSession();
        session.setMaxInactiveInterval(60*60*24*7);
//      System.out.println("session����"+session+"�����ʱ�䣺"+session.getMaxInactiveInterval()+"�롣");
        
        if (cart == null) {
        	//session����Ϊ�գ����һ��ʹ�ã���Ҫ����һ�����ﳵ���ŵ�������
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        //4���ѹ��ﳵ����ӵ����ﳵ��
        cart.addItem(cartItem);
        //5�������ӵ���Ʒ���ִ��session������
        request.getSession().setAttribute("lastName",cartItem.getName());
	}
	
	/**
	 * 	AJAX����ɾ�����ﳵ�е���Ʒ��
	 */
	@RequestMapping(value="/delete.request",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String doDelete(HttpServletRequest request) {
		int id = ConvertUtils.paraInt(request.getParameter("id"), -1);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
        	cart.deleteItem(id);
        	//����ɾ����ͼ�������һ�μ��빺�ﳵ��ͼ�飬ˢ��lastName
        	Book book = bookService.queryBookById(id);
        	if(book.getName().equals(request.getSession().getAttribute("lastName"))) {
        		request.getSession().setAttribute("lastName",null);
        	}
        }
        return "ɾ���ɹ���";
	}
	
	/**
	 * 	AJAX������չ��ﳵ
	 */
	@RequestMapping(value="/clear.request",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String doClear(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
        	cart.clear();
        }
        return "����չ��ﳵ��";
	}
	
	/**
	 * 	AJAX�����޸Ĺ��ﳵ�е���Ʒ����
	 */
	@RequestMapping(value="/update.request")
	@ResponseBody
	public void doUpdate(HttpServletRequest request) {
		int id = ConvertUtils.paraInt(request.getParameter("id"), -1);
		
		//���count����С�����Ա�����ֵȡ��
		int count = ParamUtils.reUpdate(request.getParameter("count"));
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
	        cart.updateCount(id,count);
	    }
	}
}
