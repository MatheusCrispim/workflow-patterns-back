package io.demo.jony.jony.core.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import io.demo.jony.jony.core.config.AppContext;
import io.jsonwebtoken.JwtException;

/**
 * Filter for Authentication with JWT.
 *
 * @author Virtus
 *
 */
public class AuthenticationFilter extends GenericFilterBean {

    /**
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

    	HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
        try {
            /* Checks the Authorization */
            Authentication auth = getTokenService().getAuthentication(request);

            SecurityContextHolder.getContext().setAuthentication(auth);

            chain.doFilter(request, response);
        } catch (JwtException e) {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
    
    /**
     * Gets the token service.
     * 
     * @return Token Service.
     */
    private TokenAuthenticationService getTokenService() {
    	return AppContext.getBean(TokenAuthenticationService.class);
    }

}
