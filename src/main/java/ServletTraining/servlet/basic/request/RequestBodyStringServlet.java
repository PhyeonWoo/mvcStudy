package ServletTraining.servlet.basic.request;

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
 * HTTP 요청 데이터 - API 메시지 바디 - 단순 텍스트
 */
@WebServlet(name = "RequestBodyStringServlet",urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // inputStream으로 데이터를 읽을 수 있다
        ServletInputStream inputStream = request.getInputStream();  // stream이란 데이터 통로 라고 한다
        // 바이트 기반 입력 스트림의 최상위가 inputStream 이다

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// inputStream의 값을 UTF-8로 바꾼다
        // 바이트로 들어온 정보를 string형태로 바꿔주고 괄호 안에는 stream으로 받은 정보와 어떤 걸로 바꿀지 타입지정을 해야한다

        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("OKKK");


        // inputStream은 byte 코드를 반환한다. byte 코드를 우리가 읽을 수 있는 String으로 보려면 문자표 (Charset)을 지정해주어야 한다.
        // 여기서는 UTF-8로 지정해주었다
    }
}
