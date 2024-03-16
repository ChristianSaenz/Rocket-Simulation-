public abstract class Rocket implements Movable {
    protected String name;
    protected double altitude;
    protected double velocity;
    protected double acceleration;
    protected double thrust;
    protected double fuel;

    public Rocket(String name, double initialFuel) {
        this.name = name;
        this.altitude = 0;
        this.velocity = 0;
        this.acceleration = 0;
        this.fuel = initialFuel;
        this.thrust = 0;
    }

    public abstract void ignite();
    public abstract void shutdown();

    public double getAltitude() {
        return altitude;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getThrust() {
        return thrust;
    }

    public double getFuel() {
        return fuel;
    }

    public void setThrust(double thrust) {
        this.thrust = Math.max(thrust, 0); // Ensure thrust does not go below zero
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    protected abstract double getMass();

    protected void consumeFuel(double deltaTime) {
        if (fuel > 0 && thrust > 0) {
            double fuelConsumptionRate = thrust / 1000; // Example fuel consumption logic
            double consumedFuel = fuelConsumptionRate * deltaTime;
            fuel -= consumedFuel;
            if (fuel < 0) {
                fuel = 0;
                thrust = 0; // No fuel, no thrust
            }
        }
    }

    @Override
    public void updatePosition(double deltaTime) {
        consumeFuel(deltaTime);

        if (thrust > 0) {
            acceleration = thrust / getMass();
        } else if (altitude > 0) {
            acceleration = -9.81; // Simulate free fall
        } else {
            acceleration = 0; // The rocket has landed
        }

        velocity += acceleration * deltaTime;
        altitude += velocity * deltaTime;
        if (altitude < 0) {
            altitude = 0;
            velocity = 0; // Simulate landing without bouncing
        }
    }
}

