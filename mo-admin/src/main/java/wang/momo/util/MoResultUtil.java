package wang.momo.util;

import wang.momo.common.MoResult;

/**
 * 统一消息工具类，简化代码
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/1 22:43
 */
public class MoResultUtil {

    private static MoResult moResult;
    static {
        moResult=new MoResult();
    }

    /**
     * 请求成功
     * @param o
     * @return
     */
    public static MoResult success(Object o){
        return moResult.setCode(0).setMsg("success").setData(o);
    }


    public static MoResult error(Integer code,String msg){
        return moResult.setCode(code).setMsg(msg);
    }
}
