package ua.softserve.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth-> {
                    auth.mvcMatchers("/").permitAll();
//                    auth.mvcMatchers("/reader/*").hasRole("READER");
//                    auth.mvcMatchers("/test1").hasRole("ADMIN");
                })
                .httpBasic(Customizer.withDefaults())
                .build();

//         return http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/user").hasRole("READER")
//                .antMatchers("/admin").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .and().build();
    }
}
