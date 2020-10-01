create table mo_core_sys_user(
    id int (11) not null auto_increment comment '主键ID',
    mo_account varchar(10) comment '唯一账号',
    mo_name varchar(20) comment '用户名',
    mo_nick_name varchar(20) comment '昵称',
    password varchar(64) comment '密码',
    profile_pic varchar(200) comment '头像',
    age int(3) comment '年龄',
    phone varchar(20) comment '电话',
    email varchar(50) comment '邮箱',
    deleted int(1) comment '是否删除',
    create_time datetime(0) comment '创建时间',
    create_account varchar(10) comment '创建工号',
    update_time datetime(0) comment '修改时间',
    update_account varchar(10) comment '修改工号',
    PRIMARY key(id) USING BTREE,
    unique index idx_account(mo_account) USING BTREE
);

create table mo_core_sys_role(
    id int(11) not null auto_increment comment '主键ID',
    role_name varchar(50) comment '角色名称',
    role_description varchar(200) comment '角色描述',
    create_time datetime(0) comment '创建时间',
    create_account varchar(10) comment '创建工号',
    update_time datetime(0) comment '修改时间',
    update_account varchar(10) comment '修改工号',
    PRIMARY key(id) USING BTREE,
    unique index idx_role(role_name) USING BTREE
);

create table mo_core_sys_user_role_rel(
    id int(11) not null auto_increment comment '主键ID',
    user_id int(11) comment '用户ID',
    role_id int(11) comment '角色ID',
    create_time datetime(0) comment '创建时间',
    create_account varchar(10) comment '创建工号',
    update_time datetime(0) comment '修改时间',
    update_account varchar(10) comment '修改工号',
    PRIMARY key(id) USING BTREE
);

create table mo_core_sys_action(
    id int(11) not null auto_increment comment '主键ID',
    pid int(11) comment '父ID',
    action_uri varchar(200) comment '权限路径',
    action_desc varchar(200) comment '权限描述',
    menu int(1) comment '是否为菜单',
    permit_type int(1) comment '权限类型 1 页面 0 方法',
    controller_name varchar(200) comment 'controller名',
    controller_method varchar(100) comment 'controller名',
    create_time datetime(0) comment '创建时间',
    create_account varchar(10) comment '创建工号',
    update_time datetime(0) comment '修改时间',
    update_account varchar(10) comment '修改工号',
    PRIMARY key(id) USING BTREE
);

create table mo_core_sys_role_action_rel(
    id int(11) not null auto_increment comment '主键ID',
    role_id int(11) comment '角色ID',
    action_id int(11) comment '权限ID',
    create_time datetime(0) comment '创建时间',
    create_account varchar(10) comment '创建工号',
    update_time datetime(0) comment '修改时间',
    update_account varchar(10) comment '修改工号',
    PRIMARY key(id) USING BTREE
)