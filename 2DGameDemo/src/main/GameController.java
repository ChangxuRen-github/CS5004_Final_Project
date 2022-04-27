package main;

import controller.TankAngle;
import controller.TankPower;
import entity.Tank;
import util.Constant;
import util.ImageResourceParser;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This class maintains the game controller which control the tank's power and angle.
 * It also included the fire button which used to fire the cannonball
 *
 * The class used the Swing Spinner for tank's power and angle control. The tank's power and
 * angle can be changed through Spinner's button and also could be change directly in the Spinner's input box
 */
public class GameController extends JToolBar {
    public GameController(Tank tank) {
        TankPower powerController = new TankPower();
        this.add(powerController.getControllerLabel());
        JSpinner controller = powerController.getController();
        // Create the anonymous class which monitor the change of the power controller Spinner
        controller.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Object value = controller.getValue();
                if (value instanceof Integer) {
                    tank.setGunPower((Integer) value);
                } else {
                    System.out.println("Value is not int");
                }
            }
        });

        this.add(controller);

        TankAngle tankAngle = new TankAngle();
        this.add(tankAngle.getControllerLabel());
        JSpinner angleController = tankAngle.getController();
        angleController.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Object value = angleController.getValue();
                if (value instanceof Integer) {
                    tank.setGunAngle((Integer) value);
                } else {
                    System.out.println("Value is not int");
                }
            }
        });
        this.add(angleController);

        this.add(new JLabel("Fire"));
        try {
            ImageIcon fire = new ImageIcon(ImageResourceParser.getBufferedImage(Constant.RES_TARGET_PNG));
            JButton button = new JButton(fire);
            /*
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tank.fire();
                }
            }); */

            // use a lambda function to respond to the button action
            button.addActionListener((e) -> tank.fire());
            this.add(button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
