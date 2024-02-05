package org.changsol.api.configs.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class ChangSolHttpServletRequestFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {

		ChangSolHttpServletRequest changSolHttpServletRequest = new ChangSolHttpServletRequest((HttpServletRequest) request);

		chain.doFilter(changSolHttpServletRequest, response);
	}
}