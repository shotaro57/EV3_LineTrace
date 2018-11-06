package ev3Program;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;

public class Sonic {

	// 超音波センサーのポート設定
	static EV3UltrasonicSensor sonicSensor = new EV3UltrasonicSensor(SensorPort.S3);

	// 超音波センサーに必要な定義
	static SensorMode sonic = sonicSensor.getMode(0);
	static float sonicValue[] = new float[sonic.sampleSize()];

	/*
     * 関数名	:getSonicValue
     * 引数		:void
     * 戻り値	:float
     * 			:壁を検知して、距離を返す。
     */
	public float getSonicValue() {
		sonic.fetchSample(sonicValue, 0);
		return sonicValue[0];
	}

}
