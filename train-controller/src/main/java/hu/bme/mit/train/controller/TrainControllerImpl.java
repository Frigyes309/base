package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		if (speedLimit < 0 || speedLimit > 500 || speedLimit * 2 < referenceSpeed ) {
			setAlarmState(true);
		} else {
			setAlarmState(false);
		}
		enforceSpeedLimit();
		
	}

	public void emergencyBreak() {
		this.speedLimit = 0;
		referenceSpeed = 0;
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		

		// speed of the train is influenced by the position of the joystick
		/*if(joystickPosition > 0 && referenceSpeed + step <= speedLimit)
			referenceSpeed += step;

		if(joystickPosition < 0 && referenceSpeed + step >= 0)
			referenceSpeed += step;*/
	}

	@Override
	public void getAlarmState() {

	}

	@Override
	public void setAlarmState(boolean alarmState) {
		
	}

}
