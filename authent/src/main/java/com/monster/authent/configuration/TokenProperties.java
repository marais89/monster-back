package com.monster.authent.configuration;

import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.util.io.pem.PemObject;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Validated
@ConfigurationProperties(prefix = "auth.jwt")
public class TokenProperties {

    @NotNull
    private Duration tokenAlive;

    @NotNull
    private Resource privateKeyLocation;

    @NotNull
    @Size(min = 1)
    private String issuer = "auth-gateway";

    @NotNull
    private String signingKeyType;

    public TokenProperties() {
    }


    public PrivateKey buildPrivateKey() throws GeneralSecurityException {
        String keyAlgorithm = signingKeyType;
        byte[] privateKeyBytes = readPrivateKeyBytes();
        return KeyFactory.getInstance(keyAlgorithm).generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
    }

    private byte[] readPrivateKeyBytes() {

        PemObject pemObject;
        try (
                Reader reader = new FileReader(privateKeyLocation.getFile());
                PEMParser pemParser = new PEMParser(reader)
        ) {
            pemObject = pemParser.readPemObject();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Unable to parse private key", ex);
        }
        String objectType = pemObject.getType();
        if (!"PRIVATE KEY".equals(pemObject.getType())) {
            throw new InvalidPropertyException(getClass(), "privateKeyLocation", String.format("%s is not a PRIVATE KEY (type is %s)", privateKeyLocation, objectType));
        }
        return pemObject.getContent();
    }

    public Map<String, Object> buildDefaultClaims(String username) {
        Map<String, Object> claims = new HashMap<>();

        Date issuedAt = new Date(System.currentTimeMillis());

        claims.put("iss", getIssuer());
        claims.put("jti", jtiGenerator());
        claims.put("nbf", issuedAt);
        claims.put("sub", username);
        claims.put("iat", issuedAt);
        claims.put("exp", new Date(issuedAt.getTime() + getTokenAlive().toMillis()));
        return claims;
    }

    private String jtiGenerator() {
        return UUID.randomUUID().toString() + "-" + UUID.randomUUID().toString();
    }

    public Duration getTokenAlive() {
        return tokenAlive;
    }

    public void setTokenAlive(Duration tokenAlive) {
        this.tokenAlive = tokenAlive;
    }

    public Resource getPrivateKeyLocation() {
        return privateKeyLocation;
    }

    public void setPrivateKeyLocation(Resource privateKeyLocation) {
        this.privateKeyLocation = privateKeyLocation;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSigningKeyType() {
        return signingKeyType;
    }

    public void setSigningKeyType(String signingKeyType) {
        this.signingKeyType = signingKeyType;
    }
}
