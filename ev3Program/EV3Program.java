package ev3Program;

import lejos.hardware.lcd.LCD;

public class EV3Program {

	public static void main(String[] args) {

		LineTrace lineTrace = null;
		LCD.clear();

        for( int process = 0; process < 6;process++ ) {
        	if( process == 0 ) {						// 白色と黒色の値を取得
                lineTrace = new SearchColor(2);
                lineTrace.initMotor();					// モーター初期化
                lineTrace.waitByStart();				// スタートの合図まで待機
        	}else if( process == 1 ) {					// ショートカット位置までライントレースを行う
                lineTrace = new LineTraceByShortCut(4);
        	}else if( process == 2 ) {					// ショートカットを行う
        		lineTrace = new ShortCut(4);
        	}else if( process == 3 ) {					// ゴールまでライントレースを行う
        		lineTrace = new LineTraceByGoal(4);
        	}else if( process == 4 ) {					// 壁を検知するまでライントレースを行う
        		lineTrace = new LineTraceByWall(1);
        	}else if( process == 5 ) {					// 壁を検知して駐車する
        		lineTrace = new Parking(1);
        	}

        	lineTrace.run();
		}

	}

}
