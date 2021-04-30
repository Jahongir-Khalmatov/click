package lesson4.click.security;

//import io.jsonwebtoken.Jwts;
//import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    long expireTime=36000*1000;
String secretKey="JakhongirShukhrat";

public String generateToken(String username){
    return Jwts
            .builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expireTime))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact();
}
public boolean validationToken(String token){
    try {
        Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
    }
    public String getUsernameFromToken(String token) {

        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


}
