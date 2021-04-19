package com.monster.authent.facade;

import com.monster.authent.configuration.TokenProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.FixedClock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;

@Component
public class JwtUtils {

    protected final Log logger = LogFactory.getLog(getClass());

    private final TokenProperties tokenProperties;

    public JwtUtils(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    public String generateJwtToken(String username) throws GeneralSecurityException {

        return Jwts.builder()
                .setClaims(tokenProperties.buildDefaultClaims(username))
                .signWith(tokenProperties.buildPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) throws GeneralSecurityException {
        return Jwts.parser().setSigningKey(tokenProperties.buildPrivateKey()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {

        Clock clock = new FixedClock();

        try {
            Jwts.parser().setSigningKey(tokenProperties.buildPrivateKey()).setClock(clock).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.info("Invalid JWT signature: {}" + e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}" + e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}" + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}" + e.getMessage());
        } catch (GeneralSecurityException e) {
            logger.error("General security exception: {}" + e.getMessage());
        }

        return false;
    }
}
