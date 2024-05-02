package com.prowings.project_management.confi;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.prowings.project_management")
@EnableAspectJAutoProxy
public class AppConfig {

}
