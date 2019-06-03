package br.com.ongvd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.ongvd.service.OngService;
import br.com.ongvd.service.OngServiceImpl;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/painel/**")
					.authenticated()
				.antMatchers(
						"/",
						"/css/**",
						"/js/**",
						"/favicon/**",
						"/fonts/**",
						"/img/**",
						"/home",
						"/doador/**",
						"/voluntario/**",
						"/ong/**",
						"/h2-console/**")
					.permitAll()
						.anyRequest()
							.authenticated()
				.and()
					.formLogin()
						.loginPage("/login")
							.defaultSuccessUrl("/painel/ong/configuracoes", true)
								.permitAll()
				.and()
					.logout()
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout")
				.permitAll();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Bean
	public OngService ongService() {
		return new OngServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(ongService());
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
}