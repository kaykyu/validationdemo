package kq.practice.validationdemo;

import java.io.File;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ValidationdemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ValidationdemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (args.containsOption("dataDir")) {
			final String dataDir = args.getOptionValues("dataDir").get(0);

			File fileDir = new File(dataDir);

			if (!fileDir.exists()) {
				fileDir.mkdir();
				System.out.printf("Directory created: %s\n", fileDir.getAbsolutePath());
			} else {
				System.out.println(fileDir.getAbsolutePath());
			}
		}
	}

}
