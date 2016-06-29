package de.SweetCode.e.rendering.particle.areas;

import de.SweetCode.e.E;
import de.SweetCode.e.Renderable;
import de.SweetCode.e.input.InputEntry;
import de.SweetCode.e.math.CircleBox;
import de.SweetCode.e.math.Location;
import de.SweetCode.e.math.Vector2D;
import de.SweetCode.e.rendering.particle.Particle;
import de.SweetCode.e.rendering.particle.ParticleTypes;
import de.SweetCode.e.utils.Assert;

import java.awt.*;

public class SpiralArea implements Renderable {

    private final static double rotation = 0;

    private final CircleBox circleBox;
    private final ParticleTypes particleType;
    private final Color color;

    private final double thetaMax;
    private final double step;
    private final double distance;

    /**
     * Constructor to create a new SpirlArea.
     * @param circleBox The circle box to determine the area of the spiral.
     * @param particleType The particle type of each particle.
     * @param color The color of the particles.
     * @param coils The amount of coils in the spiral.
     * @param distance The distance between each point.
     */
    public SpiralArea(CircleBox circleBox, ParticleTypes particleType, Color color, int coils, double distance) {

        Assert.assertNotNull("The circle box cannot be null.", circleBox);
        Assert.assertNotNull("The particle type cannot be null.", particleType);
        Assert.assertNotNull("The color cannot be null.", color);

        Assert.assertTrue("The amount of coils cannot be less than 1.", coils > 0);
        Assert.assertTrue("The distance between each point cannot be 0 or less.", distance > 0);

        this.circleBox = circleBox;
        this.particleType = particleType;
        this.color = color;

        this.thetaMax = coils * 2 * Math.PI;
        this.step = circleBox.getRadius() / this.thetaMax;
        this.distance = distance;

        this.setup();
    }

    private void setup() {

        for (double theta = this.distance / this.step; theta <= this.thetaMax; ) {

            double away = this.step * theta;
            double around = theta + this.rotation;

            Location location = new Location(
                    this.circleBox.getCenter().getX() + Math.cos(around) * away,
                    this.circleBox.getCenter().getY() + Math.sin(around) * away
            );

            E.getE().addComponent(new Particle(location, new Vector2D(0, 0), this.particleType, this.color, false, true, true, -1, 2));

            theta += distance / away;

        }

    }

    @Override
    public void render(Graphics2D value) {}

    @Override
    public void update(InputEntry input, long delta) {}

    @Override
    public boolean isActive() {
        return true;
    }
}
