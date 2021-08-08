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
	 * 	���ͼ��	
	 */
	@RequestMapping(value="/add")
	@Scope("prototype")
	public ModelAndView doAdd(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		//1����ȡ�ͻ�������Ĳ�������װ��book����
		Book book=ParamUtils.getValueUtils(request.getParameterMap(), new Book());
		//2������bookservice.addBook()�����Ѹոշ�װ�Ķ��������ֵ���浽���ݿ�
		bookService.addBook(book);
		/*
		 * 	3��ͼ�������Ϻ���ת��ͼ����ʾ��ҳ��
		 * 	request.getRequestDispatcher("/BookServlet?action=list").forward(request, response);
		 * 	�����ض���������������ת��һ������
		 */
		mv.setViewName("redirect:/book/manager.request?pageNo="+request.getParameter("pageNo")+1);
		return mv;
	}
	
	/**
	 * 	ɾ��ͼ��
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
	 * 	��ȡ��Ҫ�޸ĵ�ͼ������
	 */
	@RequestMapping(value="/updateQuery")
	public ModelAndView doUpdateQuery(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		//1����ȡͼ����
		int id=Integer.parseInt(request.getParameter("id"));
		//2������bookservice.queryBookById()������ѯ���ݿ������
		Book bookData=bookService.queryBookById(id);
		//3������ѯ�����ݱ��浽�������
		mv.addObject("bookData",bookData);
		//4������ת�����޸�ҳ��
		mv.setViewName("forward:/manager/edit.jsp");
		return mv;
	}
	
	/**
	 * 	�޸�ͼ��
	 */
	@RequestMapping(value="/update")
	@Scope("prototype")
	public ModelAndView doUpdate(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		//1����ȡ����Ĳ�����װ��JavaBean
		Book book=ParamUtils.getValueUtils(request.getParameterMap(), new Book());
		
		if(book.getSales()==0) {
			//���Sales����С�����Ա�����ֵȡ��
			book.setSales(ParamUtils.reUpdate(request.getParameter("sales")));
		}
		if(book.getStock()==0) {
			//���Stock����С�����Ա�����ֵȡ��
			book.setStock(ParamUtils.reUpdate(request.getParameter("stock")));
		}
		
		//2������bookservice.updateBook()�����޸����ݿ������
		bookService.updateBook(book);
		//3���޸���ϣ���ת��ͼ����ʾ��ҳ��
		mv.setViewName("redirect:/book/manager.request?pageNo="+request.getParameter("pageNo"));
		return mv;
	}

}
