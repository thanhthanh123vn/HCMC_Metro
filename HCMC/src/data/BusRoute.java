package data;

import java.util.ArrayList;
import java.util.List;


class BusRoute {
    private String routeNumber; 
    private List<BusStation> stops;

    public BusRoute(String number) {
        this.routeNumber = number;
        this.stops = new ArrayList<>();
    }

    public void addStop(BusStation stop) { stops.add(stop); }
    public String getRouteNumber() { return routeNumber; }
    public List<BusStation> getStops() { return stops; }
}