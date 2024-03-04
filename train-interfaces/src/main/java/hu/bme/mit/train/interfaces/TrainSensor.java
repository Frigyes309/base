package hu.bme.mit.train.interfaces;

import java.time.LocalDateTime;

public interface TrainSensor {

	int getSpeedLimit();

	void overrideSpeedLimit(int speedLimit);

	LocalDateTime getTime(int joystickPosition, int speed);

	int getJoystickPosition(LocalDateTime time);

	int getReferenceSpeed(LocalDateTime time);

	void newValue(LocalDateTime time, int joystickPosition, int referenceSpeed);
}
