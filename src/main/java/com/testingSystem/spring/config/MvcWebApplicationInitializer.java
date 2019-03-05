package com.testingSystem.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcWebApplicationInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Load database and spring security configurations
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class/*, WebSecurityConfig.class */};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
