package com.naresh.shoppingcart;

import com.naresh.shoppingcart.dao.UserRepository;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@Bean
	public ServletWebServerFactory servletContainer(){
		// Enable SSL
		TomcatServletWebServerFactory tomact = new TomcatServletWebServerFactory(){
			@Override
			protected void postProcessContext(Context context){
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};
		// Redirect Https for Http
		tomact.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
		return tomact;
	}

	/**
	 * Redirect http Requests back to https@port 8443
	 * @return
	 */
	private Connector httpToHttpsRedirectConnector(){
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;

	}

}
