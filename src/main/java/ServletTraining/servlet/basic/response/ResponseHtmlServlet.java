package ServletTraining.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// HTTP 응답 데이터 - 단순 텍스트, HTML
@WebServlet(name = "ResponseHtmlServlet",urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // html으로 반환하고 싶을때는 content-type을 text/html으로 지정해줘야 한다
       response.setContentType("text/html");
       response.setCharacterEncoding("UTF-8");


        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<div>안녕?</div");
        writer.println("</body>");
        writer.println("</html>");
    }
}
