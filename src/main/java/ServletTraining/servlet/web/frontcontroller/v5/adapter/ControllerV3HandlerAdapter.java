package ServletTraining.servlet.web.frontcontroller.v5.adapter;

import ServletTraining.servlet.web.frontcontroller.ModelView;
import ServletTraining.servlet.web.frontcontroller.v3.ControllerV3;
import ServletTraining.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
        /**
         * handler가 ControllerV3로 구현된것이 넘어오면 참을 반환
         * 그 이외의 것이 handler로 넘어오면 거짓을 반환한다
         */
    }

    @Override
    public ModelView handler(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;
        /**
         * handler라는 객체를 ControllerV3 타입으로 캐스팅하는걸 말한
         *  다른 타입으로 변환하는 과정을 의미한다
         */

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

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
