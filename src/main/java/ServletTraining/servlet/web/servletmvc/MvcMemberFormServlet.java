package ServletTraining.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;



import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 회원 등록 폼 - 컨트롤러
 */

@WebServlet(name = "MvcMemberFormServlet",urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 위의 주소로 전송하겠다는 코드이다
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // /WEB-INF 이 경로안에 JSP가 있으면 욉웨서 JSP를 직접 호출 할 수 없다
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);  // Controller에서 View로 이동할때 사용되는 것이다
        dispatcher.forward(request, response);
        // dispatcher.forward() : 다른 서블릿이나 JSP 로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이 발생한다

    }
}
