package ev3Program;

import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

abstract public class LineTrace {

	// モーターのポート設定
	protected static RegulatedMotor leftMotor  = Motor.C;
	protected static RegulatedMotor rightMotor = Motor.B;

	// その他パラメータ
	protected static int madSpeed;				// モータースピードの倍率
	protected static float colorWhite;			// 白色の値を格納する変数
	protected static float colorBlack;			// 黒色の値を格納する変数
	protected static Color color = new Color();
	protected static Sonic sonic = new Sonic();

	/*
     * 関数名	:LineTrace
     * 引数		:int
     * 戻り値	:void
     * 			:コンストラクタ。madSpeedを初期化する。
     */
	public LineTrace(int madSpeed) {
		LineTrace.madSpeed = madSpeed;
	}

	/*
     * 関数名	:run
     * 引数		:void
     * 戻り値	:void
     * 			:ロボットを走行させる。
     */
	public abstract void run();

	/*
     * 関数名	:initMotor
     * 引数		:void
     * 戻り値	:void
     * 			:モーターの初期化を行う。
     */
    public void initMotor() {
        leftMotor.resetTachoCount();
        rightMotor.resetTachoCount();
        leftMotor.rotateTo(0);
        rightMotor.rotateTo(0);
    }

    /*
     * 関数名	:waitByStart
     * 引数		:void
     * 戻り値	:void
     * 			:スタートを判断したら、関数を終了する。
     */
    public void waitByStart() {
    	while( sonic.getSonicValue() < 0.2 );
    }

    /*
     * 関数名	:doLineTrace
     * 引数		:void
     * 戻り値	:void
     * 			:ライントレースを行う。
     */
    protected void doLineTrace() {
    	int leftMotorPower, rightMotorPower;

    	leftMotorPower = (int)((color.getIntensityOfRed() - colorBlack) * 100);
    	rightMotorPower = (int)((colorWhite - color.getIntensityOfRed()) * 100);
    	rotateMotor(leftMotorPower, rightMotorPower);
    }

    /*
     * 関数名	:doRotateAngle
     * 引数		:int
     * 戻り値	:void
     * 			:90度回転させる。
     * 			:引数に角度を指定するが、90と-90しか対応しない。
     */
    protected void doRotateAngle(int angle) {
    	float tmpGetTachoCount;

    	tmpGetTachoCount = rightMotor.getTachoCount();
    	while( rightMotor.getTachoCount() - tmpGetTachoCount < 350 ) {
    		if( angle > 0 )			rotateMotor(0, 50);
    		else if( angle < 0 )	rotateMotor(50, 0);
    		else;
    	}
    }

	/*
     * 関数名	:rotateMotor
     * 引数		:int, int
     * 戻り値	:void
     * 			:左右のモーターを回転させる。引数に回転パワーを指定する。
     */
    protected void rotateMotor(int leftMotorPower, int rightMotorPower) {
        leftMotor.setSpeed(leftMotorPower * madSpeed);
        rightMotor.setSpeed(rightMotorPower * madSpeed);

        if ( leftMotorPower > 0 ) {
            leftMotor.forward();
        } else if ( leftMotorPower < 0 ) {
            leftMotor.backward();
        } else {
            leftMotor.stop();
        }
        if ( rightMotorPower > 0 ) {
            rightMotor.forward();
        } else if ( rightMotorPower < 0 ) {
            rightMotor.backward();
        } else {
            rightMotor.stop();
        }
    }

    /*
     * 関数名	:doBeep
     * 引数		:int
     * 戻り値	:void
     * 			:ビープ音を鳴らす。引数に回数を指定する。
     */
    protected void doBeep(int num) {
    	for(int i = 0; i < num; i++) Sound.beep();
    }

    /*
     * 関数名	:searchNumParking
     * 引数		:void
     * 戻り値	:int
     * 			:壁検知を行って駐車する番号を返す。
     */
    protected int searchNumParking() {
    	if( sonic.getSonicValue() < 0.23 )			return 2;
    	else if( sonic.getSonicValue() < 0.28 )		return 3;
    	else										return 1;
    }

    /*
     * 関数名	:searchColorIDParking
     * 引数		:int
     * 戻り値	:int
     * 			:駐車する番号に対応したカラーIDを返す。
     */
    protected int searchColorIDParking(int numParking) {
    	if(numParking == 1)			return 0;
    	else if(numParking == 2)	return 2;
    	else if(numParking == 3)	return 3;
    	else						return 7;		// 停止
    }

    /*
     * 関数名	:isWall
     * 引数		:void
     * 戻り値	:boolean
     * 			:緑線上でライントレースを行っている状態で、
     * 			:黒色を検知した場合に壁があると判定する。
     */
    protected boolean isWall() {
    	if( color.getColorID() == 7 )	return true;
    	else							return false;
    }

    /*
     * 関数名	:isGoal
     * 引数		:void
     * 戻り値	:boolean
     * 			:ゴールの判定を行う。緑色検知でゴールをみなす。
     */
    protected boolean isGoal() {
    	if( color.getColorID() == 1 ||
    		rightMotor.getTachoCount() > 8800 )		return true;
    	else										return false;
    }

    /*
     * 関数名	:isShortCut
     * 引数		:void
     * 戻り値	:boolean
     * 			:ショートカットの判定を行う。
     */
    protected boolean isShortcut() {
    	if( leftMotor.getTachoCount() < 1900 )		return false;
    	else										return true;
    }

}




