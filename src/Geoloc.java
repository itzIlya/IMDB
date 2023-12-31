public class Geoloc {
    GeoType geoType;
    Geoloc parent;
    String data;

    public Geoloc(GeoType geoType, Geoloc parent, String data) {
        this.geoType = geoType;
        this.parent = parent;
        this.data = data;
    }
}
