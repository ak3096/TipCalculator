package com.androidgeeks.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tvTipperAmount)
    TextView tvTipperAmount;
    @BindView(R.id.tvTippercent)
    TextView tvTippercent;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;

    float percentage = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;
    float totalBillAmount = 0;

    float DEFAULT_PERCENTAGE = 5;
    float REGULAR_PERCENTAGE = 10;
    float EXCELLENT_PERCENTAGE = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValues();

    }

    private void setTipValues() {

        tvTippercent.setText(getString(R.string.main_msg_tippercent, percentage));
        tvTipperAmount.setText(getString(R.string.main_msg_tiptotal, tipTotal));
        tvBillTotalAmount.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount));
    }

    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                percentage = DEFAULT_PERCENTAGE;
                break;
            case R.id.ibGoodService:
                percentage = REGULAR_PERCENTAGE;
                break;
            case R.id.ibExcellentService:
                percentage = EXCELLENT_PERCENTAGE;
                break;
        }
        calculateFinalBill();
        setTipValues();

    }

    @OnTextChanged(R.id.etBillAmount)
    public void onViewClicked() {
        calculateFinalBill();
        setTipValues();

    }

    private void calculateFinalBill() {
        if (percentage == 0) {
            percentage = REGULAR_PERCENTAGE;
        }
        if (!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals(".")) {
            totalBillAmount = Float.valueOf(etBillAmount.getText().toString());
        } else
            totalBillAmount = 0;
        tipTotal = (totalBillAmount * percentage) / 100;
        finalBillAmount = totalBillAmount + tipTotal;
    }
}

