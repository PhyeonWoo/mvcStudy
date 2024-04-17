package ServletTraining.servlet.web.frontcontroller.v5;

import ServletTraining.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler); // 넘어온 Controller를 해결할 수 있는지 판단하는 부분이다

    ModelView handler(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException;
    /**
     * 전에는 프론트 컨트롤러가 실제 컨트롤러를 호출했지만 이제는 이 어댑터를 통해서 실제 컨트롤러가 호출 된다.
     * 어댑터는 실제 컨트롤러를 호출하고, 그 결과로 ModelView를 반환해야 한다.
     */

}
