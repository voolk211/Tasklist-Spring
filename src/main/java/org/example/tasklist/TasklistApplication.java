package org.example.tasklist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("org.example.tasklist.repository")
@SpringBootApplication()
@EnableTransactionManagement
public class TasklistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasklistApplication.class, args);
    }

}
