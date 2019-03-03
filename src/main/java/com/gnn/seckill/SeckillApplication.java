package com.gnn.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SeckillApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SeckillApplication.class, args);
    }

}





///**
// * 打war包，使用外置tomcat启动application
// */
//public class SeckillApplication extends SpringBootServletInitializer {
//
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(SeckillApplication.class);
//    }
//}