
- 插入数据库中文乱码解决
    - `spring.datasource.url = jdbc:mysql://127.0.0.1:3306/seckill?characterEncoding=utf8` 增加 `?characterEncoding=utf8` 段

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
   
