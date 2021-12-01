package com.clases.security.usuarios.config;

import com.clases.security.usuarios.domain.security.AuthSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration       // anotacion de esta clase como de configuracion
@EnableWebSecurity   // activo la seguridad web
public class SecurityConfig  extends WebSecurityConfigurerAdapter {//se establece el adaptador para la configuracion

    @Autowired
    private AuthSecurityManager authSecurityManager;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    //se configuran la autenticacion de los usuarios
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( authSecurityManager  ).passwordEncoder(passwordEncoder());
    }

    //configura la seguridad, rutas y lo que esta permitido, y cuales seran las paginas autorizadas
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests()//autoriza las peticiones
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                .antMatchers("/resources/static/assets/**")
                .permitAll()

                .antMatchers("/login*")//hace que se mueva a este prefijo de login, spring security crea esta pagina
                .permitAll()//permite el acceso a todos a la pagina de anterior (login)
                .antMatchers("/no-protegida*")
                .permitAll()
                .antMatchers("/no-protegida-correo*")
                .permitAll()
                .antMatchers("/sobrenosotros-desprotegida*")
                .permitAll()
                .antMatchers("/listapelis-desprotegida*")
                .permitAll()
                .antMatchers("/registro*")
                .permitAll()
                .antMatchers("/correosinproteger*")
                .permitAll()
                .antMatchers("/users/new*")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/movies/{id}/view").hasAnyRole("ADMIN")
                .anyRequest()                   //todas las peticiones deben cumplir el siguiente criterio
                .authenticated()                //que esten autenticadas
                .and()                          // y ademas debe mostrar lo siguiente
                .exceptionHandling().accessDeniedPage("/prohibido")
                .and()
                .formLogin();                   //formulario de login
        //automaticamente si el ingreso es correcto nos llevara a la pagina / que es la inicial, que esta en UserController->homePage
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
