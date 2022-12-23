package br.com.financas.config.security;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import br.com.financas.model.RoleModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider implements Serializable{
	private static final long serialVersionUID = 2569800841756370596L;
	
	private String secretKey = "secret"; 
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	private long validityInMilliseconds = 10 * 60 * 60;
	
	public String createToken(String username, RoleModel role) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", role);
		
		Date now = new Date();
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + validityInMilliseconds))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public Authentication getAuthentication(String token) {
		String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
	}
	
	public boolean validateToken(String token) {
		Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		return true;
	}
}











