package com.de.sizhe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TixingActivity extends Activity {

	private Button chubtn,zhongbtn,gaobtn;
	private EditText edtishu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tixing);
		
		chubtn=(Button) findViewById(R.id.chuji);
		zhongbtn=(Button) findViewById(R.id.zhongji);
		
		gaobtn=(Button) findViewById(R.id.gaoji);		
		edtishu=(EditText) findViewById(R.id.tishu);
		
		chubtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TixingActivity.this,ChujiActivity.class);
				Bundle bundle=new Bundle();
				bundle.putInt("tishu", Integer.valueOf(edtishu.getText().toString()));
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
		
		
		zhongbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				Intent intent=new Intent(TixingActivity.this,ZhongjiMainActivity.class);
				Bundle bundle=new Bundle();
				bundle.putInt("tishu", Integer.valueOf(edtishu.getText().toString()));
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
		
		gaobtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TixingActivity.this,TGaojiActivity.class);
				Bundle bundle=new Bundle();
				bundle.putInt("tishu", Integer.valueOf(edtishu.getText().toString()));
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tixing, menu);
		return true;
	}

}
