package sg.edu.rp.c346.id21008914.demoweather;

public class Carpark {

    private String carpark_number;
    private int lots_available;

    public Carpark(String carpark_number, int lots_available) {
        this.carpark_number = carpark_number;
        this.lots_available = lots_available;
    }

    public String getCarpark_number() {
        return carpark_number;
    }

    public void setCarpark_number(String carpark_number) {
        this.carpark_number = carpark_number;
    }

    public int getLots_available() {
        return lots_available;
    }

    public void setLots_available(int lots_available) {
        this.lots_available = lots_available;
    }

    @Override
    public String toString() {
        return "Carpark{" +
                "carpark_number='" + carpark_number + '\'' +
                ", lots_available=" + lots_available +
                '}';
    }
}