/**
 * 
 */
package org.logesco.validators;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

/**
 * @author cedrickiadjeu
 *
 */
@Configuration
public class ValidationConfig {
	@Bean
	public SpringValidatorAdapter validator(){
		return new LocalValidatorFactoryBean();
	}
}
