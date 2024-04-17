package ServletTraining.servlet.web.frontcontroller.v5;

import ServletTraining.servlet.web.frontcontroller.ModelView;
import ServletTraining.servlet.web.frontcontroller.MyView;
import ServletTraining.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import ServletTraining.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import ServletTraining.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import ServletTraining.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import ServletTraining.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import ServletTraining.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import ServletTraining.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import ServletTraining.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet{

    private Map<String,Object> handlerMappingMap = new HashMap<>();// 무슨 핸들러가 들어올지 모르므로 Object를 넣는다
    /**
     * 매핑 정보의 값이 `ControllerV3` , `ControllerV4` 같은 인터페이스에서 아무 값이나 받을 수 있는 `Object` 로 변경되었다
     */
    private List<MyHandlerAdapter> handlerAdapters = new ArrayList<>(); // 여러 어댑터중에서 하나를 꺼내써야하기 때문에 List형태로 가져온다


    // 생성자
    public FrontControllerServletV5() {
        initHandlerMappingMap(); // 핸들러 매핑 초기화(등록)
        initHandlerAdapters(); // handlerAdapter에 ControllerV3Handler를 넣어준다 & 어댑터 초기(등록)
    }

    private void initHandlerMappingMap() {
        // v3
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form",new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save",new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members",new MemberListControllerV3());

        // v4
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form",new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save",new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members",new MemberListControllerV4());
    }


    // 핸들러 매핑 부분
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());  // 해당 컨트롤러를 처리할 수 있는 어댑터 추가
        handlerAdapters.add(new ControllerV4HandlerAdapter());  // 해당 컨트롤러를 처리할 수 있는 어댑터 추가
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * 핸들러 매핑 부분
         */
        // MemberFormControllerV3 or V4가 반환이 된다
        Object handler = getHandler(request);  // handler로 위의 주소에 해당하는 값이 있는 요청을 찾는다
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // ControllerV4HandlerAdapter가 호출된다
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        /**
         * 어댑터 호출부분
         */
        ModelView mv = adapter.handler(request, response, handler);

        String viewName = mv.getViewName();  // 논리주소를 가져온다
        MyView view = viewResolver(viewName); // viewResolver에서 넘겨받은 논리주소 viewName을 물리주소로 합쳐준다

        view.render(mv.getModel(),request,response); // 반환해주는 값
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {

        /**
         * 핸들러 처리할 수 있는 어댑터 조회
         */
        for(MyHandlerAdapter adapter : handlerAdapters) {  // V4인지 V3인지 확인하는 부분
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다."+ handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/"+viewName+".jsp");  // 물리 뷰 경로
    }
}
