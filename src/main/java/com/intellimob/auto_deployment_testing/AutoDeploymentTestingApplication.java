package com.intellimob.auto_deployment_testing;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AutoDeploymentTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoDeploymentTestingApplication.class, args);
	}

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        // Configure to skip null values during mapping
        modelMapper.getConfiguration()
                .setPropertyCondition(mappingContext -> mappingContext.getSource() != null);
        return  modelMapper;
    }
}
