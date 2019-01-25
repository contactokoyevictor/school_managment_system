/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.app.config;
import java.io.File;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRegistration.Dynamic;  
  
import org.springframework.web.WebApplicationInitializer;  
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;  
import org.springframework.web.servlet.DispatcherServlet;  

/**
 *
 * @author VICTOR_OKOYE
 */

  
public class WebInitializer implements WebApplicationInitializer {
    private final int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {        
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();  
        ctx.register(Config.class);  
        servletContext.addListener(new SessionListener());
        ctx.setServletContext(servletContext);
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        // temp file will be uploaded here
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        //register a MultipartConfigElement
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

        servlet.setMultipartConfig(multipartConfigElement);
        servlet.addMapping("/");  
        servlet.setLoadOnStartup(1);
    }
    
    
    
}
