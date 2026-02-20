package com.intellimob.auto_deployment_testing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDTO {
    private String name;
    private Double price;
}
