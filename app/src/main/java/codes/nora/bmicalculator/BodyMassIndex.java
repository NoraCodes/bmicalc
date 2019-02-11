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

class BodyMassIndex {
    public static String calculateBmiClass(double bmi) {
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

    public static double calculateBmiImperial(int weightPounds, int heightInches) {
        return 703 * weightPounds / Math.pow((double) heightInches, 2);
    }

    public static double calculateBmiMetric(int weightKilos, int heightCentimeters) {
        return weightKilos / Math.pow(heightCentimeters / 100.0, 2);
    }
}
