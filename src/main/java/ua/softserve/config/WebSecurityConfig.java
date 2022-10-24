package ua.softserve.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        /*return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth-> {
                    auth.mvcMatchers("/").permitAll();
//                    auth.mvcMatchers("/reader/*").hasRole("READER");
//                    auth.mvcMatchers("/test1").hasRole("ADMIN");
                })
                .httpBasic(Customizer.withDefaults())
                .build();*/

         return http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                 .authenticationProvider(authenticationProvider())
                .httpBasic()
                .and()
                 .formLogin()
                 .loginPage("/login")
                 .defaultSuccessUrl("/book")
                 .failureUrl("/login?error")
                 .permitAll()
                 .and()
                 .logout()
                 .permitAll().and().build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(getEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
