package ServletTraining.servlet.web.frontcontroller.v2.controller;

import ServletTraining.servlet.domain.Member;
import ServletTraining.servlet.domain.MemberRepository;
import ServletTraining.servlet.web.frontcontroller.MyView;
import ServletTraining.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> memberList = memberRepository.findAll();

        request.setAttribute("memberList",memberList);
        return new MyView("/WEB-INF/views/members.jsp");
    }
}
