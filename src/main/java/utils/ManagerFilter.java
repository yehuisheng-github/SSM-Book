package utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ���������������ҳ����Ҫ�û���¼
 */
public class ManagerFilter implements Filter {

	/**
	 * 	��ʼ������
	 */
    public void init(FilterConfig filterConfig) throws ServletException { }

    /**
     * 	���˷���
     */
    public void doFilter(ServletRequest servletReq, 
    		ServletResponse servletResp, FilterChain filterChain) throws IOException, ServletException {
    	
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletReq;
        Object user = httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            httpServletRequest.getRequestDispatcher("/page/login.jsp").forward(servletReq,servletResp);
        } else {
        	//������һ������������servlet����
            filterChain.doFilter(servletReq,servletResp);
        }
    }

    /**
     * 	���ٷ���
     */
    public void destroy() { }
}
