package ServletTraining.servlet.web.frontcontroller.v4.controller;

import ServletTraining.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;


/**
 * 구현 Controller
 */
public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";  // new-form의 논리주소를 넘겨준다
    }
}
