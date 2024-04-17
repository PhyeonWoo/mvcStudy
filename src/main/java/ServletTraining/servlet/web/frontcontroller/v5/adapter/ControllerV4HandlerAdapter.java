package ServletTraining.servlet.web.frontcontroller.v5.adapter;

import ServletTraining.servlet.web.frontcontroller.ModelView;
import ServletTraining.servlet.web.frontcontroller.v4.ControllerV4;
import ServletTraining.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4); // controllerV4일 경우에만 처리하는 어댑터
    }

    @Override
    public ModelView handler(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;  // controllerV4로 캐스팅

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap,model); // 해당 컨트롤러를 호출

        ModelView mv = new ModelView(viewName); // 모델뷰를 어댑터에서 생성해준다
        mv.setModel(model); // 모델을 세팅해주는 부분

        return mv;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        // 전체 파라미터 조회
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
