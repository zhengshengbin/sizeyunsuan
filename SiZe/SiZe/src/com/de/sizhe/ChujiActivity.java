package com.de.sizhe;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class ChujiActivity extends Activity {

	private TableLayout tableLayout;
	private TextView tv1;
	private Button b1;
	private int count=0;
	private int t=0;
	static DecimalFormat decimal = new DecimalFormat("#.##");
	
	private Handler handler=new Handler();
	private Runnable runnable=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			++t;
			tv1.setText("时间："+String.valueOf(t));
			handler.postDelayed(runnable, 1000);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tgaoji);
		handler.postDelayed(runnable, 1000);
		
		final Intent intent1=new Intent(ChujiActivity.this,TDeFenActivity.class);
		tableLayout=(TableLayout) findViewById(R.id.GTab);
		b1=(Button) findViewById(R.id.b1);
		tv1=(TextView) findViewById(R.id.shijian);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		count=bundle.getInt("tishu");//获取题目数量		
		
		String str1 = new String();    //题目
		final String s[] = new String[count]; //题目数组
		final String[] str=new String[count];  //用户输入的答案数组
		final String[] bjanswer = new String[count];// 标准答案
		int[] num1 = new int[4];   //计算的各个数值

		final EditText[] answer=new EditText[count];
		TextView[] show=new TextView[count];

		for(int i=0;i<count;i++)
		{
			int n=2;//4个操作数
			char[] op = { '+', '-', '×', '÷' };
			int[] no = new int[4];
			int cs;// 正负参数

			TableRow tableRow=new TableRow(this);
			show[i]=new TextView(this);
			answer[i]=new EditText(this);

			tableRow.addView(show[i]);
			tableRow.addView(answer[i]);

			for (int j = 0; j < n; j++) 
			{
				cs = (int) (Math.random() * 2);
				if (cs == 0)// 负数
					num1[j] = -(int) (Math.random() * 10);// 控制随机数数值
				else// 正数
					num1[j] = (int) (Math.random() * 10);// 控制随机数数值
			}

			for (int k = 0; k < n - 1; k++) 
            {
                no[k] = (int) (Math.random() * 4);// 随机产生操作符
                if (no[k] == 3 && num1[k + 1] == 0) 
                {
                    do {
                        num1[k + 1] = (int) (Math.random() * 100);// 如果除号后为0，则重新取数。
                        } while (num1[k + 1] == 0);
                }
            }
			
			for (int h = 0; h < n; h++) 
			{
				if (h != n - 1)
				{
					if (num1[h] < 0)
						str1 = str1 +"  (" + String.valueOf(num1[h])+")" + String.valueOf(op[no[h]]);
					else
						str1 = str1 +"  "+ String.valueOf(num1[h])+ String.valueOf(op[no[h]]);
				} 
				else
				{
					if (num1[h] < 0)
						str1 = str1 +"  (" + String.valueOf(num1[h]) +")=";
					else
						str1 = str1+"  " + String.valueOf(num1[h]) + "=";
				}
			}
			s[i] = str1;
			str1 = new String();

			
			
			// 计算标准答案
			int sign; // 累加运算时的符号
			float left, right;// 保存蹭结果
			decimal.setRoundingMode(RoundingMode.HALF_UP);
			left = 0;
			right = num1[0];
			sign = 1;

			for (int g = 0; g < n - 1; g++)
			{
				switch (op[no[g]])
				{
				case '+':
					left = left + sign * right;
					sign = 1;
					right = num1[g + 1];
					break;
				case '-':
					left = left + sign * right;
					sign = -1;
					right = num1[g + 1];
					break;
				case '×':
					right = right * num1[g + 1];
					break;
				case '÷':
					right = right / num1[g + 1];
					break;
				}
			}
			bjanswer[i] = String.valueOf(decimal.format(left + sign * right));

			show[i].setText(s[i]);
			show[i].setTextSize(18);
			answer[i].setEms(5);
			answer[i].setId(i);
			tableLayout.addView(tableRow);
		}
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				for(int i=0;i<count;i++)
				{
					str[i]=answer[i].getText().toString();		//用户输入的答案数组			
				}
				Bundle bundle=new Bundle();
				bundle.putStringArray("timu", s);
				bundle.putStringArray("useranswer", str);
				bundle.putStringArray("bjanswer", bjanswer);
				bundle.putInt("Ttishu", count);
				bundle.putString("shijian", String.valueOf(t));
				intent1.putExtras(bundle);
				startActivity(intent1);				
				handler.removeCallbacks(runnable);
				finish();
			}
		});
	}


}
