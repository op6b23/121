package com.ssd.petMate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ssd.petMate.service.UserFacade;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserFacade userFacade;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        		.antMatchers("/**").hasRole("USER");
//                .antMatchers("/mypage/**").hasRole("USER")
//                .antMatchers("/inquiry/**").hasRole("ADMIN")
//                .antMatchers("/info/**").authenticated() // 차후 수정 필요
//        		.antMatchers("/index/**").permitAll(); 
        http.csrf().disable();
    	

		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and();

        http.formLogin()
                .loginPage("/singIn")
                .loginProcessingUrl("/signIn")
//                .failureUrl("/signIn-error")
                .defaultSuccessUrl("/index", true)
                .usernameParameter("userID")
                .passwordParameter("pwd");

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/signOut"))
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true);
    }
    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	     auth.inMemoryAuthentication()
//	     .passwordEncoder(passwordEncoder())
//	     .withUser("ssd0202")
//	     .password(passwordEncoder().encode("kong0527"))
//	     .roles("USER");
//    }
//

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userFacade).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 
}