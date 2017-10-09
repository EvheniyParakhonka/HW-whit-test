package piftik.github.com.apptotest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    private String mResult;
    private TextView mSignTextView;
    private EditText mInputEditTextLeft;
    private EditText mInputEditTextRight;
    private TextView mAnswerTextView;
    private ICalculate mICalculate = new Calculate();

    @Override
    public void onCreate(final @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initView();
    }

    private void initView() {
        mSignTextView = (TextView) findViewById(R.id.sign_text_view);
        mInputEditTextRight = (EditText) findViewById(R.id.input_field_right_edit_text);
        mInputEditTextLeft = (EditText) findViewById(R.id.input_field_left_edit_text);
        View additionButton = findViewById(R.id.addition_button);
        View subtractionButton = findViewById(R.id.subtraction_button);
        View multiplicationButton = findViewById(R.id.multiplication_button);
        View divisionButton = findViewById(R.id.division_button);
        View resultButton = findViewById(R.id.result_button);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        additionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {

                mICalculate.add(getValueFromLeftEditText(), getValueFromRightEditText());
                mSignTextView.setText("+");

            }
        });

        subtractionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {

                mResult = String.valueOf(mICalculate.subtract(getValueFromLeftEditText(), getValueFromRightEditText()));
                mSignTextView.setText("-");


            }
        });
        multiplicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {

                mResult = String.valueOf(mICalculate.multiply(getValueFromLeftEditText(), getValueFromRightEditText()));
                mInputEditTextLeft.setText("*");

            }
        });

        divisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                if (getValueFromLeftEditText() != 0 && getValueFromRightEditText() != 0) {
                    mResult = String.valueOf(mICalculate.divide(getValueFromLeftEditText(), getValueFromRightEditText()));
                    mInputEditTextLeft.setText("/");
                }
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                showResult(mResult);
            }
        });
    }

    private void showResult(String pResult) {
        mAnswerTextView.setText(pResult);
    }

    private double getValueFromLeftEditText() {
        double left = 0;
        if (mInputEditTextLeft.getText().toString().trim().length() > 0) {
            left = Integer.parseInt(mInputEditTextLeft.getText().toString());
        }
        return left;
    }

    private double getValueFromRightEditText() {
        double right = 0;
        if (mInputEditTextRight.getText().toString().trim().length() < 0) {
            right = Double.parseDouble(mInputEditTextRight.getText().toString());

        }
        return right;

    }
}

