package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private Table<LocalDateTime, Integer, Integer> guavaTable;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;

		guavaTable = HashBasedTable.create();
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}
	
	@Override
	public LocalDateTime getTime(int joystickPosition, int speed) {
		return LocalDateTime.get(joystickPosition, speed);
	}	

	@Override
	public int getJoystickPosition(LocalDateTime time) {
		return guavaTable.get(time);
	}	

	@Override
	public int getReferenceSpeed(LocalDateTime time) {
		return guavaTable.get(time);
	}	

	@Override
	public void newValue(LocalDateTime time, int joystickPosition, int referenceSpeed) {
		guavaTable.put(time, joystickPosition, referenceSpeed);
	}
}
