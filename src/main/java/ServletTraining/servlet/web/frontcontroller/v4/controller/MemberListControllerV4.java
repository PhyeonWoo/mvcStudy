package ServletTraining.servlet.web.frontcontroller.v4.controller;

import ServletTraining.servlet.domain.Member;
import ServletTraining.servlet.domain.MemberRepository;
import ServletTraining.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // memberRepository에서 찾아서 List형태로 넘겨준다
        List<Member> members = memberRepository.findAll();


        model.put("members",members);

        return "members"; // 논리주소 members
    }
}
