package yoon.test.reactTest2.api;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoon.test.reactTest2.security.jwt.JwtProvider;
import yoon.test.reactTest2.service.MemberService;
import yoon.test.reactTest2.vo.request.MemberDto;
import yoon.test.reactTest2.vo.response.MemberResponse;
import yoon.test.reactTest2.vo.response.Message;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;


    @PostMapping("/join")
    public ResponseEntity<Message> memberJoin(@RequestBody MemberDto dto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));
        Message message = new Message();

        MemberResponse result = memberService.join(dto);
        if(result == null){
            message.setStatus(HttpStatus.BAD_REQUEST);
            message.setMessage("Register Failed");

            return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
        }
        message.setStatus(HttpStatus.OK);
        message.setMessage("user info");
        message.setData(result);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Message> memberLogin(@RequestBody MemberDto dto, HttpServletResponse response){
        System.out.println(dto.getEmail());
        System.out.println(dto.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application" ,"JSON", Charset.forName("UTF-8")));
        Message message = new Message();
        try {
            MemberResponse result = memberService.login(dto);
            message.setStatus(HttpStatus.OK);
            message.setMessage("Login Success");
            message.setData(result);

            String token = jwtProvider.createToken(result.getEmail());
            System.out.println(token);

            response.setHeader("Authorization", token);

        }catch(Exception e){
            message.setStatus(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
            message.setData(null);
            new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
