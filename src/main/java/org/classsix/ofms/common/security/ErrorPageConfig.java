//package org.classsix.ofms.common.security;
//
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.web.servlet.ErrorPage;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//
///**
// * Created by jiang on 2017/5/14.
// * 面向运气，面向心情，面向Bug。
// */
//@Configuration
//public class ErrorPageConfig {
//
//    @Bean
//    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
//        return new MyCustomizer();
//    }
//
//    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {
//
//        @Override
//        public void customize(ConfigurableEmbeddedServletContainer container) {
//            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
//        }
//
//    }
//
//}
