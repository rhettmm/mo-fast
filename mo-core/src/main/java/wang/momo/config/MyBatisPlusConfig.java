package wang.momo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/18 1:37
 */
@Configuration
@MapperScan("wang.momo")
public class MyBatisPlusConfig {

    /**
     * Mybatisplus自带分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
