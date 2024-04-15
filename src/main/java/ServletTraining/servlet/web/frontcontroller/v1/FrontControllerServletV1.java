package ServletTraining.servlet.web.frontcontroller.v1;

import ServletTraining.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import ServletTraining.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import ServletTraining.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControlelrServletV1",urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String,ControllerV1> controllerV1Map = new HashMap<>();

    // 생성자
    public FrontControllerServletV1() {
        /**
         * controllerV1Map
         * key : 매핑 URL
         * value : 호출될 컨트롤러
         */
        controllerV1Map.put("/front-controller/v1/members/new-form",new MemberFormControllerV1());
        // localhost:8080/front-controller/v1/members/new-form 입력하면 MemberFormControllerV1을 호출한다
        controllerV1Map.put("/front-controller/v1/members/save",new MemberSaveControllerV1());
        // localhost:8080/front-controller/v1/members/save 입력하면 MemberSaveControllerV1을 호출한다
        controllerV1Map.put("/front-controller/v1/members/members",new MemberListControllerV1());
        // localhost:8080/front-controller/v1/members/members 입력하면 MemberListControllerV1을 호출한다
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // 위에 입력한 URI를 입력하면 requestURI로 입력받는다
        String requestURI = request.getRequestURI();
        // 해당하는 값을 controller에 넣는다

        ControllerV1 controller = controllerV1Map.get(requestURI);
        if(controller == null) {
            // controller에 해당하는 값이 없을때 404를 리턴
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 있을 경우 인터페이스 process를 호출한다
        controller.process(request,response);
    }
}
