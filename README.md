# blog

### 更新日志
- 2020.7.78 ： 修复了前台标签页面和分页页面的分页
#### 介绍
SpringBoot+Jpa+Thymeleaf构建的博客

#### 软件架构
SpringBoot2.3.1,thymeleaf3.0.2,semanticUi,Jquery,SpringDataJpa，mysql5.7


#### 安装教程

1.  导入项目
2.  创建一个数据库
2.  yam文件中将数据库信息修改为你的数据库信息
3.  启动项目，由于使用了Jpa会为你自动创建表
4.  导入后台管理员账号
    ```sql
    insert  into `t_user`(`id`,`avatar`,`create_time`,`email`,`nickname`,`password`,`type`,`update_time`,`username`) 
    values 
        (1,'/image/head.jpg',NULL,
        'imissyou5201314@outlook.com','硬汉本汉','202cb962ac59075b964b07152d234b70',NULL,NULL,'codehero');

    ```

#### 使用说明

1.  后台管理员账号为codehero，密码为123。
2.  密码采用md5加密，可以去util包下的md5工具类重新生成密码，重新生成的密码需要你手动去MySQL修改t_user表中的password字段
3.  t_blog表中content的字段Jpa生成的为varchar(255),存储的信息为博客正文，需要修改字段类型为longtext

#### 参与贡献

硬汉本汉 QQ 1959368673


