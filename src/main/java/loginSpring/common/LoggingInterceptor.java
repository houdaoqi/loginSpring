package loginSpring.common;

/**
 * Created by lenovo on 11/7/2016.
 */
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingInterceptor implements HandlerInterceptor {

    //private static final Logger logger = Logger.getLogger("AuthInterceptor");
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
    private long startTime;
    private long endTime;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        startTime = System.currentTimeMillis();
        logger.trace(" The start time of the request is: " + startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.trace(" The request is processing... ");
//        if(modelAndView.getModelMap().containsKey("status")){
//            String status = (String) modelAndView.getModelMap().get("status");
//            if(status.equals("SUCCESS!")){
//                status = "Authentication " + status;
//                modelAndView.getModelMap().put("status",status);
//            }
//        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        endTime = System.currentTimeMillis();
        logger.trace(" The processing time is: " + (double)(endTime - startTime) / (1000) + "s");
    }
}
