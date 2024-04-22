package com.example.usmobile;

import com.example.usmobile.domain.Cycle;
import com.example.usmobile.domain.DailyUsage;
import com.example.usmobile.domain.User;
import com.example.usmobile.exception.EntityAlreadyExists;
import com.example.usmobile.repository.CycleRepository;
import com.example.usmobile.repository.DailyUsageRepository;
import com.example.usmobile.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDateTime;

@SpringBootApplication
public class UsMobileApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsMobileApplication.class, args);
    }

    //Swagger Documentation
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.usmobile.controller"))
                .build();
    }

    // Adding Initial Data to the DB
    @Bean
    CommandLineRunner runner(UserRepository userRepository, CycleRepository cycleRepository, DailyUsageRepository dailyUsageRepository){
        return args -> {
            // adding Users
            User user1 = new User("cherif", "Kadri", "cherif.kadri1@usmobile.com", "password", "12347562340");
            User user2 = new User("cherif2", "Kadri", "cherif.kadri2@usmobile.com", "password", "123423490");
            User user3 = new User("cherif3", "Kadri", "cherif.kadri3@usmobile.com", "password", "1356567890");
            User user4 = new User("cherif4", "Kadri", "cherif.kadri4@usmobile.com", "password", "127577890");

            if(!userRepository.findUserByEmail(user1.getEmail()).isPresent() && !userRepository.findUserByMdn(user1.getMdn()).isPresent() ){userRepository.save(user1);}



            if(!userRepository.findUserByEmail(user2.getEmail()).isPresent() && !userRepository.findUserByMdn(user2.getMdn()).isPresent() ){userRepository.save(user2);}


            if(!userRepository.findUserByEmail(user3.getEmail()).isPresent() && !userRepository.findUserByMdn(user3.getMdn()).isPresent() ){userRepository.save(user3);}



            if(!userRepository.findUserByEmail(user4.getEmail()).isPresent() && !userRepository.findUserByMdn(user4.getMdn()).isPresent() ){userRepository.save(user4);}








            //adding cycles
            Cycle cycle1 = new Cycle("12347562340", LocalDateTime.now().minusDays(120),LocalDateTime.now().minusDays(90),userRepository.findUserByEmail("cherif.kadri1@usmobile.com").get().getId());
            Cycle cycle2 = new Cycle("12347562340", LocalDateTime.now().minusDays(90),LocalDateTime.now().minusDays(60),userRepository.findUserByEmail("cherif.kadri1@usmobile.com").get().getId());
            Cycle cycle3 = new Cycle("12347562340", LocalDateTime.now().minusDays(15),LocalDateTime.now().plusDays(15),userRepository.findUserByEmail("cherif.kadri1@usmobile.com").get().getId());
            Cycle cycle4 = new Cycle("123423490", LocalDateTime.now().minusDays(120),LocalDateTime.now().minusDays(90),userRepository.findUserByEmail("cherif.kadri1@usmobile.com").get().getId());


            cycleRepository.save(cycle1);
            cycleRepository.save(cycle2);
            cycleRepository.save(cycle3);
            cycleRepository.save(cycle4);

            //adding dailyUsage

            DailyUsage dailyUsage1 = new DailyUsage("12347562340",userRepository.findUserByEmail("cherif.kadri1@usmobile.com").get().getId(),LocalDateTime.now().minusDays(3),500);
            DailyUsage dailyUsage2 = new DailyUsage("12347562340",userRepository.findUserByEmail("cherif.kadri1@usmobile.com").get().getId(),LocalDateTime.now().minusDays(2),590);
            DailyUsage dailyUsage3 = new DailyUsage("12347562340",userRepository.findUserByEmail("cherif.kadri1@usmobile.com").get().getId(),LocalDateTime.now().minusDays(1),434);
            DailyUsage dailyUsage4 = new DailyUsage("1356567890",userRepository.findUserByEmail("cherif.kadri3@usmobile.com").get().getId(),LocalDateTime.now().minusDays(3),700);

            dailyUsageRepository.save(dailyUsage1);
            dailyUsageRepository.save(dailyUsage2);
            dailyUsageRepository.save(dailyUsage3);
            dailyUsageRepository.save(dailyUsage4);


        };
    }
}
