package ua.web_challenge.volunteer.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

public final class TokenHandler {

	private static final String HMAC_ALGO = "HmacSHA256";
	private static final String SEPARATOR = ".";
	private static final String SEPARATOR_SPLITTER = "\\.";

    private static class UserToken {
        private final UserDetails userDetails;
        private final long expires;

        public UserToken(UserDetails userDetails, long expires) {
            this.userDetails = userDetails;
            this.expires = expires;
        }

        public UserDetails getUserDetails() {
            return userDetails;
        }

        public long getExpires() {
            return expires;
        }
    }

	private final Mac hmac;

	public TokenHandler(byte[] secretKey) {
		try {
			hmac = Mac.getInstance(HMAC_ALGO);
			hmac.init(new SecretKeySpec(secretKey, HMAC_ALGO));
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new IllegalStateException("failed to initialize HMAC: " + e.getMessage(), e);
		}
	}

	public UserDetails parseUserFromToken(String token) {
		final String[] parts = token.split(SEPARATOR_SPLITTER);
		if (parts.length == 2 && parts[0].length() > 0 && parts[1].length() > 0) {
			try {
				final byte[] userBytes = fromBase64(parts[0]);
				final byte[] hash = fromBase64(parts[1]);

				boolean validHash = Arrays.equals(createHmac(userBytes), hash);
				if (validHash) {
					final UserToken userToken = fromJSON(userBytes);
					if (new Date().getTime() < userToken.getExpires()) {
						return userToken.getUserDetails();
					}
				}
			} catch (IllegalArgumentException e) {
				//log tempering attempt here
			}
		}
		return null;
	}

	public String createTokenForUser(UserDetails user, long expiresPeriod) {
        long expires = System.currentTimeMillis() + expiresPeriod;
        UserToken userToken = new UserToken(user, expires);

        byte[] userBytes = toJSON(userToken);
		byte[] hash = createHmac(userBytes);

		final StringBuilder sb = new StringBuilder(170);
		sb.append(toBase64(userBytes));
		sb.append(SEPARATOR);
		sb.append(toBase64(hash));

		return sb.toString();
	}

	private UserToken fromJSON(final byte[] userBytes) {
		try {
			return new ObjectMapper().readValue(new ByteArrayInputStream(userBytes), UserToken.class);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private byte[] toJSON(UserToken token) {
		try {
			return new ObjectMapper().writeValueAsBytes(token);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

	private String toBase64(byte[] content) {
		return DatatypeConverter.printBase64Binary(content);
	}

	private byte[] fromBase64(String content) {
		return DatatypeConverter.parseBase64Binary(content);
	}

	// synchronized to guard internal hmac object
	private synchronized byte[] createHmac(byte[] content) {
		return hmac.doFinal(content);
	}
}
