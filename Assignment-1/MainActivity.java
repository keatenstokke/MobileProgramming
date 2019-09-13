package com.example.calculator_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double currentNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the results screen to display the numbers
        final TextView resultsScreen = findViewById(R.id.resultsScreen);

        // Initializing each of the operation buttons for the calculator
        final Button divideBtn = findViewById(R.id.divideBtn);
        final Button multiplyBtn = findViewById(R.id.multiplyBtn);
        final Button subtractBtn = findViewById(R.id.subtractBtn);
        final Button addBtn = findViewById(R.id.addBtn);
        final Button equalsBtn = findViewById(R.id.equalsBtn);
        final Button decimalBtn = findViewById(R.id.decimalBtn);

        // Initializing each of the numerical buttons for the calculator
        final Button zeroBtn = findViewById(R.id.zeroBtn);
        final Button oneBtn = findViewById(R.id.oneBtn);
        final Button twoBtn = findViewById(R.id.twoBtn);
        final Button threeBtn = findViewById(R.id.threeBtn);
        final Button fourBtn = findViewById(R.id.fourBtn);
        final Button fiveBtn = findViewById(R.id.fiveBtn);
        final Button sixBtn = findViewById(R.id.sixBtn);
        final Button sevenBtn = findViewById(R.id.sevenBtn);
        final Button eightBtn = findViewById(R.id.eightBtn);
        final Button nineBtn = findViewById(R.id.nineBtn);

        // Create a listener to know when the user interacts with a button
        final View.OnClickListener activeListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){

                // Retrieve the value entered in by the user
                final int id = v.getId();

                // Select what to do when a distinct button is pressed
                switch(id){
                    // Defining the procedure for the operation buttons
                    // When button "+/-" is pressed
                    case R.id.negateBtn:
                        // If current number is positive, convert to negative

                        // Else if number is negative, convert to positive


                        break;
                    // When button '/' is pressed
                    case R.id.divideBtn:
                        resultsScreen.append("/");

                        break;
                    // When button '*' is pressed
                    case R.id.multiplyBtn:
                        resultsScreen.append("*");

                        break;
                    // When button '-' is pressed
                    case R.id.subtractBtn:
                        resultsScreen.append("-");

                        break;
                    // When button '+' is pressed
                    case R.id.addBtn:
                        resultsScreen.append("+");

                        break;
                    // When button '=' is pressed
                    case R.id.equalsBtn:

                        break;
                    // When button '.' is pressed (decimal)
                    case R.id.decimalBtn:
                        resultsScreen.append(".");

                        break;

                    // Defining the procedure for the numerical buttons
                    // When button '0' is pressed
                    case R.id.zeroBtn:
                        resultsScreen.append("0");

                        break;
                    // When button '1' is pressed
                    case R.id.oneBtn:
                        resultsScreen.append("1");

                        break;
                    // When button '2' is pressed
                    case R.id.twoBtn:
                        resultsScreen.append("2");

                        break;
                    // When button '3' is pressed
                    case R.id.threeBtn:
                        resultsScreen.append("3");

                        break;
                    // When button '4' is pressed
                    case R.id.fourBtn:
                        resultsScreen.append("4");

                        break;
                    // When button '5' is pressed
                    case R.id.fiveBtn:
                        resultsScreen.append("5");

                        break;
                    // When button '6' is pressed
                    case R.id.sixBtn:
                        resultsScreen.append("6");

                        break;
                    // When button '7' is pressed
                    case R.id.sevenBtn:
                        resultsScreen.append("7");

                        break;
                    // When button '8' is pressed
                    case R.id.eightBtn:
                        resultsScreen.append("8");

                        break;
                    // When button '9' is pressed
                    case R.id.nineBtn:
                        resultsScreen.append("9");

                        break;
                }// End switch statement
            }
        };

        // Setting the click listener

        // Setting the click listener for the operation buttons
        divideBtn.setOnClickListener(activeListener);
        multiplyBtn.setOnClickListener(activeListener);
        subtractBtn.setOnClickListener(activeListener);
        addBtn.setOnClickListener(activeListener);
        equalsBtn.setOnClickListener(activeListener);
        decimalBtn.setOnClickListener(activeListener);

        // Setting the click listener for the numerical buttons
        zeroBtn.setOnClickListener(activeListener);
        oneBtn.setOnClickListener(activeListener);
        twoBtn.setOnClickListener(activeListener);
        threeBtn.setOnClickListener(activeListener);
        fourBtn.setOnClickListener(activeListener);
        fiveBtn.setOnClickListener(activeListener);
        sixBtn.setOnClickListener(activeListener);
        sevenBtn.setOnClickListener(activeListener);
        eightBtn.setOnClickListener(activeListener);
        nineBtn.setOnClickListener(activeListener);

        // Setting the clear button and implementing its functionality
        final Button clear = findViewById(R.id.clearBtn);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the clear button is selected, replace the current results screen value with 0
                resultsScreen.setText("0");
            }
        });

        // Setting the backspace button and implementing it's functionality
        final Button backspace = findViewById(R.id.backspaceBtn);
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gets the current values stored in the results screen
                String currentValue = resultsScreen.getText().toString();

                // Gets the current length of the values in the results screen
                int len = currentValue.length();

                // Sets the current value on the screen to -1 of the previous length (deletes the last entered value [backspace])
                if(len > 1){
                    currentValue = currentValue.substring(0, len - 1);

                    // Updates the results screen with the newly determined value
                    resultsScreen.setText(currentValue);
                }
                // If there is one value in the screen to be removed, replace with 0
                else if(len == 1) {
                    resultsScreen.setText("0");
                }
                // If there is is no value in the screen to be removed, set to 0
                else {
                    resultsScreen.setText("0");
                }
            }
        });

    }
}
