/* codes.nora.bmicalculator - computes Body Mass Index from weight and height
    Copyright (C) 2019 Leonora Tindall and Nicholas Meersman

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package codes.nora.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText heightMajorTextEntry;
    private EditText heightMinorTextEntry;
    private EditText weightTextEntry;
    private TextView bmiResultTextView;
    private TextView bmiClassTextView;
    private RadioGroup unitSelectRadioGroup;
    private TextView weightTextView;
    private TextView heightMinorTextView;
    private TextView heightMajorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heightMajorTextEntry = findViewById(R.id.heightMajorTextEntry);
        heightMinorTextEntry = findViewById(R.id.heightMinorTextEntry);
        weightTextEntry = findViewById(R.id.weightTextEntry);
        bmiResultTextView = findViewById(R.id.bmiResultTextView);
        bmiClassTextView = findViewById(R.id.bmiClassTextView);
        unitSelectRadioGroup = findViewById(R.id.unitSelectRadioGroup);
        weightTextView = findViewById(R.id.weightTextView);
        heightMinorTextView = findViewById(R.id.heightMinorTextView);
        heightMajorTextView = findViewById(R.id.heightMajorTextView);
    }

    private boolean checkFieldsNotEmpty() {
        return !(heightMinorTextEntry.getText().equals("")
                || heightMajorTextEntry.getText().equals("")
                || weightTextEntry.getText().equals(""));
    }

    private boolean checkFieldsParseAsInteger() {
        try {
            Integer.parseInt(heightMajorTextEntry.getText().toString());
            Integer.parseInt(heightMinorTextEntry.getText().toString());
            Integer.parseInt(weightTextEntry.getText().toString());
            return true;
        } catch (NumberFormatException _) {
            return false;
        }
    }

    private String calculateBmiClass(double bmi) {
        if (bmi < 15.0) {
            return "Very Severely Underweight";
        } else if (bmi < 16) {
            return "Severely Underweight";
        } else if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25 ) {
            return "Healthy Weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else if (bmi < 35) {
            return "Obese Class I";
        } else if (bmi < 40) {
            return "Obese Class II";
        } else {
            return "Obese Class III";
        }
    }

    private void onClickImperial() {
        if (checkFieldsNotEmpty() && checkFieldsParseAsInteger()) {
            int heightFeet = Integer.parseInt(heightMajorTextEntry.getText().toString());
            int heightFractionInches = Integer.parseInt(heightMinorTextEntry.getText().toString());
            int heightInches = (heightFeet) * 12 + heightFractionInches;
            int weightPounds = Integer.parseInt(weightTextEntry.getText().toString());
            double bmi = 703.0 * (double) weightPounds / Math.pow((double) heightInches, 2);
            String bmiClass = calculateBmiClass(bmi);
            bmiResultTextView.setText(String.format("%.2f", bmi));
            bmiClassTextView.setText(bmiClass);
        }
    }

    private void onClickMetric() {
        if (checkFieldsNotEmpty() && checkFieldsParseAsInteger()) {
            int heightFractionMeters = Integer.parseInt(heightMajorTextEntry.getText().toString());
            int heightFractionCentimeters = Integer.parseInt(heightMinorTextEntry.getText().toString());
            double heightMeters = (double) heightFractionMeters + ((double) heightFractionCentimeters / 100.0);
            int weightKilos = Integer.parseInt(weightTextEntry.getText().toString());
            double bmi = weightKilos / Math.pow((double) heightMeters, 2);
            String bmiClass = calculateBmiClass(bmi);
            bmiResultTextView.setText(String.format("%.2f", bmi));
            bmiClassTextView.setText(bmiClass);
        }
    }

    public void onClickCalculate(View v) {
        int radioButtonID = unitSelectRadioGroup.getCheckedRadioButtonId();
        if (radioButtonID == R.id.metricRadioButton) {
            onClickMetric();
        } else if (radioButtonID == R.id.imperialRadioButton) {
            onClickImperial();
        }
    }

    public void changeText(View v)
    {
        int radioButtonID = unitSelectRadioGroup.getCheckedRadioButtonId();
        if (radioButtonID == R.id.metricRadioButton) {
            weightTextView.setText(R.string.weight_label_metric);
            heightMinorTextView.setText(R.string.height_minor_label_metric);
            heightMajorTextView.setText(R.string.height_major_label_metric);
        } else if (radioButtonID == R.id.imperialRadioButton) {
            weightTextView.setText(R.string.weight_label_imperial);
            heightMinorTextView.setText(R.string.height_minor_label_imperial);
            heightMajorTextView.setText(R.string.height_major_label_imperial);
        }
    }
}
