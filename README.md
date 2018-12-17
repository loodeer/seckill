


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

