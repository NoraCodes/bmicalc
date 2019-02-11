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

import java.util.Locale;

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

    private boolean fieldsAreNotEmpty() {
        return !(heightMinorTextEntry.getText().equals("")
                || heightMajorTextEntry.getText().equals("")
                || weightTextEntry.getText().equals(""));
    }

    private boolean fieldsParseAsIntegers() {
        try {
            Integer.parseInt(heightMajorTextEntry.getText().toString());
            Integer.parseInt(heightMinorTextEntry.getText().toString());
            Integer.parseInt(weightTextEntry.getText().toString());
            return true;
        } catch (NumberFormatException _) {
            return false;
        }
    }

    public void onClickCalculate(View v) {
        if (fieldsAreNotEmpty() && fieldsParseAsIntegers()) {
            int heightMajor = Integer.parseInt(heightMajorTextEntry.getText().toString());
            int heightMinor = Integer.parseInt(heightMinorTextEntry.getText().toString());
            int weight = Integer.parseInt(weightTextEntry.getText().toString());
            int radioButtonID = unitSelectRadioGroup.getCheckedRadioButtonId();

            UnitTranslator unitTranslator;
            if (radioButtonID == R.id.metricRadioButton) {
                unitTranslator = new UnitTranslator().metric(heightMajor, heightMinor, weight);
            } else if (radioButtonID == R.id.imperialRadioButton) {
                unitTranslator = new UnitTranslator().imperial(heightMajor, heightMinor, weight);
            } else {
                return;
            }

            double bmi = unitTranslator.getBmi();
            String bmiClass = unitTranslator.getBmiClass();
            bmiResultTextView.setText(String.format(Locale.ENGLISH, "%.2f", bmi));
            bmiClassTextView.setText(bmiClass);
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
