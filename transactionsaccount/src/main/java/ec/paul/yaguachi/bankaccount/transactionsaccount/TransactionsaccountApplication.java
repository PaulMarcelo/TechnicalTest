package ec.paul.yaguachi.bankaccount.transactionsaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "ec.paul.yaguachi.bankaccount.transactionsaccount.model")
public class TransactionsaccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionsaccountApplication.class, args);
	}

}
