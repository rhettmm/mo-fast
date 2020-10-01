package wang.momo.moadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "wang.momo")
@MapperScan(basePackages = "wang.momo.moadmin.dao")
public class MoAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoAdminApplication.class, args);
    }

}
