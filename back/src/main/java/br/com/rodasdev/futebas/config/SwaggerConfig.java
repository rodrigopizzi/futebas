package br.com.rodasdev.futebas.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket api() { 
		final List<ResponseMessage> globalResponses = Arrays.asList(
				new ResponseMessageBuilder().code(200).message("OK").build());
		
        return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("br.com.rodasdev.futebas.restcontroller"))              
			.paths(PathSelectors.any())
			.build()
			.globalResponseMessage(RequestMethod.POST, globalResponses)
			.globalResponseMessage(RequestMethod.PUT, globalResponses)
			.globalResponseMessage(RequestMethod.GET, globalResponses)
			.globalResponseMessage(RequestMethod.DELETE, globalResponses)
			.securitySchemes(Arrays.asList(basicAuth()))
			.securityContexts(Arrays.asList(securityContext()))
			.apiInfo(apiInfo());
    }


	private BasicAuth basicAuth() {
		return new BasicAuth("Basic Auth");
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder()
		        .securityReferences(defaultAuth())
		        .forPaths(PathSelectors.regex("/.*"))
		        .build();
	}
	
	List<SecurityReference> defaultAuth() {
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = authorizationScope;
	    return Arrays.asList(new SecurityReference("Basic Auth", authorizationScopes));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
			       "Futebas API", 
			       "Gerenciador de Partidas de Futebol", 
			       "1.0.0", 
			       "Terms of service", 
			       new Contact("Rodrigo Pizzi Argentato", null, "rodrigopizzi@gmail.com"), 
			       "License of API", "API license URL", Collections.emptyList());
	}
	
	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder
				.builder()
				.deepLinking(true)
				.displayOperationId(false)
				.defaultModelsExpandDepth(1)
				.defaultModelExpandDepth(1)
				.defaultModelRendering(ModelRendering.EXAMPLE)
				.displayRequestDuration(false)
				.docExpansion(DocExpansion.NONE)
				.filter(false)
				.maxDisplayedTags(null)
				.operationsSorter(OperationsSorter.ALPHA)
				.showExtensions(false)
				.tagsSorter(TagsSorter.ALPHA)
				.supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
				.validatorUrl(null)
				.build();
	}
}
