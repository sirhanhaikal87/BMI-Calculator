package com.example.bmicalculator;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Class Variables; also are called 'Field'
    private TextView resultText;
    private Button calculateButton;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViews();

        String alertText = "This is my Variabel Text";
        Toast.makeText(this, alertText, Toast.LENGTH_LONG).show();

        SetupButtonClickListener();

    }


    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private void SetupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);
                if (age >= 18){
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }

            }
        });
    }



    private double calculateBmi() {

      String feetText = feetEditText.getText().toString();
      String inchesText = inchesEditText.getText().toString();
      String weightText = weightEditText.getText().toString();

      //resultText.setText("Age: " + ageText + ", Feet: " + feetText + ", Inches: " + inchesText + ", Weight: " + weightText);

        //Converting the Number 'String to Integer' variables
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        //height in meters is the inches multiplied  by 0.0253
        double heightInMeters = totalInches * 0.0254;

        //BMI Formula = weight in kg divided by height in meter squared
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (bmi < 18.5){
            //Display underweight
            fullResultString = bmiTextResult + " - You are Underweight";
        } else if (bmi > 25){
            //Display overweight
            fullResultString = bmiTextResult + " - You are Overweight";
        }else {
            //Display healthy
            fullResultString = bmiTextResult + " - You are Healthy Weight";
        }

        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (maleButton.isChecked()){
            //Display boy guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for boys";
        } else if (femaleButton.isChecked()) {
            //Display girl guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for girls";
        }else {
            //Display general guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range";
        }
        resultText.setText(fullResultString);
    }

}