package yoon.test.reactTest2.vo.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class Message {

    private HttpStatus status;

    private String message;

    private Object data;

    public Message(){
        this.status = HttpStatus.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }

}
