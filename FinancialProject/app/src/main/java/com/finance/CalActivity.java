package com.finance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.base.BaseActivity;

public class CalActivity extends BaseActivity {

    private TextView mTvTitle;

    private ImageView mIvBack;
    private TextView mIvStu;

    private TextView resultTextView1;
    private TextView resultTextView2;
    private Button clearButton;
    private Button divideButton;
    private Button multiplyButton;
    private Button deleteButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button minusButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button plusButton;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button zeroButton;
    private Button equalButton;
    private Button decimalButton;


    //声明其他变量
    private double firstOperand;
    private String operator;
    private boolean operatorPressed;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

    }


    @Override
    public void initWidget() {
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mIvStu = (TextView) findViewById(R.id.mIvStu);

        resultTextView1 = (TextView) findViewById(R.id.tv_result1);
        resultTextView2 = (TextView) findViewById(R.id.tv_result2);
        clearButton = (Button) findViewById(R.id.btn_clear);
        divideButton = (Button) findViewById(R.id.btn_divide);
        multiplyButton = (Button) findViewById(R.id.btn_multiply);
        deleteButton = (Button) findViewById(R.id.btn_delete);
        sevenButton = (Button) findViewById(R.id.btn_7);
        eightButton = (Button) findViewById(R.id.btn_8);
        nineButton = (Button) findViewById(R.id.btn_9);
        minusButton = (Button) findViewById(R.id.btn_minus);
        fourButton = (Button) findViewById(R.id.btn_4);
        fiveButton = (Button) findViewById(R.id.btn_5);
        sixButton = (Button) findViewById(R.id.btn_6);
        plusButton = (Button) findViewById(R.id.btn_plus);
        oneButton = (Button) findViewById(R.id.btn_1);
        twoButton = (Button) findViewById(R.id.btn_2);
        threeButton = (Button) findViewById(R.id.btn_3);
        zeroButton = (Button) findViewById(R.id.btn_0);
        equalButton = (Button) findViewById(R.id.btn_equal);
        decimalButton = (Button) findViewById(R.id.btn_decimal);

        mTvTitle.setText("计算器");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mIvStu.setOnClickListener(this);
        mIvStu.setText("修改");
        mIvStu.setVisibility(View.GONE);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView1.setText("");
                resultTextView2.setText("0");
                operatorPressed = false;
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operatorPressed) {
                    firstOperand = Double.parseDouble(resultTextView2.getText().toString());
                    operator = "/";
                    resultTextView1.setText(firstOperand+"/");
                    resultTextView2.setText("");
                    operatorPressed = true;
                }
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operatorPressed) {
                    firstOperand = Double.parseDouble(resultTextView2.getText().toString());
                    operator = "*";
                    resultTextView1.setText(firstOperand+"*");
                    resultTextView2.setText("");
                    operatorPressed = true;
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultTextView2.getText().toString();
                if (currentText.length() > 0) {
                    currentText = currentText.substring(0, currentText.length() - 1);
                    if (currentText.length() == 0) {
                        currentText = "0";
                    }
                    resultTextView2.setText(currentText);
                }
            }
        });

        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumberToResult("7");
            }
        });

        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumberToResult("8");
            }
        });

        nineButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                appendNumberToResult("9");
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operatorPressed) {
                    firstOperand = Double.parseDouble(resultTextView2.getText().toString());
                    operator = "-";
                    resultTextView1.setText(firstOperand+"-");
                    resultTextView2.setText("");
                    operatorPressed = true;
                }
            }
        });

        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumberToResult("4");
            }
        });

        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumberToResult("5");
            }
        });

        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumberToResult("6");
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operatorPressed) {
                    firstOperand = Double.parseDouble(resultTextView2.getText().toString());
                    operator = "+";
                    resultTextView1.setText(firstOperand+"+");
                    resultTextView2.setText("");
                    operatorPressed = true;
                }
            }
        });

        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumberToResult("1");
            }
        });

        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumberToResult("2");
            }
        });

        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumberToResult("3");
            }
        });

        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumberToResult("0");
            }
        });

        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!resultTextView2.getText().toString().contains(".")) {
                    appendNumberToResult(".");
                }
            }
        });

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operatorPressed) {
                    double secondOperand = Double.parseDouble(resultTextView2.getText().toString());
                    double result = 0;
                    switch (operator) {
                        case "+":
                            result = firstOperand + secondOperand;
                            resultTextView1.setText(firstOperand+"+"+secondOperand);
                            break;
                        case "-":
                            result = firstOperand - secondOperand;
                            resultTextView1.setText(firstOperand+"-"+secondOperand);
                            break;
                        case "*":
                            result = firstOperand * secondOperand;
                            resultTextView1.setText(firstOperand+"*"+secondOperand);
                            break;
                        case "/":
                            result = firstOperand / secondOperand;
                            resultTextView1.setText(firstOperand+"/"+secondOperand);
                            break;
                    }
                    resultTextView2.setText(String.valueOf(result));
                    operatorPressed = false;
                }
            }
        });
    }

    private void appendNumberToResult(String number) {
        if(number=="."&&resultTextView2.getText().toString().equals("0")){
            resultTextView2.setText("0.");
        }else {
        if (resultTextView2.getText().toString().equals("0")) {
            resultTextView2.setText(number);
        }else{
            resultTextView2.append(number);
        }
        }
    }




    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIvBack:
                CalActivity.this.finish();
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        initWidget();
        initData();
    }
}
