
### 项目仅供学习交流使用。

本项目主要目的是为了进行 java 实践，90% + 的代码来自 `龙虾三少` 大佬在慕课网的视频课程，在此特再次感谢大佬。

不过也有一些不一样的地方，是按我自己的思考来的，比如价格我是以分为单位存在数据库里的,itemVO 下返回 promoVO 字段等等，慕课网的同学如果参考该项目请特别注意。

课程地址：[SpringBoot构建电商基础秒杀项目](https://www.imooc.com/learn/1079) ，强烈推荐！！

[html 目录](https://github.com/loodeer/seckill/tree/master/html) 下为前端代码，直接通过浏览器打开本地文件访问。剩余的为服务端代码。

### mysql DDL 

用户信息表
```
CREATE TABLE `user_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'uid',
  `name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `telphone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `register_mode` int(11) DEFAULT NULL COMMENT '注册来源',
  `third_party_id` varchar(11) DEFAULT NULL COMMENT '关联第三方账号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tel_unique` (`telphone`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
```
用户密码表
```
CREATE TABLE `user_password` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(64) DEFAULT NULL COMMENT '加密密码',
  `user_id` int(11) DEFAULT NULL COMMENT 'user_info.id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户密码表';
```
商品信息表
```
CREATE TABLE `item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '商品标题',
  `price` int(11) NOT NULL COMMENT '价格',
  `description` varchar(512) NOT NULL DEFAULT '' COMMENT '描述',
  `sales` int(11) NOT NULL DEFAULT '0' COMMENT '销量',
  `img_url` varchar(128) NOT NULL DEFAULT '' COMMENT '商品图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='商品信息表';
```
商品库存表
```
CREATE TABLE `item_stock` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL COMMENT '库存',
  `item_id` int(11) NOT NULL COMMENT 'item.id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='商品库存表';
```
订单表
```
CREATE TABLE `order_info` (
  `id` varchar(16) NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL COMMENT 'user_info.id',
  `item_id` int(11) NOT NULL COMMENT 'item.id',
  `item_price` int(11) NOT NULL COMMENT '商品价格',
  `amount` int(11) NOT NULL COMMENT '购买件数',
  `order_price` int(11) NOT NULL COMMENT '订单价格',
  `promo_id` int(11) DEFAULT '0' COMMENT '秒杀活动id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
```
计数器表
```
CREATE TABLE `sequence_info` (
  `name` varchar(32) NOT NULL DEFAULT '',
  `current_value` int(11) NOT NULL DEFAULT '0' COMMENT '当前值',
  `step` int(11) NOT NULL DEFAULT '1' COMMENT '步长'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='计数器表';
```
秒杀信息表
```
CREATE TABLE `promo_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '秒杀活动id',
  `promo_name` varchar(32) NOT NULL DEFAULT '' COMMENT '秒杀活动名',
  `start_time` datetime NOT NULL COMMENT '秒杀开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `promo_item_price` int(11) NOT NULL COMMENT '秒杀价',
  `item_id` int(11) NOT NULL COMMENT 'item.id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='秒杀信息表';
```

### 使用到的外部依赖

- org.springframework.boot:spring-boot-starter-web
- mysql:mysql-connector-java
- com.alibaba:druid
- org.mybatis.spring.boot:mybatis-spring-boot-starter
- org.apache.commons:commons-lang3
- org.hibernate:hibernate-validator
- joda-time:joda-time
- junit:junit
- org.springframework:spring-test
- org.mybatis.generator：mybatis-generator-maven-plugin （插件）

### 过程分解

- IDEA 生成 maven 项目


- 集成 spring-boot
    - https://spring.io/guides/gs/rest-service/

- mybatis-generator-maven-plugin 插件使用

- 短信验证码获取接口 `com.loodeer.controller.UserController.getOtp`

- 前端 ajax 调用接口获取验证码 `html/getotp.html`
    - jquery 使用
    - CrossOrigin 注解

- 用户注册接口 `com.loodeer.controller.UserController.register`
    - apache-common-lang 包使用
    - Transactional 事务注解

- 前端用户注册页面 `html/register.html`
    - MD5Encoder 默认只能 encode 16 位的字符串
    - 允许跨域 @CrossOrigin(allowCredentials="true", allowedHeaders = "*")
        - allowedHeaders 允许前端将 token 放入 header 做 session 共享的跨域请求。
        - allowCredentials 授信后，需前端也设置 xhfFields 授信才能实现跨域 session 共享。xhrFields: {withCredentials: true}, 
    - insertSelective 中设置 keyProperty="id" useGeneratedKeys="true" 使得插入完后的 DO 生成自增 id 。
    
- 规则校验优化 `com.loodeer.validator.ValidatorImpl`
    - 使用 hibernate-validator 通过注解来完成模型参数校验
    
- 用户登陆接口 `com.loodeer.controller.UserController.login`
    - Mapper.xml 文件里 parameterType、jdbcType 分别指定入参和数据库字段类型。
    
- 登陆前端页面 `html/login.html`
 
- 设计商品领域模型 `com.loodeer.service.model.ItemModel`
    - 再设计表结构。 item 表、item_stock 表
    - mybatis generator 自动生成 DO DAO Mapper
    - Mapper.xml 里面 insert 语句增加 useGeneratedKeys="true" keyProperty="id"

- 商品服务 `com.loodeer.service.impl.ItemServiceImpl`
    - 实现类要加上 `@Service` 注解
    - 实现方法要加上 `@Transactional` 注解
    - `createItem` 方法入参和出参都是 `itemModel` 也是蛮奇怪的
    
- 商品创建页面 `html/createitem.html`
    - 创建成功，item 表 title、description 中文乱码（待解决）
    
- 商品详情页 
    - 分转为元： `BigDecimal.valueOf(itemModel.getPrice()).divide(new BigDecimal(100))`

- 商品列表页 `com.loodeer.controller.ItemController.getItem()`
    - `List<ItemVO> itemVOList = new ArrayList<>();` 新建 List
    - `itemModelList.get(i)` 挨个获取 List 的元素
    
- 商品列表页 `html/itemlist.html`
    - 动态渲染 dom 数据
    - 针对每一行生成一个点击跳转事件，`$(this).data("id")` 解决闭包内无法获取外部变量问题

- 商品详情页 `html/itemdetail.html`
    - 动态渲染 dom 数据

- 交易领域模型设计 `com.loodeer.service.model.OrderModel`

- 下单接口 `com.loodeer.service.impl.OrderServiceImpl.createOrder`
    - mysql 通过自设计 `sequence` 表，来实现递增值
    - 事务执行到了就提交 `Propagation.REQUIRES_NEW`
    - `LocalDateTime.now();` 获取时间
    - `now.format(DateTimeFormatter.BASIC_ISO_DATE);` 时间格式转化
    - DOMapper 里面啥时候要写 @Param 啥时候不要写？
    
- 秒杀模型 `com.loodeer.service.model.PromoModel`
    - `joda-time` 
    - `insertSelective(promoDO)` 后 promoDO 里即有 `id`,前提是 `mapper.xml` 里插入语句设置了 `useGeneratedKeys="true" keyProperty="id"`
    - `joda-time` 格式的数据可以直接 `.toDate()` 转成 `Date` 格式
   
- 插入数据库中文乱码解决
    - `spring.datasource.url = jdbc:mysql://127.0.0.1:3306/seckill?characterEncoding=utf8` 增加 `?characterEncoding=utf8` 段
    
- 秒杀活动获取服务
    - `new DateTime(promoDO.getStartTime())` `Date` 格式转为 `joda-time` 的 `DateTime` 格式 
    - `dateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"))` 转为 String 类型指定格式的输出
    
- 秒杀进行中已秒杀价购买
    - REST 接口入参格式不匹配，接口就报错了
