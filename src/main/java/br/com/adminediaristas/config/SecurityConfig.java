package br.com.adminediaristas.config;

import br.com.adminediaristas.core.enums.TipoUsuarioEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Value("${br.com.treinaweb.ediaristas.rememberme.key}")
    private String rememberMeKey;

    @Value("${br.com.treinaweb.ediaristas.rememberme.validitySeconds}")
    private int rememberMeValiditySeconds;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("admin/**").hasAuthority(TipoUsuarioEnum.ADMIN.toString())
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/admin/login")
                .usernameParameter("email")
                .passwordParameter("senha")
                .defaultSuccessUrl("/admin/servicos")
                .permitAll();

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout", "GET"))
                .logoutSuccessUrl("/admin/login");

        http.rememberMe()
                .tokenValiditySeconds(rememberMeValiditySeconds)
                .key(rememberMeKey)
                .rememberMeParameter("lembrar-me");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/webjars/**")
                .antMatchers("/img/**");
    }
}
