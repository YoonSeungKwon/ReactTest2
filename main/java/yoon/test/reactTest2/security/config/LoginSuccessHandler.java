package yoon.test.reactTest2.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import yoon.test.reactTest2.domain.Members;
import yoon.test.reactTest2.security.jwt.JwtProvider;
import yoon.test.reactTest2.vo.response.Message;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Members member = (Members) authentication.getPrincipal();
        String token = jwtProvider.createToken(member.getEmail());
        loginSuccess(token);
    }

    public ResponseEntity<Message> loginSuccess(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));
        headers.set("Authorization", "Bearer " + token);
        Message message = new Message();

        message.setMessage("Login Success");
        message.setData(token);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
