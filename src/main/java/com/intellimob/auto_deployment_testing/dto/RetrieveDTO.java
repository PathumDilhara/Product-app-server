package com.intellimob.auto_deployment_testing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveDTO {
    private Long id;
    private String name;
    private Double price;
}
