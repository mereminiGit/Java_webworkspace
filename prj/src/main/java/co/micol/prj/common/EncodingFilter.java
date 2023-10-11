package co.micol.prj.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	private String encode;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encode = filterConfig.getInitParameter("encode");	// web.xml에 설정하겠다.
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 동작 할 때 어케할꺼냐
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encode); // utf-8이 담김
		}
		// 필터 체인 동작
		chain.doFilter(request, response);
 	}

	@Override
	public void destroy() {
		// 필터가 끝날때 프로젝트 종료시 어케할꺼냐
	}

}
