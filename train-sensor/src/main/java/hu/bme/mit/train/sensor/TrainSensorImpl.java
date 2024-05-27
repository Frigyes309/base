package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import java.time.LocalDateTime;
import com.google.common.collect.*;

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
		if (speedLimit < 0 || speedLimit > 500 ||
		 speedLimit * 2 < controller.getReferenceSpeed()) {
				user.setAlarmState(true);
		}
		else {
			user.setAlarmState(false);
		}
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}
	
	@Override
	public LocalDateTime getTime(int joystickPosition, int speed) {
		return LocalDateTime.now(); //guavaTable.get(joystickPosition, speed);
	}	

	@Override
	public int getJoystickPosition(LocalDateTime time, int referenceSpeed) {
		return guavaTable.get(time, referenceSpeed);
	}	 

	@Override
	public int getReferenceSpeed(LocalDateTime time, int joystickPosition) {
		return guavaTable.get(time, joystickPosition);
	}	

	@Override
	public void newValue(LocalDateTime time, int joystickPosition, int referenceSpeed) {
		guavaTable.put(time, joystickPosition, referenceSpeed);
	}
}
