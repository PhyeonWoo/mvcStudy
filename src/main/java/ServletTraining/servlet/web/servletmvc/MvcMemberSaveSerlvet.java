package ServletTraining.servlet.web.servletmvc;

import ServletTraining.servlet.domain.Member;
import ServletTraining.servlet.domain.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;


@WebServlet(name = "MvcMemberSaveServlet",urlPatterns = "/servlet-mvc/members/save")
//@WebServlet(name = "MvcMemberSaveSerlvet",urlPatterns = "/servlet-mvc/members/save")
class MvcMemberSaveSerlvet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance(); // 싱글톤 방식

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        // request.getParameter는 String형태로 받기 때문에 int로 바꿔주는
        // Integer.parseInt를 사용한다

        Member member = new Member(username, age);
        System.out.println("member = " + member);  // username과 age를 출력하는 값
        memberRepository.save(member);

       // Model에 데이터를 보관한다
        request.setAttribute("member",member);  // setAttribute를 사용하면 request객체에 데이터를 보관해서 뷰에 전달 가능
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request,response);
    }
}
