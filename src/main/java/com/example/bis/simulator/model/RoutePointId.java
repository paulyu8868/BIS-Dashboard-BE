package com.example.bis.simulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutePointId implements Serializable {
    private String routeId;
    private Integer pointSqno;
}