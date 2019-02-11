package codes.nora.bmicalculator;

import android.widget.EditText;

class UnitTranslator {
    private double bmi;
    private String bmiClass;

    public double getBmi() {
        return bmi;
    }

    public String getBmiClass() {
        return bmiClass;
    }

    public UnitTranslator imperial(int heightMajor, int heightMinor, int weight) {
        int heightInches = (heightMajor) * 12 + heightMinor;
        bmi = BodyMassIndex.calculateBmiImperial(weight, heightInches);
        bmiClass = BodyMassIndex.calculateBmiClass(bmi);
        return this;
    }

    public UnitTranslator metric(int heightMajor, int heightMinor, int weight) {
        int heightCentimeters = heightMajor * 100 + heightMinor;
        bmi = BodyMassIndex.calculateBmiMetric(weight, heightCentimeters);
        bmiClass = BodyMassIndex.calculateBmiClass(bmi);
        return this;
    }
}