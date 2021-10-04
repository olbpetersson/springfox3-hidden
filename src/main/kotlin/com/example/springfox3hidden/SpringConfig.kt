package com.example.springfox3hidden

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.RequestParameterBuilder
import springfox.documentation.schema.ScalarType
import springfox.documentation.service.ParameterType
import springfox.documentation.service.RequestParameter
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SpringConfig {

    @Bean
    fun docket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(
                ApiInfoBuilder()
                    .title("Test")
                    .description("Testing hidden attribute")
                    .version("1")
                    .build()
            )
            .groupName("Hidden test")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.springfox3hidden"))
            .paths(PathSelectors.any())
            .build()
            .globalRequestParameters(listOf(customHeader()))
    }

    private fun customHeader(): RequestParameter {
        return RequestParameterBuilder()
            .name("My-Custom-Header")
            .description("A header with a default value which shouldn't be displayed")
            .hidden(true)
            .query { simpleParameterSpecificationBuilder ->
                simpleParameterSpecificationBuilder
                    .model { modelSpecificationBuilder -> modelSpecificationBuilder.scalarModel(ScalarType.STRING) }
                    .defaultValue("myCustomValue")
            }
            .`in`(ParameterType.HEADER)
            .required(false)
            .build()
    }
}
