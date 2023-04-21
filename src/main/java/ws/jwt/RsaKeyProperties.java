/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.jwt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="rsa")
public class RsaKeyProperties {

    
    private RSAPublicKey publicKey;
    
    private RSAPrivateKey privateKey;

    public RsaKeyProperties(
            @Value("${rsa.public-key}")
            RSAPublicKey publicKey, 
            @Value("${rsa.private-key}")
            RSAPrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}