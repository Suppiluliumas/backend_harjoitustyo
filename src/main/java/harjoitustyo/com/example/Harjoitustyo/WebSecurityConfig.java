package harjoitustyo.com.example.Harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import java.io.IOException;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/css/**").permitAll() // Enable css when logged out
		.requestMatchers("/").permitAll().requestMatchers("/gamelist").permitAll()
		.requestMatchers("/delete/{id}").hasAuthority("ADMIN").requestMatchers("/editgame/{id}")
		.hasAuthority("ADMIN").requestMatchers(toH2Console()).permitAll().anyRequest().authenticated().and()
		.csrf().ignoringRequestMatchers(toH2Console()).and().headers().frameOptions().disable().and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/gamelist", true).permitAll().and().logout()
		.permitAll().and().httpBasic();
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	@Component
	public class CorsFilter extends OncePerRequestFilter {
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
				FilterChain filterChain) throws ServletException, IOException {
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
			response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
			if ("OPTIONS".equals(request.getMethod())) {
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				filterChain.doFilter(request, response);
			}
		}
	}

}