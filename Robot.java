package org.usfirst.frc.team3291.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Ultrasonic;

public class Robot extends SampleRobot {
	
	RobotDrive myRobot = new RobotDrive(0, 1, 2, 3);
	Joystick stick = new Joystick(0);
	Joystick stick1 = new Joystick(1);
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	SendableChooser<String> chooser = new SendableChooser<>();
	Ultrasonic ultra = new Ultrasonic(1,1); 
	//creates the ultra object and assigns ultra to be an ultrasonic sensor which 
	//uses DigitalOutput 1 for the echo pulse and DigitalInput 1 for the trigger pulse
	 	 
	 
	 public Robot() {
		myRobot.setExpiration(0.1);
	}

	
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto modes", chooser);
		ultra.setAutomaticMode(true);
		
	}
    
    
	public void autonomous() {
		String autoSelected = chooser.getSelected();
		System.out.println("Auto selected: " + autoSelected);
		double range = ultra.getRangeInches();
		switch (autoSelected) {
		case customAuto:
			if (range < 24.0) {
				myRobot.drive(0.0, 0.0);
				Timer.delay(0.1);
				myRobot.drive(0.25, 0.0);
				Timer.delay(0.5);
				myRobot.drive(-0.5, -1.0);
				Timer.delay(0.3);
				myRobot.drive(0.5, 0.0);
			}
			else if (range < 20.0) {
				myRobot.drive(0.0, 0.0);
				
			}
			break;
		case defaultAuto:
		default:
			myRobot.setSafetyEnabled(true);
			myRobot.drive(-0.5, 0);
			while (range < 24.0) {
				myRobot.drive(0.0, 0.0);
				Timer.delay(0.1);
				myRobot.drive(0.25, 0.0);
				Timer.delay(0.5);
				myRobot.drive(-0.5, -1.0);
				Timer.delay(0.3);
				myRobot.drive(-0.5, 0.0);
			}
			break;
		}
		
	}

	public void operatorControl() {
		myRobot.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			myRobot.arcadeDrive(stick); // drive with arcade style (use right
										// stick)
			Timer.delay(0.005); // wait for a motor update time
		}
	}

	/**
	 * Runs during test mode
	 */
	@Override
	public void test() {
	}
}
