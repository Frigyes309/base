package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.*;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController TC;
    TrainUser TU;
    TrainSensor TS;

    @Before
    public void before() {
        TC = mock(TrainController.class);
        TU = mock(TrainUser.class);
        TS = new TrainSensorImpl(TC, TU);
    }

    @Test
    public void testNegative() {
        when(TC.getReferenceSpeed()).thenReturn(0);

        TS.overrideSpeedLimit(-1);
        verify(TU,  times(1)).setAlarmState(true);
    } 

    @Test
    public void testHighValue() {
        when(TC.getReferenceSpeed()).thenReturn(0);

        TS.overrideSpeedLimit(501);
        verify(TU,  times(1)).setAlarmState(true);
    } 

    @Test
    public void testRightValue() {
        when(TC.getReferenceSpeed()).thenReturn(0);

        TS.overrideSpeedLimit(480);
        verify(TU,  times(0)).setAlarmState(true);
    } 

    @Test
    public void testRelativeLow() {
        when(TC.getReferenceSpeed()).thenReturn(100);

        TS.overrideSpeedLimit(49);
        
        verify(TU,  times(1)).setAlarmState(true);
    } 
}
