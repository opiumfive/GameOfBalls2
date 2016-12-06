package com.opiumfive.gameofballs2.temp;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.text.Text;




public class InfoScene extends CameraScene {
	private static String FILE_RECORDS = "memoria-records";
	
	
	public Text scoreText1;
	
	public int last_best_score=0;
	
	
	public InfoScene() {
		super(MainActivity.mCamera);
		setBackgroundEnabled(true);
	
		
		scoreText1 = new Text(70, 120, MainActivity._main.mFont2, "Best Score:", "Best Score: XXXXX".length(), MainActivity._main.getVertexBufferObjectManager());
		refresh();

		attachChild(scoreText1);
		
	}
	
	void refresh(){
		open();
		scoreText1.setText("Best Score: " + last_best_score);
	}
	
	protected void Save_score()  {
		  if (MainActivity._main.score> last_best_score) {
		  try {
		    
		// � ���������� MainState.last_score_final �  ���� �������� ������ � ��������� ������ ���������� �� ��������� �����, � �� ��������� � ���� score.txt


		  final String TESTSTRING = new String(String.valueOf(MainActivity._main.score));
		  FileOutputStream fOut = MainActivity._main.openFileOutput(FILE_RECORDS,MainActivity._main.MODE_PRIVATE);
		  OutputStreamWriter osw = new OutputStreamWriter(fOut);
		  
		  osw.write(TESTSTRING);
		  
		  osw.flush();
		  osw.close();
		  } catch (Exception e) {
		   // TODO: handle exception
		  } }
	}
	
	protected void open() {
		  try
		  {                  /* ��������� ��� ����(��� �������� ����)*/

		   InputStream inStream = MainActivity._main.	openFileInput(FILE_RECORDS);
		      if(inStream != null)
		   {
		    InputStreamReader sr = new                               InputStreamReader(inStream);
		    BufferedReader reader = new BufferedReader(sr);
		    String str;
		    StringBuffer buffer = new StringBuffer();
		        str = reader.readLine();
		         buffer.append(str);
		            inStream.close();
		    String stroka;
		    stroka=buffer.toString();
		    last_best_score=Integer.parseInt(stroka);
		    }}
		   catch(Throwable t)
		  {
		     }
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