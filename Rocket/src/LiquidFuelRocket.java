public class LiquidFuelRocket extends Rocket {
    private double mass;

    public LiquidFuelRocket(String name, double initialFuel, double mass) {
        super(name, initialFuel);
        this.mass = mass;
    }

    @Override
    public void ignite() {
        thrust = 15000; // Example thrust value
    }

    @Override
    public void shutdown() {
        thrust = 0;
    }

    @Override
    protected double getMass() {
        return mass;
    }
}
