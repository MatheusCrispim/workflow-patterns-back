package io.demo.jony.jony.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import io.demo.jony.jony.core.api.ApiVersions;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Swagger Configuration.
 *
 * @author Virtus
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    /**
     * Builder for Response Messages.
     */
    private static ResponseMessageBuilder messageBuilder = new ResponseMessageBuilder();

    /* ALL DEFAULT CODES */
    private static final ResponseMessage CODE_200 = message(200, "Success");
    private static final ResponseMessage CODE_201 = message(201, "Created");
    private static final ResponseMessage CODE_401 = message(401, "You are not authorized to view the resource");
    private static final ResponseMessage CODE_403 = message(403, "Accessing the resource you were trying to reach is forbidden");
    private static final ResponseMessage CODE_404 = message(404, "The resource you were trying to reach is not found");
    private static final ResponseMessage CODE_406 = message(406, "The client error response code indicates that a response matching the list of acceptable values defined in Accept-Charset and Accept-Language cannot be served.");

    /**
     * Defines the API configuration for Swagger use.
     *
     * @return Docket.
     */
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.demo.jony.jony.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

        addResponseMessages(docket);
        addTags(docket);

        return docket;
    }

    /**
     * Adds response messages to all request methods.
     *
     * @param docket Docket.
     */
    private void addResponseMessages(Docket docket) {
        /* GET */
        addGlobalMessages(docket, RequestMethod.GET,
                CODE_200, CODE_401, CODE_403, CODE_404, CODE_406);

        /* POST */
        addGlobalMessages(docket, RequestMethod.POST,
                CODE_200, CODE_201, CODE_401, CODE_403, CODE_404, CODE_406);

        /* PUT */
        addGlobalMessages(docket, RequestMethod.PUT,
                CODE_200, CODE_401, CODE_403, CODE_404, CODE_406);

        /* PATCH */
        addGlobalMessages(docket, RequestMethod.PATCH,
                CODE_200, CODE_401, CODE_403, CODE_404, CODE_406);

        /* DELETE */
        addGlobalMessages(docket, RequestMethod.DELETE,
                CODE_200, CODE_201, CODE_401, CODE_403, CODE_404, CODE_406);

    }

    /**
     * Adds all tags.
     *
     * @param docket Docket.
     */
    private void addTags(Docket docket) {
        docket.tags(
                tag("user-controller", "Endpoint for operations in Users")
        );
    }

    /**
     * Adds all messages as global message to that request method.
     *
     * @param docket   Docket.
     * @param method   Request Method.
     * @param messages Messages.
     */
    private void addGlobalMessages(Docket docket, RequestMethod method, ResponseMessage... messages) {
        docket.globalResponseMessage(method, Arrays.asList(messages));
    }

    /**
     * Creates a response message.
     *
     * @param code    Code.
     * @param message Message.
     * @return Response Message.
     */
    private static ResponseMessage message(Integer code, String message) {
        return messageBuilder.code(code).message(message).build();
    }

    /**
     * Creates a tag.
     *
     * @param name Name.
     * @param desc Description.
     * @return Tag.
     */
    private Tag tag(String name, String desc) {
        return new Tag(name, desc);
    }

    /**
     * Creates the API Info for Swagger.
     *
     * @return API Info.
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Demo API", "REST API for Demo.", ApiVersions.LASTEST.getVersion(),
                "", new Contact("Demo", "http://demo.virtus.ufcg.edu.br/web", ""), "", "", new ArrayList<>());
    }

}