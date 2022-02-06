package TTL.models;

import lombok.Data;

import java.util.List;

@Data
public class Route<T extends Node> {

    private double routeScore;

    private double epsilon;

    private List<T> route;
}
