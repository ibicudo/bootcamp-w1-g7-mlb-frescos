package com.mercadolibre.ingrid_bicudo;

import com.mercadolibre.ingrid_bicudo.config.SpringConfig;
import com.mercadolibre.ingrid_bicudo.util.ScopeUtils;
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
 