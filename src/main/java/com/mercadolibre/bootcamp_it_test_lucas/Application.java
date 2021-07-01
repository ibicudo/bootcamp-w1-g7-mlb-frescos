package com.mercadolibre.bootcamp_it_test_lucas;

import com.mercadolibre.bootcamp_it_test_lucas.config.SpringConfig;
import com.mercadolibre.bootcamp_it_test_lucas.util.ScopeUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		ScopeUtils.calculateScopeSuffix();
		new SpringApplicationBuilder(SpringConfig.class).registerShutdownHook(true)
				.run(args);
	}
}
