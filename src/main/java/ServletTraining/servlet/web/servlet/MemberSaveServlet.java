package ServletTraining.servlet.web.servlet;

import ServletTraining.servlet.domain.Member;
import ServletTraining.servlet.domain.MemberRepository;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * `MemberSaveServlet` 은 다음 순서로 동작한다.
 * 1. 파라미터를 조회해서 Member 객체를 만든다.
 * 2. Member 객체를 MemberRepository를 통해서 저장한다.
 * 3. Member 객체를 사용해서 결과 화면용 HTML을 동적으로 만들어서 응답한다.
 */

/**
 * 회원 저장
 */


@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("MemberSaveServlet.service");

        String username = request.getParameter("username");

        // request.getParameter는 String형태로 받기 때문에 int로 바꿔주는
        // Integer.parseInt를 사용한다
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        System.out.println("member = " + member);  // username과 age를 출력하는 값

        memberRepository.save(member);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id=" + member.getId() + "</li>\n" +
                "    <li>username=" + member.getUsername() + "</li>\n" +
                " <li>age=" + member.getAge() + "</li>\n" + "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
                "</html>");
    }
}


