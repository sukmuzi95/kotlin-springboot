//package com.example.kotlinspringbootboard.config
//
//import org.springframework.context.annotation.Configuration
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
//
//@Configuration
//class MvcConfig : WebMvcConfigurer {
//
//    companion object {
//        val CLASSPATH_RESOURCE_LOCATIONS = arrayOf("classpath:/templates", "classpath:/static/")
//    }
//    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
//        registry
//                .addResourceHandler("/**")
//                .addResourceLocations("classpath:/templates", "classpath:/static/")
//    }
//}