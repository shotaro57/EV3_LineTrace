package ev3Program;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class Color {

	// カラーセンサーのポート設定
	private static EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S2);

	// カラーセンサーに必要な定義
    private static SensorMode intensityOfRed = colorSensor.getMode(1);
    private static SensorMode colorID = colorSensor.getMode(0);
	private float intensityOfRedValue[] = new float[intensityOfRed.sampleSize()];
	private float colorIDValue[] = new float[colorID.sampleSize()];

	/*
     * 関数名	:getIntensityOfRed
     * 引数		:void
     * 戻り値	:float
     * 			:赤色の光強度を返す。
     */
	public float getIntensityOfRed() {
		intensityOfRed.fetchSample(intensityOfRedValue, 0);
    	return intensityOfRedValue[0];
	}

	/*
     * 関数名	:getColorID
     * 引数		:void
     * 戻り値	:float
     * 			:カラーIDを返す。
     */
	public float getColorID() {
		colorID.fetchSample(colorIDValue, 0);
    	return colorIDValue[0];
	}

}
