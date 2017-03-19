package filter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;

@WebFilter(filterName="authority"
		, urlPatterns={"/filter/*"}
		, initParams={
		@WebInitParam(name="encoding", value="utf-8"),
		@WebInitParam(name="loginPage", value="/filter/login"),
		@WebInitParam(name="proLogin", value="/filter/proLogin")})
public class AuthorityFilter implements Filter
{
	// FilterConfig可用于访问Filter的配置信息
	private FilterConfig config;
	// 实现初始化方法
	public void init(FilterConfig config)
	{
		this.config = config;
	}
	// 实现销毁方法
	public void destroy()
	{
		this.config = null;
	}
	// 执行过滤的核心方法
	public void doFilter(ServletRequest request,
						 ServletResponse response, FilterChain chain)
			throws IOException,ServletException
	{
		// 获取该Filter的配置参数
		String encoding = config.getInitParameter("encoding");
		String loginPage = config.getInitParameter("loginPage");
		String proLogin = config.getInitParameter("proLogin");
		// 设置request编码用的字符集
		request.setCharacterEncoding(encoding);			// ①
		HttpServletRequest requ = (HttpServletRequest)request;
		HttpSession session = requ.getSession(true);
		// 获取客户请求的页面
		String requestPath = requ.getServletPath();
		// 如果session范围的user为null，即表明没有登录
		// 且用户请求的既不是登录页面，也不是处理登录的页面
		if( session.getAttribute("user") == null
				&& !requestPath.endsWith(loginPage)
				&& !requestPath.endsWith(proLogin))
		{
			// forward到登录页面
			request.setAttribute("tip" , "您还没有登录");
			request.getRequestDispatcher(loginPage)
					.forward(request, response);
		}
		// "放行"请求
		else
		{
			chain.doFilter(request, response);
		}
	}
}
