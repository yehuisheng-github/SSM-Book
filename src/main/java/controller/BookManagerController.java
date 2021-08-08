package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pojo.Book;
import service.BookService;
import utils.ConvertUtils;
import utils.ParamUtils;

@Controller
@RequestMapping("/book/manager")
public class BookManagerController {

	@Autowired
	private BookService bookService;
	
	/**
	 * 	添加图书	
	 */
	@RequestMapping(value="/add")
	@Scope("prototype")
	public ModelAndView doAdd(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		//1、获取客户端请求的参数，封装成book对象
		Book book=ParamUtils.getValueUtils(request.getParameterMap(), new Book());
		//2、调用bookservice.addBook()方法把刚刚封装的对象的属性值保存到数据库
		bookService.addBook(book);
		/*
		 * 	3、图书添加完毕后，跳转到图书显示的页面
		 * 	request.getRequestDispatcher("/BookServlet?action=list").forward(request, response);
		 * 	请求重定向两次请求，请求转发一次请求
		 */
		mv.setViewName("redirect:/book/manager.request?pageNo="+request.getParameter("pageNo")+1);
		return mv;
	}
	
	/**
	 * 	删除图书
	 */
	@RequestMapping(value="/delete")
	@Scope("prototype")
	public ModelAndView doDelete(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		int id=Integer.parseInt(request.getParameter("id"));
		bookService.deleteBookById(id);
		mv.setViewName("redirect:/book/manager.request?pageNo="+request.getParameter("pageNo"));
		return mv;
	}
	
	/**
	 * 	获取需要修改的图书数据
	 */
	@RequestMapping(value="/updateQuery")
	public ModelAndView doUpdateQuery(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		//1、获取图书编号
		int id=Integer.parseInt(request.getParameter("id"));
		//2、调用bookservice.queryBookById()方法查询数据库的数据
		Book bookData=bookService.queryBookById(id);
		//3、将查询的数据保存到域对象当中
		mv.addObject("bookData",bookData);
		//4、请求转发到修改页面
		mv.setViewName("forward:/manager/edit.jsp");
		return mv;
	}
	
	/**
	 * 	修改图书
	 */
	@RequestMapping(value="/update")
	@Scope("prototype")
	public ModelAndView doUpdate(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		//1、获取请求的参数封装成JavaBean
		Book book=ParamUtils.getValueUtils(request.getParameterMap(), new Book());
		
		if(book.getSales()==0) {
			//如果Sales出现小数，对变量的值取整
			book.setSales(ParamUtils.reUpdate(request.getParameter("sales")));
		}
		if(book.getStock()==0) {
			//如果Stock出现小数，对变量的值取整
			book.setStock(ParamUtils.reUpdate(request.getParameter("stock")));
		}
		
		//2、调用bookservice.updateBook()方法修改数据库的数据
		bookService.updateBook(book);
		//3、修改完毕，跳转到图书显示的页面
		mv.setViewName("redirect:/book/manager.request?pageNo="+request.getParameter("pageNo"));
		return mv;
	}

}
