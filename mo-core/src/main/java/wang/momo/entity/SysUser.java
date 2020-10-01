package wang.momo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("mo_core_sys_user")
public class SysUser implements Serializable{

        /**
        * 主键ID
        */
        @TableId(type = IdType.AUTO)
        private Integer id;

        /**
        * 唯一账号
        */
        private String moAccount;

        /**
        * 用户名
        */
        private String moName;

        /**
        * 昵称
        */
        private String moNickName;

        /**
        * 密码
        */
        private String password;

        /**
        * 头像
        */
        private String profilePic;

        /**
        * 年龄
        */
        private Integer age;

        /**
        * 电话
        */
        private String phone;

        /**
        * 邮箱
        */
        private String email;

        /**
        * 是否删除
        */
        private Integer deleted;

        /**
        * 创建时间
        */
        private Date createTime;

        /**
        * 创建工号
        */
        private String createAccount;

        /**
        * 修改时间
        */
        private Date updateTime;

        /**
        * 修改工号
        */
        private String updateAccount;


}