package piftik.github.com.apptotest;


import android.view.View;
import android.widget.EditText;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)

public class MockitoTest {
    private ActivityController<CalculatorActivity> mCalculatorActivityController;
    CalculatorActivity mCalculatorActivity;
    @Mock
    ICalculate mICalculateMock = Mockito.mock(ICalculate.class);

    @Before
    public void initTest() {
        mCalculatorActivityController = Robolectric.buildActivity(CalculatorActivity.class)
                .create().visible().start().resume();
        mCalculatorActivity = mCalculatorActivityController.get();

    }
    @After
    public void destroyActivity(){
        mCalculatorActivityController.pause().stop().destroy();
    }


    @Test
    public void MockTestWithRobolectric() {
        View additionButton = mCalculatorActivity.findViewById(R.id.addition_button);
        Assert.assertTrue(additionButton.getVisibility() == View.VISIBLE);
        View subtractionButton = mCalculatorActivity.findViewById(R.id.subtraction_button);
        Assert.assertTrue(subtractionButton.isEnabled());
        View multiplicationButton = mCalculatorActivity.findViewById(R.id.multiplication_button);
        View divisionButton = mCalculatorActivity.findViewById(R.id.division_button);
        Assert.assertNotNull(divisionButton);
        View resultButton = mCalculatorActivity.findViewById(R.id.result_button);
        EditText mInputEditTextLeft = (EditText) mCalculatorActivity.findViewById(R.id.input_field_left_edit_text);
        EditText mInputEditTextRight = (EditText) mCalculatorActivity.findViewById(R.id.input_field_right_edit_text);

        mInputEditTextLeft.setText("12");
        multiplicationButton.performClick();
        mInputEditTextRight.setText("13");
        resultButton.performClick();


        when(mICalculateMock.add(10.0, 20.0)).thenReturn(30.0);
        assertEquals(mICalculateMock.add(10, 20), 30.0, 0);
        verify(mICalculateMock).add(10.0, 20.0);
        doReturn(15.0).when(mICalculateMock).add(10.0, 5.0);
        assertEquals(mICalculateMock.add(10.0, 5.0), 15.0, 0);
        verify(mICalculateMock).add(10.0, 5.0);

        when(mICalculateMock.subtract(15.0, 25.0)).thenReturn(10.0);
        when(mICalculateMock.subtract(35.0, 25.0)).thenReturn(-10.0);

        assertEquals(mICalculateMock.subtract(15.0, 25.0), 10, 0);
        assertEquals(mICalculateMock.subtract(15.0, 25.0), 10, 0);
        assertEquals(mICalculateMock.subtract(35.0, 25.0), -10, 0);
        verify(mICalculateMock, atLeastOnce()).subtract(35.0, 25.0);
        verify(mICalculateMock, atLeast(2)).subtract(15.0, 25.0);
        verify(mICalculateMock, times(2)).subtract(15.0, 25.0);
        verify(mICalculateMock, never()).divide(10.0, 20.0);

        when(mICalculateMock.divide(15.0, 3)).thenReturn(5.0);
        assertEquals(mICalculateMock.divide(15.0, 3), 5.0, 0);
        verify(mICalculateMock).divide(15.0, 3);
    }

    @Test
    public void CalculateSpy() {
        Calculate calculateSpy = spy(new Calculate());
        when(calculateSpy.add(12.0, 17.0)).thenReturn(22.0);
        calculateSpy.add(12.0, 17.0);
        verify(calculateSpy).add(12.0, 17.0);
        assertEquals(22.0, calculateSpy.add(12.0, 17.0), 0);
    }


}
