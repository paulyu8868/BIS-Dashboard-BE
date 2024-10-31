package com.example.bis.simulator.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "ROUTE")
@Data
public class ROUTE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "number", nullable = false)
    private Integer number;
}
