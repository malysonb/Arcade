package br.dev.malyson.arcade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class WebFilterChainConfig{

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(auth -> 
                auth.requestMatchers("/login").permitAll()
                .requestMatchers("/registrar/**").permitAll()
                .requestMatchers("/static/**").permitAll()
                .requestMatchers("/player").permitAll()
                .requestMatchers(HttpMethod.POST, "/registrar").permitAll()
                .requestMatchers(HttpMethod.POST, "/player").permitAll()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin.loginPage("/")
                .usernameParameter("username") 
                .passwordParameter("password")
                .loginProcessingUrl("/loginProcess")
                .defaultSuccessUrl("/success", true).permitAll())
            .httpBasic(basic -> basic.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .exceptionHandling(Customizer.withDefaults());
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**", "/css/**", "/public/**", "/resources/**", "/javascript/**", "/js/**", "/style/**",
                "/templates/fragments/**", "/images/**");
    }



   /*@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/

    /**
     * @param http http
     * @throws Exception Exception
     */
    /*@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                // Não cheque essas requisições
                .authorizeRequests()
                .antMatchers("/",
                        "/login",
                        "/signup",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/**",
                        "/swagger-ui.html",
                        "/swagger-ui.html/**",
                        "/swagger-ui/**",
                        "/v3/**",
                        "/webjars/**",
                        "/pagina/**")
                .permitAll()
                .antMatchers(HttpMethod.POST).permitAll()
                // Qualquer outra requisição deve ser checada
                .anyRequest()
                .authenticated()
                .and().cors()
                .and().csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }*/

    /*@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }*/


}
