package com.semicolon.grincultified.configurations;

import com.semicolon.grincultified.utilities.JwtUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
public class BeanConfiguration {
    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private int mailPort;
    @Value("${spring.mail.protocol}")
    private String mailProtocol;
    @Value("${spring.mail.password}")
    private String mailPassword;
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("jwt.signing.secret")
    private String secret;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailHost);
        javaMailSender.setPort(mailPort);
        javaMailSender.setProtocol(mailProtocol);
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.ssl.enable", "true");
        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.setPassword(mailPassword);
        javaMailSender.setUsername(mailUsername);
        return javaMailSender;
    }

    @Bean
    public JwtUtility jwtUtility(){
        return new JwtUtility(secret);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
    }

}
