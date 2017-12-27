package com.springmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Igor on 02.11.2017.
 */
public class WebAppInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
