package pl.com.mnemonic.ems.classified.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pl.com.mnemonic.ems.configuration.SqlConfiguration;


@Configuration
@Import({SqlConfiguration.class})
public class ClassyContextConfiguration {

}
