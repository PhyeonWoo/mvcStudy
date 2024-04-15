package ServletTraining.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * GET 요청 서블릿 처리하는 코드
 */
@WebServlet(name = "requestServlet",urlPatterns = "/request-param")
public class RequestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("[전체 파라미터 조회]");
        request.getParameterNames().asIterator()
                .forEachRemaining(param -> System.out.println(param + "="+request.getParameter(param)));

        System.out.println("[단일 파라미터 조회]");
        String userName = request.getParameter("username");
        System.out.println("userName = " + userName);
        String age = request.getParameter("age");
        System.out.println("age =" +age);

        System.out.println("[동일한 이름 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for (String username : usernames) {
            System.out.println("username = " + username);
        }
        String[] ages = request.getParameterValues("age");
        for (String agess : ages) {
            System.out.println("age = " + agess);
        }

        response.getWriter().write("ok");
//        super.service(request, response);
    }
}
