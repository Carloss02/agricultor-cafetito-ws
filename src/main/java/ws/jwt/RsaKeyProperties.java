/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.jwt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Clase que se utiliza para leer y almacenar claves RSA(publicas o privadas)
 * desde un archivo de propiedades. 
 * 
 * @ConfigurationProperties se utiliza para especificar el prefijo "rsa" en 
 * el archivo de propiedades. 
 */
@ConfigurationProperties(prefix="rsa")
public class RsaKeyProperties {

    
    private RSAPublicKey publicKey;
    
    private RSAPrivateKey privateKey;

    /**
     * Constructor que inicializa las claves RSA con los valores especificados
     * en application.properties.
     *
     * @param publicKey Clave pública RSA.
     * @param privateKey Clave privada RSA.
     */
    public RsaKeyProperties(
            @Value("${rsa.public-key}") RSAPublicKey publicKey,
            @Value("${rsa.private-key}") RSAPrivateKey privateKey
    ) {
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