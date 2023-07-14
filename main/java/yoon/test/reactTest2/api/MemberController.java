package yoon.test.reactTest2.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoon.test.reactTest2.service.MemberService;
import yoon.test.reactTest2.vo.request.MemberDto;
import yoon.test.reactTest2.vo.response.Message;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<Message> memberJoin(@RequestBody MemberDto dto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));
        Message message = new Message();

        MemberDto result = memberService.join(dto);

        message.setMessage("user info");
        message.setData(result);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
