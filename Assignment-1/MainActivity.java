package com.example.calculator_1;

// Default libraries that are imported
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


// Class of the main activity
public class MainActivity extends AppCompatActivity{

    // Variables that the entire class can use
    // Double variables to hold numbers from the screen - anything can be represented as a double, standard integers just have ".0" attached to them
    private double invertedValue;
    private double firstValue;
    private double secondValue;
    private double resultValue;
    private double currentValueDouble;
    private String screen1;
    private String screen2;

    // The value of the screen in string form
    private String currentValueString;

    // Boolean variables to signify which operation is in use
    private boolean addUsed = false;
    private boolean subtractUsed =  false;
    private boolean divideUsed =  false;
    private boolean multiplyUsed =  false;

    // Handle orientation changes by saving current state valyes
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        // Pass in all of the private variables that need to be saved
        outState.putString("procedure", screen1);
        outState.putString("results", screen2);
        outState.putBoolean("add", addUsed);
        outState.putBoolean("sub", subtractUsed);
        outState.putBoolean("divide", divideUsed);
        outState.putBoolean("multiply", multiplyUsed);
        outState.putDouble("first", firstValue);
        outState.putDouble("second", secondValue);
        outState.putDouble("inverted", invertedValue);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the results screen and the procedure screen to display the numbers
        final TextView resultsScreen = findViewById(R.id.resultsScreen);
        final TextView procedureScreen = findViewById(R.id.procedureScreen);

        // Initializing each of the operation buttons for the calculator
        final Button divideBtn = findViewById(R.id.divideBtn);
        final Button multiplyBtn = findViewById(R.id.multiplyBtn);
        final Button subtractBtn = findViewById(R.id.subtractBtn);
        final Button addBtn = findViewById(R.id.addBtn);
        final Button equalsBtn = findViewById(R.id.equalsBtn);
        final Button decimalBtn = findViewById(R.id.decimalBtn);
        final Button negateBtn = findViewById(R.id.negateBtn);

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

                // Save the current screen values in case of orientation change
                screen1 = procedureScreen.getText().toString();
                screen2 = resultsScreen.getText().toString();

                // Select what to do when a distinct button is pressed
                switch(id){
                    // Defining the procedure for each of the operation buttons

                    // When button "+/-" is pressed [negate/invert]
                    case R.id.negateBtn:
                        // Read the current value from the results screen and store in its own variable
                        invertedValue = Double.valueOf(resultsScreen.getText().toString());
                        // If current number is positive
                        if(firstValue > 0){
                            // Convert current number to negative by multiplying -1
                            invertedValue = invertedValue * -1.0;
                            // Copy the newly computed result to the results screen for further use
                            resultsScreen.setText(Double.toString(invertedValue));
                        }
                        // If current number is negative
                        else if(invertedValue < 0){
                            // Convert current number to positive by multiplying -1
                            invertedValue = invertedValue * -1.0;
                            // Copy the newly computed result to the results screen for further use
                            resultsScreen.setText(Double.toString(invertedValue));
                        }
                        // Else - whatever it is multiply by -1 anyways
                        else{
                            invertedValue = invertedValue * -1.0;
                            resultsScreen.setText(Double.toString(invertedValue));
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '/' is pressed [divide]
                    case R.id.divideBtn:
                        // If the divide function hasn't been used, compute for the first time
                        if(divideUsed == false){
                            // Retrieve the first value entered into the results screen
                            firstValue = Double.valueOf(resultsScreen.getText().toString());
                            // Add the value from the results screen to the procedure screen so the user can track their commands
                            procedureScreen.setText(Double.toString(firstValue));
                            // Append the operation used to the procedure screen so the user can track what operation they selected
                            procedureScreen.append("/");
                            // Reset the results screen with a zero ('0') so that the user can enter in the next number
                            resultsScreen.setText("0");
                            // Set the text color of the button to white so the user can see that it is actively selected
                            divideBtn.setTextColor(Color.WHITE);
                            // Set the operation variable to true so these steps won't be repeated
                            divideUsed = true;
                        }
                        else{
                            // Pull the last entered value from the screen
                            secondValue = Double.valueOf(resultsScreen.getText().toString());
                            // Append the value from the results screen to the procedure screen
                            procedureScreen.append(Double.toString(secondValue));
                            // Append the resulting operation to the procedure screen
                            procedureScreen.append("=");
                            resultValue = firstValue / secondValue;
                            procedureScreen.append(Double.toString(resultValue));
                            procedureScreen.append("/");
                            // Reset the results screen for the next value
                            resultsScreen.setText("0");
                            firstValue = resultValue;
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '*' is pressed [multiply]
                    case R.id.multiplyBtn:
                        // If the multiply function hasn't been used, compute for the first time
                        if(multiplyUsed == false){
                            // Retrieve the first value entered into the results screen
                            firstValue = Double.valueOf(resultsScreen.getText().toString());
                            // Add the value from the results screen to the procedure screen so the user can track their commands
                            procedureScreen.setText(Double.toString(firstValue));
                            // Append the operation used to the procedure screen so the user can track what operation they selected
                            procedureScreen.append("*");
                            // Reset the results screen with a zero ('0') so that the user can enter in the next number
                            resultsScreen.setText("0");
                            // Set the text color of the button to white so the user can see that it is actively selected
                            multiplyBtn.setTextColor(Color.WHITE);
                            // Set the operation variable to true so these steps won't be repeated
                            multiplyUsed = true;
                        }
                        else{
                            // Pull the last entered value from the screen
                            secondValue = Double.valueOf(resultsScreen.getText().toString());
                            // Append the value from the results screen to the procedure screen
                            procedureScreen.append(Double.toString(secondValue));
                            // Append the resulting operation to the procedure screen
                            procedureScreen.append("=");
                            resultValue = firstValue * secondValue;
                            procedureScreen.append(Double.toString(resultValue));
                            procedureScreen.append("*");
                            // Reset the results screen for the next value
                            resultsScreen.setText("0");
                            firstValue = resultValue;
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '-' is pressed [subtract]
                    case R.id.subtractBtn:
                        // If the subtract function hasn't been used, compute for the first time
                        if(subtractUsed == false){
                            // Retrieve the first value entered into the results screen
                            firstValue = Double.valueOf(resultsScreen.getText().toString());
                            // Add the value from the results screen to the procedure screen so the user can track their commands
                            procedureScreen.setText(Double.toString(firstValue));
                            // Append the operation used to the procedure screen so the user can track what operation they selected
                            procedureScreen.append("-");
                            // Reset the results screen with a zero ('0') so that the user can enter in the next number
                            resultsScreen.setText("0");
                            // Set the text color of the button to white so the user can see that it is actively selected
                            subtractBtn.setTextColor(Color.WHITE);
                            // Set the operation variable to true so these steps won't be repeated
                            subtractUsed = true;
                        }
                        else{
                            // Pull the last entered value from the screen
                            secondValue = Double.valueOf(resultsScreen.getText().toString());
                            // Append the value from the results screen to the procedure screen
                            procedureScreen.append(Double.toString(secondValue));
                            // Append the resulting operation to the procedure screen
                            procedureScreen.append("=");
                            resultValue = firstValue - secondValue;
                            procedureScreen.append(Double.toString(resultValue));
                            procedureScreen.append("-");
                            // Reset the results screen for the next value
                            resultsScreen.setText("0");
                            firstValue = resultValue;
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '+' is pressed [add]
                    case R.id.addBtn:
                        // If the add function hasn't been used, compute for the first time
                        if(addUsed == false){
                            // Retrieve the first value entered into the results screen
                            firstValue = Double.valueOf(resultsScreen.getText().toString());
                            // Add the value from the results screen to the procedure screen so the user can track their commands
                            procedureScreen.setText(Double.toString(firstValue));
                            // Append the operation used to the procedure screen so the user can track what operation they selected
                            procedureScreen.append("+");
                            // Reset the results screen with a zero ('0') so that the user can enter in the next number
                            resultsScreen.setText("0");
                            // Set the text color of the button to white so the user can see that it is actively selected
                            addBtn.setTextColor(Color.WHITE);
                            // Set the operation variable to true so these steps won't be repeated
                            addUsed = true;
                        }
                        else{
                            // Pull the last entered value from the screen
                            secondValue = Double.valueOf(resultsScreen.getText().toString());
                            // Append the value from the results screen to the procedure screen
                            procedureScreen.append(Double.toString(secondValue));
                            // Append the resulting operation to the procedure screen
                            procedureScreen.append("=");
                            resultValue = firstValue + secondValue;
                            procedureScreen.append(Double.toString(resultValue));
                            procedureScreen.append("+");
                            // Reset the results screen for the next value
                            resultsScreen.setText("0");
                            firstValue = resultValue;
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '=' is pressed [equals]
                    case R.id.equalsBtn:
                        // Retrieves the second value that was entered into the results screen
                        secondValue = Double.valueOf(resultsScreen.getText().toString());

                        if(addUsed == true){
                            // If the add operation is detected, perform the operation and store in a results value
                            resultValue = firstValue + secondValue;
                            // Reset the operation value so it can be activated again
                            addUsed = false;
                            // Reset the operations text color
                            addBtn.setTextColor(Color.BLACK);
                            // Add the second input value to the procedure screen
                            procedureScreen.append(Double.toString(secondValue));
                            // Add the newly computed result to the results screen
                            resultsScreen.setText(Double.toString(resultValue));
                        }
                        else if(subtractUsed == true){
                            // If the subtract operation is detected, perform the operation and store in a results value
                            resultValue = firstValue - secondValue;
                            // Reset the operation value so it can be activated again
                            subtractUsed = false;
                            // Reset the operations text color
                            subtractBtn.setTextColor(Color.BLACK);
                            // Add the second input value to the procedure screen
                            procedureScreen.append(Double.toString(secondValue));
                            // Add the newly computed result to the results screen
                            resultsScreen.setText(Double.toString(resultValue));
                        }
                        else if(multiplyUsed == true){
                            // If the multiply operation is detected, perform the operation and store in a results value
                            resultValue = firstValue * secondValue;
                            // Reset the operation value so it can be activated again
                            multiplyUsed = false;
                            // Reset the operations text color
                            multiplyBtn.setTextColor(Color.BLACK);
                            // Add the second input value to the procedure screen
                            procedureScreen.append(Double.toString(secondValue));
                            // Add the newly computed result to the results screen
                            resultsScreen.setText(Double.toString(resultValue));
                        }
                        else if(divideUsed == true){
                            // If the divide operation is detected, perform the operation and store in a results value
                            resultValue = firstValue / secondValue;
                            // Reset the operation value so it can be activated again
                            divideUsed = false;
                            // Reset the operations text color
                            divideBtn.setTextColor(Color.BLACK);
                            // Add the second input value to the procedure screen
                            procedureScreen.append(Double.toString(secondValue));
                            // Add the newly computed result to the results screen
                            resultsScreen.setText(Double.toString(resultValue));

                        }
                        else{
                            // If the equals button is hit repeatdly, leave the current value on the screen
                            resultsScreen.setText(resultsScreen.getText().toString());
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '.' is pressed [decimal]
                    case R.id.decimalBtn:
                        // Takes the values from the results screen and puts in a screen to find is a decimal has been used
                        String decimalDetect = resultsScreen.getText().toString();
                        // Only adds a decimal if there is no decimal detected in the results screen
                        if(!decimalDetect.contains(".")){
                            // Appends a decimal to the results screen where the user has entered in a value
                            resultsScreen.append(".");
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // Defining the procedure for the numerical buttons

                    // When button '0' is pressed
                    case R.id.zeroBtn:
                        // Gets the current value from the results screen and converts it to a string and then to a double
                        currentValueString = resultsScreen.getText().toString();
                        currentValueDouble = Double.valueOf(currentValueString);

                        // If the only value on the screen is a 0, replace it with the number pressed, else, append the number
                        if(currentValueDouble == 0 && currentValueString.length() == 1){
                            resultsScreen.setText("0");
                        }
                        else{
                            resultsScreen.append("0");
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '1' is pressed
                    case R.id.oneBtn:
                        // Gets the current value from the results screen and converts it to a string and then to a double
                        currentValueString = resultsScreen.getText().toString();
                        currentValueDouble = Double.valueOf(currentValueString);

                        // If the only value on the screen is a 0, replace it with the number pressed, else, append the number
                        if(currentValueDouble == 0 && currentValueString.length() == 1){
                            resultsScreen.setText("1");
                        }
                        else{
                            resultsScreen.append("1");
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '2' is pressed
                    case R.id.twoBtn:
                        // Gets the current value from the results screen and converts it to a string and then to a double
                        currentValueString = resultsScreen.getText().toString();
                        currentValueDouble = Double.valueOf(currentValueString);

                        // If the only value on the screen is a 0, replace it with the number pressed, else, append the number
                        if(currentValueDouble == 0 && currentValueString.length() == 1){
                            resultsScreen.setText("2");
                        }
                        else{
                            resultsScreen.append("2");
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '3' is pressed
                    case R.id.threeBtn:
                        // Gets the current value from the results screen and converts it to a string and then to a double
                        currentValueString = resultsScreen.getText().toString();
                        currentValueDouble = Double.valueOf(currentValueString);

                        // If the only value on the screen is a 0, replace it with the number pressed, else, append the number
                        if(currentValueDouble == 0 && currentValueString.length() == 1){
                            resultsScreen.setText("3");
                        }
                        else{
                            resultsScreen.append("3");
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '4' is pressed
                    case R.id.fourBtn:
                        // Gets the current value from the results screen and converts it to a string and then to a double
                        currentValueString = resultsScreen.getText().toString();
                        currentValueDouble = Double.valueOf(currentValueString);

                        // If the only value on the screen is a 0, replace it with the number pressed, else, append the number
                        if(currentValueDouble == 0 && currentValueString.length() == 1){
                            resultsScreen.setText("4");
                        }
                        else{
                            resultsScreen.append("4");
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '5' is pressed
                    case R.id.fiveBtn:
                        // Gets the current value from the results screen and converts it to a string and then to a double
                        currentValueString = resultsScreen.getText().toString();
                        currentValueDouble = Double.valueOf(currentValueString);

                        // If the only value on the screen is a 0, replace it with the number pressed, else, append the number
                        if(currentValueDouble == 0 && currentValueString.length() == 1){
                            resultsScreen.setText("5");
                        }
                        else{
                            resultsScreen.append("5");
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '6' is pressed
                    case R.id.sixBtn:
                        // Gets the current value from the results screen and converts it to a string and then to a double
                        currentValueString = resultsScreen.getText().toString();
                        currentValueDouble = Double.valueOf(currentValueString);

                        // If the only value on the screen is a 0, replace it with the number pressed, else, append the number
                        if(currentValueDouble == 0 && currentValueString.length() == 1){
                            resultsScreen.setText("6");
                        }
                        else{
                            resultsScreen.append("6");
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '7' is pressed
                    case R.id.sevenBtn:
                        // Gets the current value from the results screen and converts it to a string and then to a double
                        currentValueString = resultsScreen.getText().toString();
                        currentValueDouble = Double.valueOf(currentValueString);

                        // If the only value on the screen is a 0, replace it with the number pressed, else, append the number
                        if(currentValueDouble == 0 && currentValueString.length() == 1){
                            resultsScreen.setText("7");
                        }
                        else{
                            resultsScreen.append("7");
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '8' is pressed
                    case R.id.eightBtn:
                        // Gets the current value from the results screen and converts it to a string and then to a double
                        currentValueString = resultsScreen.getText().toString();
                        currentValueDouble = Double.valueOf(currentValueString);

                        // If the only value on the screen is a 0, replace it with the number pressed, else, append the number
                        if(currentValueDouble == 0 && currentValueString.length() == 1){
                            resultsScreen.setText("8");
                        }
                        else{
                            resultsScreen.append("8");
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;

                    // When button '9' is pressed
                    case R.id.nineBtn:
                        // Gets the current value from the results screen and converts it to a string and then to a double
                        currentValueString = resultsScreen.getText().toString();
                        currentValueDouble = Double.valueOf(currentValueString);

                        // If the only value on the screen is a 0, replace it with the number pressed, else, append the number
                        if(currentValueDouble == 0 && currentValueString.length() == 1){
                            resultsScreen.setText("9");
                        }
                        else{
                            resultsScreen.append("9");
                        }
                        // Save the current screen values in case of orientation change
                        screen1 = procedureScreen.getText().toString();
                        screen2 = resultsScreen.getText().toString();

                        break;
                }// End switch statement
            }
        };

        // Setting the click listeners

        // Setting the click listener for the operation buttons
        divideBtn.setOnClickListener(activeListener);
        multiplyBtn.setOnClickListener(activeListener);
        subtractBtn.setOnClickListener(activeListener);
        addBtn.setOnClickListener(activeListener);
        equalsBtn.setOnClickListener(activeListener);
        decimalBtn.setOnClickListener(activeListener);
        negateBtn.setOnClickListener(activeListener);

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
        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // When the clear button is selected, replace the procedure screen value with nothing
                procedureScreen.setText("");
                // When the clear button is selected, replace the current results screen value with 0
                resultsScreen.setText("0");

                // Reset operations in use back to false so no operation is active
                addUsed = false;
                subtractUsed = false;
                multiplyUsed = false;
                divideUsed = false;

                // Reset the colors of the buttona back to black
                addBtn.setTextColor(Color.BLACK);
                multiplyBtn.setTextColor(Color.BLACK);
                divideBtn.setTextColor(Color.BLACK);
                subtractBtn.setTextColor(Color.BLACK);
            }
        });

        // Setting the backspace button and implementing it's functionality
        final Button backspace = findViewById(R.id.backspaceBtn);
        backspace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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
                else if(len == 1){
                    resultsScreen.setText("0");
                }
                // If there is is no value in the screen to be removed, set to 0
                else{
                    resultsScreen.setText("0");
                }
                // FIXME: set operand uses to false if they were deleted/remove operations if they were deleted
            }
        });

        // If an orientation change happened then perform the following actions
        if(savedInstanceState != null){
            // Setting the screens back to what was on the screens before orientation change
            screen1 = savedInstanceState.getString("procedure");
            screen2 = savedInstanceState.getString("results");
            procedureScreen.setText(screen1);
            resultsScreen.setText(screen2);

            // Setting the buttons text back to there original color if they were used or not
            addUsed = savedInstanceState.getBoolean("add");
            subtractUsed = savedInstanceState.getBoolean("subtract");
            divideUsed = savedInstanceState.getBoolean("divide");
            multiplyUsed = savedInstanceState.getBoolean("multiply");

            // Retriving the last known values
            firstValue = savedInstanceState.getDouble("first");
            secondValue = savedInstanceState.getDouble("second");
            invertedValue = savedInstanceState.getDouble("inverted");

            if(addUsed == true){
                addBtn.setTextColor(Color.WHITE);
            }
            else{
                addBtn.setTextColor(Color.BLACK);
            }
            if(subtractUsed == true){
                subtractBtn.setTextColor(Color.WHITE);
            }
            else{
                subtractBtn.setTextColor(Color.BLACK);
            }
            if(divideUsed == true){
                divideBtn.setTextColor(Color.WHITE);
            }
            else{
                divideBtn.setTextColor(Color.BLACK);
            }
            if(multiplyUsed == true){
                multiplyBtn.setTextColor(Color.WHITE);
            }
            else{
                multiplyBtn.setTextColor(Color.BLACK);
            }
        }
    }
}
