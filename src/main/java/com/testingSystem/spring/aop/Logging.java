package com.testingSystem.spring.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@Component
@Aspect
public class Logging {

    private static final Logger log = Logger.getLogger(Logging.class);

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controller() {
    }

    @After(value = "controller() && args(..,request, response)", argNames = "request,response")
    public void LogToControllers(HttpServletRequest request, HttpServletResponse response) throws IOException {

    //  Я пока хз что логировать, может Леша в понедельник попонятней объяснит)
        Map requestParameterMap =  request.getParameterMap();

        log.info("===============================================");
        for (Object key : requestParameterMap.keySet()){
            log.info("PARAM = " + key + ", VALUE = " +  requestParameterMap.get(key));
        }
        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()){
            log.info(headers.nextElement());
        }

        log.info("Method:    "  + request.getMethod());
        log.info("URI:   " + request.getRequestURI());
        log.info("PATHINFO " + request.getPathInfo());
        log.info("===============================================");
    }





}
