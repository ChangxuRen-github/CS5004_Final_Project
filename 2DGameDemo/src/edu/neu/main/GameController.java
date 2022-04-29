package edu.neu.main;

import edu.neu.controller.TankAngle;
import edu.neu.controller.TankPower;
import edu.neu.util.Constant;
import edu.neu.util.ImageResourceParser;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/*
 * This class maintains the game controller which control the tank's power and angle.
 * It also included the fire button which used to fire the cannonball
 *
 * The class used the Swing Spinner for tank's power and angle control. The tank's power and
 * angle can be changed through Spinner's button and also could be change directly in the Spinner's input box
 */
public class GameController extends JToolBar {
    public GameController(GamePanel gamePanel) {

        // Add tank power controller
        TankPower tankPower = new TankPower();
        this.add(tankPower.getControllerLabel());
        JSpinner powerController = tankPower.getController();
        powerController.setMaximumSize(new Dimension(64, 16));
        // Create the anonymous class which monitor the change of the power controller Spinner
        //add change Listener is an interface of the java.
        powerController.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Object value = powerController.getValue();
                if (value instanceof Integer) {
                    gamePanel.getTank().setGunPower((Integer) value);
                } else {
                    System.out.println("Value is not int");
                }
            }
        });
        this.add(powerController);

        // Add tank angle controller
        TankAngle tankAngle = new TankAngle();
        this.add(tankAngle.getControllerLabel());
        JSpinner angleController = tankAngle.getController();
        angleController.setMaximumSize(new Dimension(64, 16));
        angleController.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Object value = angleController.getValue();
                if (value instanceof Integer) {
                    gamePanel.getTank().setGunAngle((Integer) value);
                } else {
                    System.out.println("Value is not int");
                }
            }
        });
        this.add(angleController);

        this.add(new JLabel("  Fire  "));
        try {
            ImageIcon Fire = new ImageIcon(ImageResourceParser.getBufferedImage(Constant.RES_CONTROLLER_FIRE_PNG));
            JButton button = new JButton(Fire);
            /*
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tank.fire();
                }
            }); */

            // use a lambda function to respond to the button action
            button.addActionListener((e) -> gamePanel.getTank().fire());
            this.add(button);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        this.add(new JLabel( "  Reset  "));
        try{
            ImageIcon reset = new ImageIcon(ImageResourceParser.getBufferedImage(Constant.RES_CONTROLLER_RESET_PNG));
            JButton resetButton= new JButton(reset);
            this.add(resetButton);
            resetButton.addActionListener((e) -> gamePanel.reset());
            this.add(resetButton);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
