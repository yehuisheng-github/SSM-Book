package utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 拦截器：进入管理页面需要用户登录
 */
public class ManagerFilter implements Filter {

	/**
	 * 	初始化方法
	 */
    public void init(FilterConfig filterConfig) throws ServletException { }

    /**
     * 	过滤方法
     */
    public void doFilter(ServletRequest servletReq, 
    		ServletResponse servletResp, FilterChain filterChain) throws IOException, ServletException {
    	
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletReq;
        Object user = httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            httpServletRequest.getRequestDispatcher("/page/login.jsp").forward(servletReq,servletResp);
        } else {
        	//交给下一个过滤器或者servlet处理
            filterChain.doFilter(servletReq,servletResp);
        }
    }

    /**
     * 	销毁方法
     */
    public void destroy() { }
}
