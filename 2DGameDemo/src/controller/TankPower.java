package controller;

import javax.swing.*;

public class TankPower {
    public static final int MIN_ANGLE = 10;
    public static final int MAX_ANGLE= 80;
    public static final int ANGLE_STEP = 1;
    public static final int INT_ANGLE = 30;

    public JComponent getControllerLabel() {
        return new JLabel("Power");
    }

    public JSpinner getController() {
        SpinnerNumberModel numberModel = new SpinnerNumberModel(INT_ANGLE, MIN_ANGLE, MAX_ANGLE, ANGLE_STEP);

        return new JSpinner(numberModel);
    }
}
