package com.clases.security.usuarios.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration       // anotacion de esta clase como de configuracion
@EnableWebSecurity   // activo la seguridad web
public class SecurityConfig extends WebSecurityConfigurerAdapter {//se establece el adaptador para la configuracion

    //se configuran la autenticacion de los usuarios
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //verificacion de usuarios en memoria
        //se guarda en memoria a futuro se consultaran los datos desde una base de datos
        //se establece el rol a futuro se debe obtener el rol del usuario desde base de datos
        auth.inMemoryAuthentication()
                .withUser("jorge").password(passwordEncoder().encode("jorge")).roles("ADMIN")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER");
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



                .anyRequest()                   //todas las peticiones deben cumplir el siguiente criterio
                .authenticated()                //que esten autenticadas
                .and()                          // y ademas debe mostrar lo siguiente
                .formLogin();                   //formulario de login
        //automaticamente si el ingreso es correcto nos llevara a la pagina / que es la inicial, que esta en UserController->homePage
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
