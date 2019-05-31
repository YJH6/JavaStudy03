# Spring Boot

* 微服务:一个项目可以由多个小型服务构成
* Spring Boot可以快速开发微服务模块
  * 简化j2ee开发
  * 整个spring技术栈的整合(整合springmvc spring等等)
  * 整个j2ee技术的整合(整合mybatis redis)

## 目录结构

* resources:

  static:静态资源(js css 图片 音频 视频)

  templates:模板文件(模板引擎kfreemarker,thymeleaf;默认不支持jsp)

  application.properties:配置文件

## web程序

* spring boot内置了tomcat,并且不需要打成war再执行

* 可以在appication.properties对端口号等服务端进行配置

  server.port=****

* spring boot将各个应用/第三方框架 设置成一个个"场景"stater,以后要用哪个,只需要引入那个场景即可

  选完之后,spring boot就会将该场景所需要的所有依赖,自动注入

* @SpringBootApplication:spring boot的主配置类

  该注解包含:

  ​	@SpringBootConfiguration:包含@Configuration,表示"配置类":

  ​		1.表示该类是一个配置类

  ```java
  @Configuration
  public class A{//表示A是一个用于配置的类
  }
  ```

  ​		2.加了@Configuration注解的类,会自动纳入Spring容器(@Component)

  ​	@EnableAutoConfiguration:使spring boot可以自动配置

  ​	spring boot在启动时,会根据META-TNF/spring.factories找到相应的三方依赖,,并引入该项目

* 配置文件

  作用:spring boot 自动配置(约定),可以使用配置文件,对默认

  默认全局配置文件:

  ​	application.properties:key=value 或行内写法(k: v,[set/List/数组] {map,对面类型的属性},[]可以省,{}不能省)

  ​	application.yml:

  ​		yaml 不是一个标记文档	注意:1.k:空格v或行内赋值	2.通过垂直对齐,指定层次关系	3.默认可以不写引号;""会将其中的转义符进行转义,其他不会

  ```
  server:
  		port: 8080
  		path: /a/b/c
  ```

  ​		xml是一个标记文档

  ```
  <server>
  	<port>8080</port>
  	<path>/a/b/c</path>
  </server>
  ```
  
  * 通过yaml给对象注入值:
  
    * 注入值
  
      student:
  
      ​	name: yjh
  
      ​	age: 19
  
    * 绑定
  
      @Component//将此Javabean放入spring容器
  
      @ConfigurationProperties(prefix = "xxxx")
  
      public class Student
  
  * 绑定:

    ​						@ConfigurationProperties				@Value				(二者可以互补)
  
      注入方式					批量注入									单个
  
      松散语法					支持											不支持
  
      SpEL		   				不支持										支持
  
      JSR303数据校验		 支持										   不支持
    
      注入复杂类型			 支持										    不支持	
    
    松散语法:userName=user-name
    
    SpEL:赋值:{"${变量}"}
    
    数据校验:
    
    ```java
    @Validated//开启jsr303数据校验的注解
    public class A{
    	@Email
    	private String email;
    }
    ```
    
    简单类型:(8个基本类型/String/Date)
    

* @PropertySource:默认会加载application.properties/application.yml文件中的数据;

  ​	@PropertySource(value = {"classpath:文件名.properties"})加载.properties文件中的数据;

  ​	但是,@PropertySource只能加载properties,不能加载yml