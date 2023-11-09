package com.company;

import com.company.request.UserLoginRequest;
import com.company.respons.UserRespons;
import com.company.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Launcher{

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

}
