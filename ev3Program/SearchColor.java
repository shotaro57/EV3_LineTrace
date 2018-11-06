package ev3Program;

import lejos.utility.Delay;

public class SearchColor extends LineTrace {

	SearchColor(int madSpeed) {
		super(madSpeed);
	}

	//ライントレースを行う前に黒と白の値を取得する。
    //最初に黒の上にセンサーがあることが前提。
    public void run() {
    	// 黒の値を取得
    	colorBlack = color.getIntensityOfRed();
    	Delay.msDelay(10);

    	// 左回転
    	while( rightMotor.getTachoCount() < 80) {
    		rotateMotor(-50, 50);
    	}
    	Delay.msDelay(10);

    	// 白の値を取得
    	colorWhite = color.getIntensityOfRed();
    	Delay.msDelay(10);
    }
}
