package com.pl.spring.generalJavaController;

import com.pl.spring.connection.Connect;
import com.pl.spring.model.User;
import com.pl.spring.view.generaWindow.GeneralWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.ObjectWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.databaseItemsInTree.roleItems.RoleLabel;
import com.pl.spring.view.generaWindow.objectWindowItems.SqlWindow;
import com.pl.spring.view.generaWindow.objectWindowItems.StatisticWindow;
import com.pl.spring.view.loginWindow.LoginWindow;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"com.pl.spring",
        "com.pl.spring.view.generaWindow.objectWindowItems"})
@PropertySource("classpath:dataConfig.property")
public class JavaController {

    @Autowired
    Environment environment;


    @Bean
    public BasicDataSource basicDataSource(){
        return new BasicDataSource();
    }


    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = basicDataSource();
        dataSource.setUrl(environment.getProperty("Url"));
        dataSource.setDriverClassName(environment.getProperty("ClassName"));
        dataSource.setUsername(environment.getProperty("DatabaseUser"));
        dataSource.setPassword(environment.getProperty("DatabasePassword"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public Connect connection(){
        return new Connect();
    }


    @Bean
    public LoginWindow getLoginWindow(){
        return new LoginWindow();
    }

    @Bean
    public User getUser(){
        return new User();
    }



//!!!!!!!!!!!!!!!!!!!GENERAL WINDOW!!!!!!!!!!!!!!!!!!!!!!!!!

    @Bean
    public GeneralWindow getGeneralWindow(){
        return  new GeneralWindow();
    }

    @Bean
    public ObjectWindow getObjectWindow(){ return new ObjectWindow();}

    @Bean
    public StatisticWindow getStatisticWindow(){
        return new StatisticWindow();
    }

    @Bean
    public SqlWindow getSqlWindow(){
        return new SqlWindow();
    }

    @Bean
    public RoleLabel getRoleLabel(){return  new RoleLabel();}

}
