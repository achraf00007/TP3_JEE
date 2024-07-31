package ma.enset.tp3springapp;
import com.github.javafaker.Faker;

import ma.enset.tp3springapp.entities.Patient;
import ma.enset.tp3springapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Date;

@SpringBootApplication
public class Tp3SpringAppApplication implements CommandLineRunner {

    @Autowired
    PatientRepository patientRepository ;


    private final Faker faker = new Faker();

    public static void main(String[] args) {
        SpringApplication.run(Tp3SpringAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

     /*   Patient patient1 = new Patient(null, "Mohammed", new Date(90, 4, 15), true, 80);
        Patient patient2 = new Patient(null, "Fatima", new Date(85, 7, 20), false, 65);
        Patient patient3 = new Patient(null, "Ali", new Date(78, 11, 10), true, 90);
        Patient patient4 = new Patient(null, "Noor", new Date(100, 2, 25), false, 75);

        // Save patients to the database
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);
        patientRepository.save(patient4);*/

        for (int i = 0; i < 50; i++) {
            Patient patient = createPatient();
            patientRepository.save(patient);
        }
    }

    private Patient createPatient() {
        String name = faker.name().fullName();
        Date birthDate = generateRandomBirthDate();
        boolean isIll = faker.bool().bool();
        int score = faker.number().numberBetween(100, 500);
        return new Patient(null, name, birthDate, isIll, score);
    }

    private Date generateRandomBirthDate() {
        // Generate a random birth date between 1950 and 2005
        int year = faker.number().numberBetween(1950, 2006);
        int month = faker.number().numberBetween(1, 13);
        int day = faker.number().numberBetween(1, 29); // Assuming 28 days in each month
        return new Date(year - 1900, month - 1, day);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

