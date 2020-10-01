package wang.momo.mogenerator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "wang.momo")
public class MoGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoGeneratorApplication.class, args);
    }

    @Bean
    public PropertiesConfiguration getConfig(){
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
            System.out.println("获取配置文件失败！");
        }
        return null;
    }

}
