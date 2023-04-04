package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * a representation for the edge
 */
@Data
@AllArgsConstructor
public class Edge implements Serializable {
    public int x;
    public int y;
}
