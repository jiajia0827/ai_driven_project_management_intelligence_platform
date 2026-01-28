package org.example.ai_driven_project_management_intelligence_platform.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * MyBatis 配置：显式提供 SqlSessionFactory、SqlSessionTemplate，解决与 JPA 同用 DataSource 时
 * MyBatis 自动配置未生效导致的 "sqlSessionFactory or sqlSessionTemplate required" 问题。
 */
@Configuration
@MapperScan("org.example.ai_driven_project_management_intelligence_platform.mapper")
public class MyBatisConfig {

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 使用 MyBatis 的 Configuration，并避免与 Spring 的 @Configuration 同名冲突
        org.apache.ibatis.session.Configuration mybatisConfig =
                new org.apache.ibatis.session.Configuration();
        mybatisConfig.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(mybatisConfig);
        bean.setMapperLocations(new org.springframework.core.io.Resource[]{
            new org.springframework.core.io.ClassPathResource("mapper/ProjectMapper.xml")
        });
        return bean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

