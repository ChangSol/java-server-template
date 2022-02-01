package org.changsol.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * SrpingBoot 시작
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// 아래는 전통적인 WAR 파일로 배포 시 SpringBootServletInitializer 상속
// servlet 3.0 스펙으로 업데이트되면서 web.xml이 없어도 동작이 가능
// 이는, web.xml 설정을 WebApplicationInitializer인터페이스를 구현하여 대신
//@SpringBootApplication
//public class Application extends SpringBootServletInitializer {
//
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }
//}

