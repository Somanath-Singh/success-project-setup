package com.aashdit.setup.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.aashdit.setup.dto.UserVO;
import com.aashdit.setup.umt.model.User;
import com.aashdit.setup.umt.service.UserDetailsServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUtil {

	@Autowired
	private UserDetailsServiceImpl service;

	private String secret = "a@shd1t_k1pddb65f";

	/** extractUsername ➜ extractUsernameSso */
	public String extractUsernameSso(String token) {
		return extractClaimSso(token, Claims::getSubject);
	}

	/** extractExpiration ➜ extractExpirationSso */
	public Date extractExpirationSso(String token) {
		return extractClaimSso(token, Claims::getExpiration);
	}

	/** extractClaim ➜ extractClaimSso */
	public <T> T extractClaimSso(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaimsSso(token);
		return claimsResolver.apply(claims);
	}

	/** extractAllClaims ➜ extractAllClaimsSso */
	private Claims extractAllClaimsSso(String token) {
		try {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			String[] chunks = token.split("\\.");
			Base64.Decoder decoder = Base64.getDecoder();
			String payload = new String(decoder.decode(chunks[1]));
			JSONObject object = new JSONObject(payload);
			@SuppressWarnings("unused")
			String userName = object.getString("sub");
			log.debug("JWT Expired -->", e);
		} catch (UnsupportedJwtException e) {
			log.debug("UnsupportedJwtException -->", e);
		} catch (MalformedJwtException e) {
			log.debug("MalformedJwtException -->", e);
		} catch (SignatureException e) {
			log.debug("SignatureException -->", e);
		} catch (IllegalArgumentException e) {
			log.debug("IllegalArgumentException -->", e);
		}
		return null;
	}

	/** isTokenExpired ➜ isTokenExpiredSso */
	private Boolean isTokenExpiredSso(String token) {
		return extractExpirationSso(token).before(new Date());
	}

	/** generateToken ➜ generateTokenSso */
	public String generateTokenSso(String username, UserVO user) {
		Map<String, Object> claims = new HashMap<>();
		return createTokenSso(claims, username, user);
	}

	/** createToken ➜ createTokenSso */
	private String createTokenSso(Map<String, Object> claims, String username, UserVO user) {
		return Jwts.builder().setClaims(claims).setSubject(username)
				.claim("authorities",
						user.getGrantedAuthorities().stream().map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 57600000)) // 16hr
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	/** validateToken ➜ validateTokenSso */
	public Boolean validateTokenSso(String token) {
		return (!isTokenExpiredSso(token));
	}

	/** checkTokenTime ➜ checkTokenTimeSso */
	public Boolean checkTokenTimeSso(String token) {
		return extractExpirationSso(token).before(new Date());
	}

	// ==============================================================================

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String username) {
		UserDetails userDetails = service.loadUserByUsername(username);
		return Jwts.builder().setClaims(claims).setSubject(username)
				.claim("authorities",
						userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				// 5 minutes
				/* .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) */
				.setExpiration(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public Boolean validateToken(String token, User user) {
		final String username = extractUsername(token);
		return (username.equals(user.getUserName()) && !isTokenExpired(token));
	}

}
