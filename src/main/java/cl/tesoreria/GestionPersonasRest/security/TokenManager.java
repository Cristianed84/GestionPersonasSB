package cl.tesoreria.GestionPersonasRest.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import cl.tesoreria.GestionPersonasRest.service.Utils;

@SuppressWarnings("deprecation")
public class TokenManager {
	
	private Utils util=new Utils();
	
	public String getJWTToken(String username) {
		Parameters params=new Parameters();
		String secretKey = params.getSecretKey();
		String encodedSecretKey=util.b64Encode(secretKey);	
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 60000))
				.signWith(SignatureAlgorithm.HS512,
						encodedSecretKey.getBytes()).compact();

		return token;
	} 
}
