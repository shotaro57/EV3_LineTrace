package ev3Program;

public class LineTraceByShortCut extends LineTrace {

	LineTraceByShortCut(int madSpeed) {
		super(madSpeed);
	}

	// ショートカットの場所までライントレースを行う。
    public void run() {
    	while ( ! isShortcut() ) doLineTrace();
    }
}
