package devlight.edu.conference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "devlight.edu.conference")
public class StudentConferenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentConferenceApplication.class, args);
	}
}
