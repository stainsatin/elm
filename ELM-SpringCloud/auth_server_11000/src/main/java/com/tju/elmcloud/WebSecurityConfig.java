package com.tju.elmcloud;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager =
                new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("ydt").password("123").authorities("admin").build());
        return inMemoryUserDetailsManager;
    }

    // 密码编码器：使用 BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 授权规则配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()                                // 授权配置
                .antMatchers("/login").permitAll()  // 登录路径放行
                .anyRequest().authenticated()                   // 其他路径都要认证之后才能访问
                .and().formLogin()                              // 允许表单登录
                .successForwardUrl("/loginSuccess")             // 设置登录成功页
                .and().logout().permitAll()                    // 登出路径放行
                .and().csrf().disable();                        // 关闭跨域伪造检查
    }
}
