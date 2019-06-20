package io.demo.jony.jony.core.security;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.demo.jony.jony.core.dto.LoggedDTO;
import io.demo.jony.jony.core.dto.TokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Service for Token Authentication.
 *
 * @author Virtus
 *
 */
@Service
public class TokenAuthenticationService {

	private static final String TOKEN_PREFIX = "Bearer";

	public static final String HEADER = "Authorization";

	protected static final String HEADER_REFRESH = "Refresh-Token";

	protected static final String CLAIM_ROLE = "role";

	@Value("${app.token.secretkey}")
	private String SECRET_KEY;

	/**
	 * Token Expiration.
	 */
	@Value("${app.token.expiration}")
	private long EXPIRATION_TOKEN;

	/**
	 * Refresh Token Expiration.
	 */
	@Value("${app.refreshtoken.expiration}")
	private long EXPIRATION_REFRESH_TOKEN;

	/**
	 * Adds the authentication in the response.
	 *
	 * @param dto Login DTO.
	 */
	public LoggedDTO addAuthentication(LoggedDTO dto) {

		String jwt = Jwts.builder().setId(String.valueOf(dto.getId())).setSubject(dto.getLogin())
				.claim(CLAIM_ROLE, dto.getIdRole())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

		String refresh = Jwts.builder().setId(String.valueOf(dto.getId())).setSubject(dto.getLogin())
				.claim(CLAIM_ROLE, dto.getIdRole())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_REFRESH_TOKEN))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
		dto.setToken(jwt);
		dto.setRefreshToken(refresh);

		return dto;
	}

	/**
	 * Gets the authentication.
	 *
	 * @param request Request.
	 * @return Authentication.
	 */
	public Authentication getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(HEADER);

		if (token != null) {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim()).getBody();

			return claims != null
					? new UsernamePasswordAuthenticationToken(new LoggedDTO(Integer.valueOf(claims.getId()),
							claims.getSubject(), Integer.valueOf(claims.get(CLAIM_ROLE).toString())), null, emptyList())
					: null;
		}

		return null;
	}

	/**
	 * Generate Token.
	 *
	 * @param login Login.
	 * @return Token.
	 */
	public String generateToken(String id, String login) {

		return Jwts.builder().setId(id).setSubject(login)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	/**
	 * Gets the refresh authentication.
	 *
	 * @param request Request.
	 * @return Refresh Authentication.
	 */
	public Authentication getRefreshAuthetication(HttpServletRequest request) {

		String refreshToken;
		try {
			refreshToken = new ObjectMapper().readValue(request.getInputStream(), TokenDTO.class).getToken();
		} catch (IOException e) {
			return null;
		}

		if (refreshToken != null) {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
					.parseClaimsJws(refreshToken.replace(TOKEN_PREFIX, "").trim()).getBody();

			if (claims != null) {
				Integer idRole = Integer.valueOf(claims.get(CLAIM_ROLE).toString());
				LoggedDTO userDTO = new LoggedDTO(Integer.valueOf(claims.getId()), claims.getSubject(), idRole);
				return new UsernamePasswordAuthenticationToken(userDTO, null, emptyList());
			}

		}

		return null;
	}
}
