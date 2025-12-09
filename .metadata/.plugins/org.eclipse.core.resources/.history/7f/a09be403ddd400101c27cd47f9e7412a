package data;

import java.util.LinkedList;
import java.util.Set;
import java.util.List;

// Lớp Nhà ga
class Station {
    private String id;
    private String name;

    public Station(String id, String name) {
        this.id = id;
        this.name = name;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}

// Lớp Đoạn đường (RoutePart)
class RoutePart {
    private Station beginStation;
    private Station endStation;
    private double distanceKm;

    public RoutePart(Station begin, Station end, double distance) {
        this.beginStation = begin;
        this.endStation = end;
        this.distanceKm = distance;
    }
 

    
    public Station getBeginStation() {
        return this.beginStation;
    }

    public Station getEndStation() {
        return this.endStation;
    }
}

// Lớp Tuyến đường (Route)
class Route {
    private String id;
    private String name;
    // Trong ảnh dùng LinkedList
    private LinkedList<RoutePart> routePartList = new LinkedList<>();

    public Station getBeginStation() {
        return routePartList.getFirst().getBeginStation(); // Giả lập logic
    }

    public Station getEndStation() {
        return routePartList.getLast().getEndStation();
    }

    public void addRoutePart(RoutePart part) {
        this.routePartList.add(part);
    }
    
    public void removeRoutePart(RoutePart part) {
        this.routePartList.remove(part);
    }
}