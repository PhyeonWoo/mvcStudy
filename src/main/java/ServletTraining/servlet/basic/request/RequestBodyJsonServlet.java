package ServletTraining.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HTTP 요청 데이터 - API 메시지 바디 - JSON
 */
@WebServlet(name = "RequestBodyJsonServlet",urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    // 미리 만들어둔 helloData로 변환하기 위해 쓰는 라이브러리 이다
    // ObjectMapper가 그 라이브러리이다

    // JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환할때 사용하는게 ObjectMapper 이다
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 입력받는 부분
        ServletInputStream inputStream = request.getInputStream();

        // JSON으로 출력하기 위한 부분
        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("message = " + message);

        // helloData의 값을 읽어올 수 있다
        HelloData helloData = objectMapper.readValue(message, HelloData.class);

        System.out.println("helloData.getUsername() = " + helloData.getUsername());
        System.out.println("helloData.getAge() = " + helloData.getAge());

        response.getWriter().write("안녕하세요");
    }
}
