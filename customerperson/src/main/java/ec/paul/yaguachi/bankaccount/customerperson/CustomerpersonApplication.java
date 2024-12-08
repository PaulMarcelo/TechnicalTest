package ec.paul.yaguachi.bankaccount.customerperson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "ec.paul.yaguachi.bankaccount.customerperson.model")
public class CustomerpersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerpersonApplication.class, args);
	}

}
