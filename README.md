


- 短信验证码获取接口

- 前端 ajax 调用接口获取验证码
    - jquery 使用
    - CrossOrigin 注解

- 用户注册接口
    - apache-common-lang 包使用
    - Transactional 事务注解

- 前端用户注册页面
    - MD5Encoder 默认只能 encode 16 位的字符串
    - 允许跨域 @CrossOrigin(allowCredentials="true", allowedHeaders = "*")
        - allowedHeaders 允许前端将 token 放入 header 做 session 共享的跨域请求。
        - allowCredentials 授信后，需前端也设置 xhfFields 授信才能实现跨域 session 共享。xhrFields: {withCredentials: true}, 
    - insertSelective 中设置 keyProperty="id" useGeneratedKeys="true" 使得插入完后的 DO 生成自增 id 。
    
 - 用户登陆接口
    - Mapper.xml 文件里 parameterType、jdbcType 分别指定入参和数据库字段类型。
