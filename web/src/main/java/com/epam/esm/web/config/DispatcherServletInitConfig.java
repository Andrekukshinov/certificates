package com.epam.esm.web.config;

import com.epam.esm.persistence.config.PersistenceConfig;
import com.epam.esm.service.config.ServiceConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String ALL_REQUESTS = "/";

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfiguration.class, PersistenceConfig.class, ServiceConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ALL_REQUESTS};
    }
}
