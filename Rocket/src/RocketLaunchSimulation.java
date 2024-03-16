import javax.swing.*;
import java.awt.*;

public class RocketLaunchSimulation {
    private LaunchSystem launchSystem;
    private RocketPanel rocketPanel;
    private Timer timer;
    private JLabel altitudeLabel, velocityLabel, accelerationLabel, thrustLabel, fuelLabel;

    public RocketLaunchSimulation() {
        launchSystem = new LaunchSystem();
        rocketPanel = new RocketPanel();
        setupGUI();
    }

    private void setupGUI() {
        JFrame frame = new JFrame("Rocket Launch Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton setSolidRocketButton = new JButton("Set Solid Fuel Rocket");
        setSolidRocketButton.addActionListener(e -> setSolidFuelRocket());

        JButton setLiquidRocketButton = new JButton("Set Liquid Fuel Rocket");
        setLiquidRocketButton.addActionListener(e -> setLiquidFuelRocket());

        JButton launchButton = new JButton("Launch Rocket");
        launchButton.addActionListener(e -> launchRocket());

        JButton abortButton = new JButton("Abort Rocket");
        abortButton.addActionListener(e -> abortRocket());

        JPanel controlPanel = new JPanel();
        controlPanel.add(setSolidRocketButton);
        controlPanel.add(setLiquidRocketButton);
        controlPanel.add(launchButton);
        controlPanel.add(abortButton);

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(rocketPanel, BorderLayout.CENTER);

        frame.setSize(800, 600);
        frame.setVisible(true);
        
        JPanel statusPanel = new JPanel(new GridLayout(5, 1));
        altitudeLabel = new JLabel("Altitude: 0 m");
        velocityLabel = new JLabel("Velocity: 0 m/s");
        accelerationLabel = new JLabel("Acceleration: 0 m/s²");
        thrustLabel = new JLabel("Thrust: 0 N");
        fuelLabel = new JLabel("Fuel: 0 kg");

        statusPanel.add(altitudeLabel);
        statusPanel.add(velocityLabel);
        statusPanel.add(accelerationLabel);
        statusPanel.add(thrustLabel);
        statusPanel.add(fuelLabel);

        frame.add(statusPanel, BorderLayout.EAST);
        frame.add(rocketPanel, BorderLayout.CENTER);

        frame.setSize(800, 600);
        frame.setVisible(true);
        

    }
   
    private void simulateRocketFlight() {
        Rocket rocket = launchSystem.getRocket();
        if (rocket == null) return;

        rocket.updatePosition(1); // Update the position based on the current state
        rocketPanel.setRocketPosition(rocket.getAltitude());
        updateStatus(rocket);

        // Continue the simulation until the rocket has landed
        if (rocket.getAltitude() <= 0) {
            timer.stop();
            JOptionPane.showMessageDialog(null, "Rocket has landed.");
        }
    }


    

    private void setSolidFuelRocket() {
        Rocket solidRocket = new SolidFuelRocket("Solid Rocket", 1000, 500);
        launchSystem.setRocket(solidRocket);
    }

    private void setLiquidFuelRocket() {
        Rocket liquidRocket = new LiquidFuelRocket("Liquid Rocket", 1000, 500);
        launchSystem.setRocket(liquidRocket);
    }

    private void launchRocket() {
        if (launchSystem.getRocket() == null) return;

        launchSystem.launch();

        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(100, e -> simulateRocketFlight());
        timer.start();
    }

   
    private void abortRocket() {
        if (launchSystem.getRocket() != null) {
            launchSystem.abort();
            if (timer != null) {
                timer.stop();
            }
            resetSimulation();
            updateStatus(launchSystem.getRocket());
            rocketPanel.setRocketPosition(0); // Resets the visual position of the rocket
        }
    }

    private void resetSimulation() {
        // Resets the simulation to its initial state
        Rocket rocket = launchSystem.getRocket();
        if (rocket != null) {
            rocket.setVelocity(0);
            rocket.setAltitude(0);
            updateStatus(rocket);
        }
    }
    private void updateStatus(Rocket rocket) {
        // Update status labels to reflect the current state of the rocket
        altitudeLabel.setText(String.format("Altitude: %.2f m", rocket.getAltitude()));
        velocityLabel.setText(String.format("Velocity: %.2f m/s", rocket.getVelocity()));
        accelerationLabel.setText(String.format("Acceleration: %.2f m/s²", rocket.getAcceleration()));
        thrustLabel.setText(String.format("Thrust: %.2f N", rocket.getThrust()));
        fuelLabel.setText(String.format("Fuel: %.2f kg", rocket.getFuel()));
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RocketLaunchSimulation::new);
    }
}
