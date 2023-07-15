package yoon.test.reactTest2.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberResponse {

    private String email;

    private String role;

    private LocalDateTime regdate;

}
