package com.bank.config;

import com.bank.security.JwtTokenFilterConfigurer;
import com.bank.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
// endpoint bazlı authorazition sağlamak istersek bu anatasyonu kullanmamız gerekir
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    // Bu metotda nereye kim erişecek onu belirleriz
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        //csrf tokenları yok eder
        // CSRF (Cross Site Request Forgery) genel yapı olarak sitenin açığından faydalanarak siteye sanki o kullanıcıymış gibi erişerek işlem yapmasını sağlar
        http.csrf().disable();
        //HTML formu ile login olmayı disable eder
        http.formLogin().disable();
        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()   // BU aşağıdaki iki adrese girerken authentication isteme diyoruz
                .antMatchers("/users/signin").permitAll()
                .antMatchers("/users/signup").permitAll()
//                .antMatchers("/v2/api-docs").permitAll()
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/swagger-ui/index.html").permitAll()
                // Authorize any endpoint by a role

                //      .antMatchers("/users/delete/**").hasRole("ROLE_ADMIN") //  bu kod ile sadece ADMIN kullanıcılara izin veriyoruz,** kalan bütün pathler için anlamına gelir
                // Disallow everything else..
                // .antMatchers("/users/delete/**").hasRole(Role.ROLE_ADMIN.name())
                .anyRequest().authenticated();  // Yukarıdaki endpointler dışındaki bütün istekleri authentice et

        // Apply JWT
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
//                swagger2 base urls
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
//                swagger3 base urls
                "/swagger-ui/**",
                "/javainuse-openapi/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}