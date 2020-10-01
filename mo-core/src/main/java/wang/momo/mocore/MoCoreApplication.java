package wang.momo.mocore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "wang.momo")
public class MoCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoCoreApplication.class, args);
    }

}
