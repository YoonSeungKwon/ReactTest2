package yoon.test.reactTest2.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import yoon.test.reactTest2.domain.Members;
import yoon.test.reactTest2.repository.MemberRepository;
import yoon.test.reactTest2.service.MemberDetailService;
import yoon.test.reactTest2.vo.request.MemberDto;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final MemberDetailService memberDetailService;
    private final String SECRET_KEY = "yoonsk1234";
    private final long exp = 1000l * 60 * 30;

    public String createToken(String email){
        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp));
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .claim("email", email)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Authentication getAuthentication(String token){
        UserDetails member = memberDetailService.loadUserByUsername(this.getEmail(token));
        return new UsernamePasswordAuthenticationToken(member,"",member.getAuthorities());
    }

    public String getEmail(String token){                                     //get Id From Token

        return (String)Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("email");
    }

    public boolean validateToken(String token){                             //Validate Check
        try{
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
