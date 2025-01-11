package PYWMiniProject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class SpingBootConfig {

    // JSON View 객체 생성
    @Bean(value = "jsonView")
    public MappingJackson2JsonView jsonView() {
        return new MappingJackson2JsonView();
    }
    
    // Spring Security 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable() // CSRF 비활성화
            .authorizeHttpRequests()
                .anyRequest().permitAll() // 모든 요청 허용
            .and()
            .formLogin().disable(); // 기본 로그인 화면 비활성화
        return httpSecurity.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
