package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import pojo.Book;
import service.BookService;
import utils.ConvertUtils;

@Controller
@RequestMapping("/book")
public class BookClientController {

	@Autowired
	private BookService bookService;
	
	/**
	 * 	��ʾ����ͼ�飬���ҷ�ҳ����
	 * 	show.request��ͼ����ʾ����
	 * 	manager.request��ͼ���������
	 */
	@RequestMapping(value={"/show.request","/manager.request"})
	public ModelAndView doShow(HttpServletRequest request) {

		//����Boolean���ͱ�����ͼ����ʾ����flagΪtrue����������Ϊfalse
		StringBuffer requestURL=request.getRequestURL();
		boolean flag=true;
		if(requestURL.toString().contains("manager")) {
			flag=false;
		}
		//���õ�ǰҳ��
		int pageNo=ConvertUtils.paraInt(request.getParameter("pageNo"), 1);
		ModelAndView mv=new ModelAndView();
		
		/**
		 * 	���÷�ҳ������ܣ�
		 * 		PageHelper.startPage(pageNum, pageSize);
		 * 		pageNum����ǰҳ��
		 * 		pageSize��ÿҳ�ж���������
		 */
		if(flag) {
			PageHelper.startPage(pageNo, 4);
		}else {
			PageHelper.startPage(pageNo, 8);
		}
		
		//1����ѯ����ͼ�飬��ʾ����ҳ
		List <Book> books=bookService.queryBooks();
		//2����װ��ҳ��Ľ����
		PageInfo pageInfo=new PageInfo(books);
		
//		System.out.println("��ǰҳ�룺"+pageInfo.getPageNum());
//		System.out.println("��ҳ�룺"+pageInfo.getPages());
//		System.out.println("�ܼ�¼����"+pageInfo.getTotal());
//		System.out.println("��ǰҳ��������"+pageInfo.getSize());
//		System.out.println("ÿҳ�ж��������ݣ�"+pageInfo.getPageSize());
//		System.out.println("��һҳ��"+pageInfo.getPrePage());
//		System.out.println("���ݣ�"+pageInfo.getList());
		
		//3������list����request����
		mv.addObject("page", pageInfo);
		
		if(flag) {
			/**
			 * 	����URL����ҳ����ʱ����ͬһ������ָ��������
			 */
			mv.addObject("pageUrl","book/show.request?x=x");
			//4������ת����ͼ����ʾҳ��
			mv.setViewName("forward:/client/show.jsp");
		}else{
			mv.addObject("pageUrl","book/manager.request?x=x");
			//4������ת����ͼ�����ҳ��
			mv.setViewName("forward:/manager/bookManager.jsp");
		}
		
		return mv;
	}
	
	/**
	 * 	���ݼ۸���в�ѯ
	 */
	@RequestMapping("/byPrice.request")
	public ModelAndView doQueryByPrice(HttpServletRequest request) {
		
		ModelAndView mv=new ModelAndView();
		int pageNo=ConvertUtils.paraInt(request.getParameter("pageNo"), 1);
		int min=ConvertUtils.paraInt(request.getParameter("min"), 0);
		int max=ConvertUtils.paraInt(request.getParameter("max"),Integer.MAX_VALUE);
		
		PageHelper.startPage(pageNo, 4);
		List <Book> books=bookService.queryByPrice(min, max);
		PageInfo pageInfo=new PageInfo(books);
		mv.addObject("page", pageInfo);
		
		mv.addObject("min", min);
		mv.addObject("max", max);
		mv.addObject("pageUrl","book/byPrice.request?min="+min+"&max="+max);
		mv.setViewName("forward:/client/show.jsp");
		return mv;
	}
	
}
