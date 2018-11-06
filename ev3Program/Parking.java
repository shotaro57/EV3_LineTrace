package ev3Program;

public class Parking extends LineTrace {

	Parking(int madSpeed) {
		super(madSpeed);
	}

	//駐車を行う。
    public void run() {
    	int numParking;			// 駐車する番号
    	float colorIDParking;		// 駐車する色のカラーID
    	float tmpGetColorID;		//
    	float tmpGetTachoCount;	// TachoCountの一時保存変数

    	// 壁検知して駐車する番号を取得
    	numParking = searchNumParking();

    	// 駐車する番号に応じてビープ音を鳴らす
    	doBeep(numParking);

    	// 駐車する番号のカラーIDを取得
    	colorIDParking = searchColorIDParking(numParking);

    	// 駐車する番号のカラーIDを取得するまでライントレースを行う
    	do {
    		tmpGetColorID = color.getColorID();

    		if( tmpGetColorID == 7 )			rotateMotor(30, 100);
        	else if( tmpGetColorID == 0 )		rotateMotor(30, 100);
        	else if( tmpGetColorID == 2 )		rotateMotor(30, 100);
        	else if( tmpGetColorID == 3 )		rotateMotor(30, 100);
        	else 								rotateMotor(100, 30);
    	}while(colorIDParking != tmpGetColorID);

    	// 駐車する
    	tmpGetTachoCount = rightMotor.getTachoCount();
    	while ( rightMotor.getTachoCount() - tmpGetTachoCount < 250 ) {
        	if( color.getColorID() == 6 )	rotateMotor(80, 30);
        	else 							rotateMotor(30, 80);
        }
    	rotateMotor(0, 0);
    	doRotateAngle(90);
    	tmpGetTachoCount = rightMotor.getTachoCount();
    	while ( rightMotor.getTachoCount() - tmpGetTachoCount < 400 ) {
        	rotateMotor(50, 50);
        }

    }
}
