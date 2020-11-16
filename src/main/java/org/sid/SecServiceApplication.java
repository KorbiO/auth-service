package org.sid;

import org.sid.dao.AppRoleRepository;
import org.sid.entities.AppRole;
import org.sid.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SecServiceApplication {

    public static void main(String[] args)  {
        SpringApplication.run(SecServiceApplication.class, args);
        
    }
    
    @Bean
    CommandLineRunner start(AccountService accountService , AppRoleRepository roleRepo){
        return  args->{
        	AppRole r1 =roleRepo.save(new AppRole( null ,"FOURNISSEUR"));
            accountService.save(new AppRole(null,"USER"));
            accountService.save(new AppRole(null,"ADMIN"));
            Stream.of("omar@korbi.net","user1","user2","user3","admin@korbi.net").forEach(un->{
                accountService.saveUser(un,"1234","1234","2121212" ,"Male" ,"monjislim", "125255" );
            });
            accountService.addRoleToUser("admin@korbi.net","ADMIN");
        };
    } 
    
    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }

}

