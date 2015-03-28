package ua.web_challenge.volunteer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ua.web_challenge.volunteer.security.TokenHandler;
import ua.web_challenge.volunteer.security.VolunteerUserAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

@Service("tokenAuthenticationService")
public class TokenAuthenticationService {
    @Value("${token.header_name}")
	private static String authHeaderName;

    @Value("${token.expired_period}")
	private static long expiredPeriod;

	private final TokenHandler tokenHandler;

	@Autowired
	public TokenAuthenticationService(@Value("${token.secret_key}") String secretKey) {
		tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secretKey));
	}

	public void addAuthentication(HttpServletResponse response, VolunteerUserAuthentication authentication) {
        UserDetails userDetails = authentication.getDetails();

		response.addHeader(authHeaderName, tokenHandler.createTokenForUser(userDetails, expiredPeriod));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(authHeaderName);
		if (token != null) {
			final UserDetails userDetails = tokenHandler.parseUserFromToken(token);
			if (userDetails != null) {
				return new VolunteerUserAuthentication(userDetails);
			}
		}
		return null;
	}
}
