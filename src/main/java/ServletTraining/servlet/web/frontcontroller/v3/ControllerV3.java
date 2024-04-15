package ServletTraining.servlet.web.frontcontroller.v3;

import ServletTraining.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // 프론트 컨트롤러가 paramMap에 담아서 호출해주면 된다
    ModelView process(Map<String, String> paramMap);
}
