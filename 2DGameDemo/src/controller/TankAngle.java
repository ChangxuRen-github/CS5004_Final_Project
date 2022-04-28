package controller;

import javax.swing.*;
//Class JComponent is a java class
public class TankAngle {
    //Set up the range of the power.
    public static final int MIN_POWER = 10;
    public static final int MAX_POWER = 80;
    public static final int INI_POWER = 30;
    public static final int POWER_STEP = 1;

    public JComponent getControllerLabel() {
        return new JLabel("Angle");
    }

    public JSpinner getController() {
        //create a new object
        SpinnerNumberModel numberModel = new SpinnerNumberModel(INI_POWER, MIN_POWER, MAX_POWER, POWER_STEP);

        return new JSpinner(numberModel);
    }
}
