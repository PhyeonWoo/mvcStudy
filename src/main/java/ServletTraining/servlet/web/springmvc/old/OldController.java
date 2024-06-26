package ServletTraining.servlet.web.springmvc.old;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


@Component("/springmvc/old-controller")  // 스프링 빈의 이름이 지정된다
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");

        // viewResolver 호출부분
        return new ModelAndView("new-form");
    }
}
