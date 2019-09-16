package com.css.aq.base;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan(basePackages = {"com.css.aq.base.*.mapper"})
@SpringBootApplication(scanBasePackages = "com")
@EnableSwagger2 // 启用swagger
public class StartApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	/**
	 * 解决Jackson导致Long性数据在前端丢失精度的问题
	 * @return
	 */
	@Bean("jackson2ObjectMapperBuilderCustomizer")
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
		Jackson2ObjectMapperBuilderCustomizer customizer = new Jackson2ObjectMapperBuilderCustomizer() {
			@Override
			public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
				jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance)
						.serializerByType(Long.TYPE,ToStringSerializer.instance);
			}
		};
		return customizer;
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StartApplication.class);
	}

}
