package com.franca.informatica.infrastructure.web.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.franca.informatica.config.util.ErrorCode;
import com.franca.informatica.config.util.ErrorResponse;
import com.franca.informatica.domain.user.AppUser;
import com.franca.informatica.domain.user.UserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			ObjectMapper mapper = new ObjectMapper();
			AppUser appUser = mapper.readValue(request.getInputStream(), AppUser.class);

			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(appUser.getUsername(),
					(appUser.getPassword()));

			return authenticationManager.authenticate(upat);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		final ObjectMapper mapper = new ObjectMapper();
		
		if (e instanceof BadCredentialsException) {
			mapper.writeValue(response.getWriter(), ErrorResponse.of("Usuário e/ou Senha inválido(s)!",
					ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		} else {
			ErrorResponse.of(e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED);
		}
		mapper.writeValue(response.getWriter(),
				ErrorResponse.of("Authentication failed", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

		String jwtToken = Jwts.builder().setSubject(userDetails.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.claim("displayName", userDetails.getDisplayName())
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY).compact();

		response.addHeader(SecurityConstants.AUTHORIZATION_HEADER, SecurityConstants.TOKEN_PREFIX + jwtToken);

		response.getWriter()
				.write(UserDTO.buildToJSon(userDetails.getUsername(), userDetails.getDisplayName(), jwtToken));
	}

}
