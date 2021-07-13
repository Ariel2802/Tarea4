package modelo;

public class Facultad {

    String facultad, decano, imagen;
    double lat, lng;

    public Facultad() {
    }

    public Facultad(String facultad, String decano, String imagen, double lat, double lng) {
        this.facultad = facultad;
        this.decano = decano;
        this.imagen = imagen;
        this.lat = lat;
        this.lng = lng;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getDecano() {
        return decano;
    }

    public void setDecano(String decano) {
        this.decano = decano;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"Decano\":\"" + decano + "\",\n" +
                "\t\"Imagen\":\"" + imagen + "\"\n" +
                "}";
    }
}
