package wang.momo.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Ip相关工具类
 * @author rhettmm
 * @version 1.0
 * @date 2020/9/26 9:53
 */
public class IpUtil {

    /**
     * 获取本机IP
     * @return
     */
    public static String getHostAddress(){
        String hostAddress=null;
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            hostAddress = localHost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostAddress;
    }

    /**
     * 获取当前主机名
     * @return
     */
    public static String getHostName(){
        String hostName=null;
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            hostName = localHost.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }


    public static void main(String[] args) {
        System.out.println(getHostAddress());
    }
}
