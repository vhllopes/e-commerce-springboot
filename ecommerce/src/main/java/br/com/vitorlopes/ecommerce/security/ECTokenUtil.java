package br.com.vitorlopes.ecommerce.security;

import br.com.vitorlopes.ecommerce.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ECTokenUtil {

    public static final long UM_SEGUNDO = 1000;
    public static final long UM_MINUTO = 60 * UM_SEGUNDO;
    public static final long UMA_HORA = 60 * UM_MINUTO;
    public static final long UM_DIA = 24 * UM_MINUTO;

    public static final String EMISSOR = "TrueDEV";
    public static final String TOKEN_KEY = "12345678912345678912345678912345";
    public static final String TOKEN_HEADER = "Bearer ";

    public static ECToken generateToken(Usuario usuario){
        Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
        String jwt = Jwts.builder().setSubject(usuario.getLogin())
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + UM_DIA))
                .signWith(secretKey, SignatureAlgorithm.HS256).compact();

        ECToken token =  new ECToken();
        token.setToken(TOKEN_HEADER + jwt);
        return token;
    }

    public static Authentication decodeToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = token.replace(TOKEN_HEADER, "");

        Jws<Claims> jws = Jwts.parser().setSigningKey(TOKEN_KEY.getBytes()).build().parseClaimsJws(token);
        String login = jws.getBody().getSubject();
        String emissor = jws.getBody().getIssuer();
        Date validade = jws.getBody().getExpiration();
        if(login.length()>0 && emissor.equals(EMISSOR) &&  validade.after(new Date(System.currentTimeMillis()))) {
            /*Gerando um objeto do tipo Autenticacao passando login: quem é, null: credenciais,
            Collections.emptyList(): endpoints que ele pode acessar*/
            return new UsernamePasswordAuthenticationToken(login, null, Collections.emptyList());
        }
        return null;
    }
}
