package ev3Program;

public class LineTraceByWall extends LineTrace {

	LineTraceByWall(int madSpeed) {
		super(madSpeed);
	}

	//壁を検知するまでライントレースを行う。
    public void run() {
        while ( ! isWall() ) {
        	if( color.getColorID() == 6 )	rotateMotor(80, 30);
        	else 							rotateMotor(30, 80);
        }
        rotateMotor(0, 0);
    }
}
