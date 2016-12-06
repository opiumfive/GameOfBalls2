package com.opiumfive.gameofballs2.temp;

import com.opiumfive.gameofballs2.temp.MainActivity;

import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;


public class ResultScene extends CameraScene {
	public Text APMText;
	public Text ACCText;
	public Text ScoreText;
	public Text HitText;
	public Text MaxStreakText;
	public int tap=10;
	
	public ResultScene() {
		super(MainActivity.mCamera);
		setBackgroundEnabled(true);
		setBackground(new Background(Color.BLACK));
		Text ResultsText = new Text(110, 30, MainActivity._main.mFont3, "GAME OVER", MainActivity._main.getVertexBufferObjectManager());
		Text ResultsText2 = new Text(100, 75, MainActivity._main.mFont3, "Your results:", MainActivity._main.getVertexBufferObjectManager());
		ScoreText = new Text(90, 120, MainActivity._main.mFont2, "Score:", "Score: XXXXX".length(), MainActivity._main.getVertexBufferObjectManager());	
		ACCText = new Text(90, 165, MainActivity._main.mFont2, "Accuracy:", "Accuracy: XXXXX".length(), MainActivity._main.getVertexBufferObjectManager());	
		APMText = new Text(90, 210, MainActivity._main.mFont2, "APM:", "APM: XXXXX".length(), MainActivity._main.getVertexBufferObjectManager());	
		HitText = new Text(90, 255, MainActivity._main.mFont2, "Targets hit:", "Targets hit: XXXXX".length(), MainActivity._main.getVertexBufferObjectManager());	
		
		attachChild(ResultsText);
		attachChild(ResultsText2);
		attachChild(ScoreText);
		attachChild(ACCText);
		attachChild(APMText);
		attachChild(HitText);
	}
	public void setTaps(int a, int mis, int tar,int gt,int sc,float ti){
		tap=a;
		float acc=(100.0f*(a-mis))/(a*1.0f);
		int accu=(int)acc;
		float apm = (tar*60.0f)/(gt*ti);
		ScoreText.setText("Score: " + sc);
		ACCText.setText("Accuracy: "+accu+"%");
		APMText.setText("APM: "+(int)apm);
		HitText.setText("Targets hit: "+tar);
	}
	
	public void Show() {
		setVisible(true);
		setIgnoreUpdate(false);
	}
	
	public void Hide() {
		setVisible(false);
		setIgnoreUpdate(true);
	}
}

