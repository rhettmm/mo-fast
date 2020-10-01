package wang.momo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import wang.momo.util.IpUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 配置启动后自动打开浏览器，方便开发
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/26 9:43
 */
@Configuration
public class RunBrowser implements CommandLineRunner {
    private static Logger log= LoggerFactory.getLogger(RunBrowser.class);
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${server.port}")
    private String port;

    @EventListener({ApplicationEvent.class})
    void applicationReadyEvent(){
        System.out.println("启动监听事件！"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }


    @Override
    public void run(String... args) throws Exception {
        String url="http://"+IpUtil.getHostAddress()+":"+port+contextPath;
        log.info("moadmin启动完成！打开浏览器"+url);
        try {
            Properties properties = System.getProperties();
            String osName = properties.getProperty("os.name");
            if(osName.toLowerCase().startsWith("win")){
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
