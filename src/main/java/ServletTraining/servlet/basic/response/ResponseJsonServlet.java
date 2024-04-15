package ServletTraining.servlet.basic.response;

import ServletTraining.servlet.basic.request.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// HTTP 응답 데이터 - API JSON
@WebServlet(name = "ResponseJsonServlet",urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        HelloData data = new HelloData();
        data.setAge(24);
        data.setUsername("박현우");

        String result = objectMapper.writeValueAsString(data);
        // JSON 문자로 변경해주는 코드

        response.getWriter().write(result);
    }
}
