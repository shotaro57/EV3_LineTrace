package ev3Program;

public class LineTraceByGoal extends LineTrace {

	LineTraceByGoal(int madSpeed) {
		super(madSpeed);
	}

	// ゴールまでライントレースを行う。
    public void run() {
    	while ( ! isGoal() ) doLineTrace();
    }
}
