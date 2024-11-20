package com.example.bis.simulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVertexId implements Serializable {
    private String linkId;
    private Integer sqno;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkVertexId)) return false;
        LinkVertexId that = (LinkVertexId) o;
        return Objects.equals(linkId, that.linkId) && Objects.equals(sqno, that.sqno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkId, sqno);
    }
}
