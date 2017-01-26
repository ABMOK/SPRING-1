package pl.com.mnemonic.ems.service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.com.mnemonic.ems.configuration.SqlConfiguration;

@Configuration
@Import({SqlConfiguration.class})
public class ServiceContextConfiguration {

}
