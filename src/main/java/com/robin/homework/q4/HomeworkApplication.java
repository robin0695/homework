package com.robin.homework.q4;

import com.robin.homework.q4.model.Service;
import com.robin.homework.q4.service.ServiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ServiceService serviceService) {
        return args -> {
            serviceService.save(new Service(1L, "svc1", 300.00, "http://google.com.it"));
            serviceService.save(new Service(2L, "svc2", 200.00, "http://google.com.it"));
            serviceService.save(new Service(3L, "svc3", 100.00, "http://google.com.it"));
            serviceService.save(new Service(4L, "svc4", 5.00, "http://google.com.it"));
            serviceService.save(new Service(5L, "svc5", 3.00, "http://google.com.it"));
            serviceService.save(new Service(6L, "svc6", 500.00, "http://google.com.it"));
            serviceService.save(new Service(7L, "svc7", 30.00, "http://google.com.it"));
        };
    }
}
