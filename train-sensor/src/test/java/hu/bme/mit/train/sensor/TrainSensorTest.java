package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainSensor trainSensor;
    TrainController trainController;
    TrainUser trainUser;

    @Before
    public void before() {
        // TODO Add initializations
        TrainSystem system = new TrainSystem();
		trainController = system.getController();
		trainSensor = system.getSensor();
		trainUser = system.getUser();
    }

    @Test
    public void TrainSensorInitializationTest() {
        LocalDateTime time = LocalDateTime.now();
        trainSensor.newValue(time, Integer(11), Integer(11));
        Assert.assertEquals(11, trainSensor.getJoystickPosition(time));
        Assert.assertEquals(11, trainSensor.getReferenceSpeed(time));
    }
}
