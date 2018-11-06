package ev3Program;

public class ShortCut extends LineTrace {
	ShortCut(int madSpeed) {
		super(madSpeed);
	}

	// ショートカットを行う。
    public void run() {
    	while( leftMotor.getTachoCount() < 2700 ) {
    		rotateMotor(70,30);
    	}
    }
}
