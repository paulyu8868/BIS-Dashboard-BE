package com.example.bis.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LinkData {
    private String linkId;
    private List<LinkVertexData> vertices;
}
