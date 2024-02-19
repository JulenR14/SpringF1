package org.api.springf1;

import org.api.springf1.repository.DriverRepository;
import org.api.springf1.service.DriverService;
import org.api.springf1.service.DriverServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringF1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringF1Application.class, args);
    }

}
