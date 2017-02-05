package com.pie.lib;

import com.pie.ingredients.PiePort;
import com.pie.ingredients.PortCatalog;
import com.pie.lib.PieParser;
import com.pie.lib.util.PieConstants;
import com.pie.robot.controllers.DSControllerFactory;
import com.pie.robot.controllers.PWMMotorFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PieParserTest {

    private PieParser parser = PieParser.getInstance(PieConstants.robotFile);

    @Test
    public void testPinDefinitions() {

        HashMap<PiePort, PWMMotorFactory.MotorCatalog> testDefs = new HashMap<>();
        testDefs.put(PortCatalog.PWMPort.PWM0, PWMMotorFactory.MotorCatalog.TALON);
        testDefs.put(PortCatalog.PWMPort.PWM1, PWMMotorFactory.MotorCatalog.TALON);

        assertEquals(testDefs, parser.getPinDefinitions());
    }

    @Test
    public void testControlDefinitions() {

        HashMap<PiePort, DSControllerFactory.DSControllerCatalog> testDefs = new HashMap<>();
        testDefs.put(PortCatalog.USBPort.USB0, DSControllerFactory.DSControllerCatalog.JOYSTICK);
        testDefs.put(PortCatalog.USBPort.USB1, DSControllerFactory.DSControllerCatalog.JOYSTICK);

        assertEquals(testDefs, parser.getControlDefinitions());
    }

    @Test
    public void testSubsystemDefinitions() {

        HashMap<String, Object> configDefs = new HashMap<>();
        configDefs.put("motor-left", "PWM0");
        configDefs.put("motor-right", "PWM1");
        configDefs.put("control", "tank");
        ArrayList<String> joys = new ArrayList<>();
        joys.add("USB0");
        joys.add("USB1");
        configDefs.put("joysticks", joys);

        HashMap<String, Object> innerDefs = new HashMap<>();
        innerDefs.put("config", configDefs);
        innerDefs.put("type", "tank_drive_2");

        HashMap<String, Object> testDefs = new HashMap<>();
        testDefs.put("chassis", innerDefs);

        assertEquals(testDefs, parser.getSubsystemDefinitions());
    }

}
