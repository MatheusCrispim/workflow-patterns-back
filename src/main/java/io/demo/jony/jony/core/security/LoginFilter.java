package io.demo.jony.jony.core.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.demo.jony.jony.core.config.AppContext;
import io.demo.jony.jony.core.dto.LoggedDTO;
import io.demo.jony.jony.core.util.JSonUtil;

/**
 * Filter for Login.
 *
 * @author Virtus
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * Constructor.
     *
     * @param url URL.
     */
    public LoginFilter(String url) {
        super(new AntPathRequestMatcher(url));

        this.setAuthenticationManager(new JWTAuthenticationManager());
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        SecurityUtils.fillAccessControlHeader(response);

        AccountCredentials creds = new ObjectMapper().readValue(request.getInputStream(), AccountCredentials.class);

        return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getLogin(),
                creds.getPassword(), Collections.emptyList()));
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain,
     * org.springframework.security.core.Authentication)
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException {

        LoggedDTO dto = (LoggedDTO) auth.getPrincipal();

        dto = getTokenService().addAuthentication(dto);

        response.getWriter().write(JSonUtil.toJSon(dto));
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
