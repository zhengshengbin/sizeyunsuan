package com.de.sizhe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TDeFenActivity extends Activity {

	private Button cxbtn,tcbtn;
	private TableLayout dtab1;
	private TextView textView;
	private int count=0;
	private String sj=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tde_fen);		
		dtab1=(TableLayout) findViewById(R.id.DTab1);
		cxbtn=(Button) findViewById(R.id.chongxin);
		tcbtn=(Button) findViewById(R.id.tuichu);
		textView=(TextView) findViewById(R.id.showtime);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		count=bundle.getInt("Ttishu");
		
		String[] sty=new String[count];
		String[] stb=new String[count];
		String[] stimu=new String[count];
		
		sty=bundle.getStringArray("useranswer");
		stb=bundle.getStringArray("bjanswer");
		sj=bundle.getString("shijian");
		stimu=bundle.getStringArray("timu");
		
		
		textView.setText("完成时间为："+sj+"s");
		int tn=(count+1)*3;
		TextView[] Tshow=new TextView[tn];
		
		TableRow tableRow=new TableRow(this);
		Tshow[0]=new TextView(this);
		Tshow[1]=new TextView(this);
		Tshow[2]=new TextView(this);
		Tshow[0].setText("                   题目");
		Tshow[1].setText("      你的答案");
		Tshow[2].setText("      正确答案");
		tableRow.addView(Tshow[0]);
		tableRow.addView(Tshow[1]);
		tableRow.addView(Tshow[2]);
		dtab1.addView(tableRow);
		for(int i=0;i<count;i++)
		{
			TableRow tableRow1=new TableRow(this);
			Tshow[(i+1)*3]=new TextView(this);
			Tshow[(i+1)*3+1]=new TextView(this);
			Tshow[(i+1)*3+2]=new TextView(this);
			tableRow1.addView(Tshow[(i+1)*3]);
			tableRow1.addView(Tshow[(i+1)*3+1]);
			tableRow1.addView(Tshow[(i+1)*3+2]);
			
			if(sty[i].equals(stb[i]))
			{
				Tshow[(i+1)*3].setText(stimu[i]);
				Tshow[(i+1)*3+1].setText(sty[i]);
				Tshow[(i+1)*3+1].setGravity(Gravity.RIGHT);
	            Tshow[(i+1)*3+2].setText(stb[i]);
				Tshow[(i+1)*3+2].setGravity(Gravity.RIGHT);
			}
			else
			{
				Tshow[(i+1)*3].setText(stimu[i]);
				Tshow[(i+1)*3+1].setText(sty[i]);
				Tshow[(i+1)*3+1].setGravity(Gravity.RIGHT);
				Tshow[(i+1)*3+1].setTextColor(Color.RED);
	            Tshow[(i+1)*3+2].setText(stb[i]);
				Tshow[(i+1)*3+2].setGravity(Gravity.RIGHT);
			}
			dtab1.addView(tableRow1);
		}
		
		cxbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TDeFenActivity.this,TixingActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		tcbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}

}
