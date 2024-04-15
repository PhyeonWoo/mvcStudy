package ServletTraining.servlet.web.frontcontroller.v3;

import ServletTraining.servlet.web.frontcontroller.ModelView;
import ServletTraining.servlet.web.frontcontroller.MyView;
import ServletTraining.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import ServletTraining.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import ServletTraining.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import ServletTraining.servlet.web.frontcontroller.v4.ControllerV4;
import ServletTraining.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import ServletTraining.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import ServletTraining.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV4",urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form",new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save",new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members/members",new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV4 controllerV4 = controllerV4Map.get(requestURI);

        if(controllerV4 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // command + option + m
        Map<String, String> paramMap = createParamMap(request);
        Map<String,Object> model = new HashMap<>();

        String viewName = controllerV4.process(paramMap, model);
        MyView view = viewResolver(viewName);  // 물리이름과 논리이름이 합쳐진 MyView를 반환

        view.render(model,request,response);
        /**
         * 뷰 객체를 통해서 HTML 화면을 렌더링 한다
         * 뷰 객체의 render()는 모델 정보도 함께 받는다
         */
    }

    //paramMap을 넘겨줘야 한다
    // param을 다 뽑는다
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        // 전체 파라미터 조회
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }


    // 실제 위치를 찾아주는 메서드
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/"+viewName+".jsp");  // 물리 뷰 경로
    }

}
