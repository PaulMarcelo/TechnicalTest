package ec.paul.yaguachi.bankaccount.transactionsaccount.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
/**
 * Class RestTemplateConfig
 *
 * @author Paul Yaguachi
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    @Qualifier("restTemplateCustomer")
    public RestTemplate restTemplateCustomer() {
        return new RestTemplate();
    }
}
