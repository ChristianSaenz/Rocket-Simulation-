public class LaunchSystem {
    private Rocket rocket;

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void launch() {
        if (rocket != null) {
            rocket.ignite();
        }
    }

    public void abort() {
        if (rocket != null) {
            rocket.shutdown();
            rocket.setThrust(0);
            rocket.setVelocity(0);
            rocket.setAltitude(0);
        }
    }
}


