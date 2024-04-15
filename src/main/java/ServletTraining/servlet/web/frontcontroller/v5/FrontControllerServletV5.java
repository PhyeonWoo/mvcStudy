package ServletTraining.servlet.web.frontcontroller.v5;

import ServletTraining.servlet.web.frontcontroller.ModelView;
import ServletTraining.servlet.web.frontcontroller.MyView;
import ServletTraining.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import ServletTraining.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import ServletTraining.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import ServletTraining.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
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
    private List<MyHandlerAdapter> handlerAdapters = new ArrayList<>(); // 여러 어댑터중에서 하나를 꺼내써야하기 때문에 List형태로 가져온다

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters(); // handlerAdapter에 ControllerV3Handler를 넣어준다
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form",new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save",new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members",new MemberListControllerV3());
    }

    private boolean initHandlerAdapters() {
        return handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // MemberFormControllerV3가 반환이 된다
        Object handler = getHandler(request);  // handler로 위의 주소에 해당하는 값이 있는 요청을 찾는다
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handler(request, response, handler);  // 어댑터 호출부분

        String viewName = mv.getViewName();  // 논리주소를 가져온다 
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request,response);

    }


    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;
        for(MyHandlerAdapter adapter : handlerAdapters) {
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
