package controller;

import javax.swing.*;
//Class JComponent is a java class
public class TankAngle {
    //Set up the range of the power.
    public static final int MIN_ANGLE = 10;
    public static final int MAX_ANGLE= 80;
    public static final int INI_ANGLE = 30;
    public static final int ANGLE_STEP = 1;


    public JComponent getControllerLabel() {
        return new JLabel("Angle");
    }

    public JSpinner getController() {
        //create a new object
        SpinnerNumberModel numberModel = new SpinnerNumberModel(INI_ANGLE, MIN_ANGLE, MAX_ANGLE, ANGLE_STEP);

        return new JSpinner(numberModel);
    }
}
