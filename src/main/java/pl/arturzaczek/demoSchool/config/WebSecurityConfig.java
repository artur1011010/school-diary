package pl.arturzaczek.demoSchool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private DataSource dataSource;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder, DataSource dataSource){
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
    }

    //todo
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().permitAll()
//                .and()
//                .csrf().disable()
//                .headers().frameOptions().disable();
//    }
//    2.0
//     http.authorizeRequests()
//             .antMatchers("/post/add").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/post/*/comment/add").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/post/comment/add").hasAnyRole("USER", "ADMIN")
//                .anyRequest().permitAll()
//            .and()
//    //h2 database requires for proper operation
//                .csrf().disable()
//                .headers().frameOptions().disable()
//            .and()
//                .formLogin()
//                .loginPage("/user/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .failureUrl("/user/login?status=error")
//                .loginProcessingUrl("/login-post-by-spring")
//                .defaultSuccessUrl("/");

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
/**
 *       Roles:
 *       -"USER",
 *       -"ADMIN",
 *       -"PRINCIPAL",
 *       -"PRINCIPAL",
 *       -"PARENT"
 *       -"STUDENT",
 */
                .antMatchers("/test1").hasAnyRole("USER", "ADMIN","PRINCIPAL", "PRINCIPAL","PARENT", "STUDENT")
                .antMatchers("/studentProfile/").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll()
                .and()
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/user/login-forms")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/user/login-forms?status=errors")
                .loginProcessingUrl("/home")
                .defaultSuccessUrl("/home")
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT u.email, u.password_hash, 1 FROM user u WHERE u.email = ? ")
                .authoritiesByUsernameQuery("SELECT u.email, r.role_name " +
                        "FROM USER u " +
                        "JOIN user_role ur ON u.id = ur.user_id " +
                        "JOIN role r ON ur.ROLE_SET_ID = r.id " +
                        "WHERE u.email = ?")
                .passwordEncoder(passwordEncoder);
    }
}
