package ServletTraining.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {
    /**
     *
     * @param paramMap
     * @param model
     * @return
     */
    String process(Map<String, String> paramMap,Map<String,Object> model);
    // viewName을 반환한다
}
