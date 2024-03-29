package ua.web_challenge.volunteer.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ua.web_challenge.volunteer.service.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component("authenticationFilter")
class TokenAuthenticationFilter extends GenericFilterBean {
	private final TokenAuthenticationService tokenAuthenticationService;

    @Autowired
	protected TokenAuthenticationFilter(TokenAuthenticationService service) {
		this.tokenAuthenticationService = service;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
		SecurityContextHolder.getContext().setAuthentication(
				tokenAuthenticationService.getAuthentication((HttpServletRequest) req));
		chain.doFilter(req, res); // always continue
	}
}