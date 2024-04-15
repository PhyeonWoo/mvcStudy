package ServletTraining.servlet.web.frontcontroller.v5;

import ServletTraining.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler); // 넘어온 Controller를 해결할 수 있는지 판단하는 부분이다

    ModelView handler(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;

}
