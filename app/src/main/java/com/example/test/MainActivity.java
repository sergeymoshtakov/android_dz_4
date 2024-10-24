package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

public class MainActivity extends ComponentActivity {
    private EditText editTextName, editTextAge;
    private SeekBar seekBarSalary;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5;
    private CheckBox checkBoxExperience, checkBoxTeamwork, checkBoxTrips;
    private Button submitButton;
    private TextView resultTextView, textViewSelectedSalary, textViewMinSalary, textViewMaxSalary;

    private final int MIN_SALARY = 1000;
    private final int MAX_SALARY = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ініціалізація елементів
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        seekBarSalary = findViewById(R.id.seekBarSalary);
        textViewSelectedSalary = findViewById(R.id.textViewSelectedSalary);
        textViewMinSalary = findViewById(R.id.textViewMinSalary);
        textViewMaxSalary = findViewById(R.id.textViewMaxSalary);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);
        radioGroup4 = findViewById(R.id.radioGroup4);
        radioGroup5 = findViewById(R.id.radioGroup5);
        checkBoxExperience = findViewById(R.id.checkBoxExperience);
        checkBoxTeamwork = findViewById(R.id.checkBoxTeamwork);
        checkBoxTrips = findViewById(R.id.checkBoxTrips);
        submitButton = findViewById(R.id.submitButton);
        resultTextView = findViewById(R.id.resultTextView);

        // Встановлюємо мінімум і максимум для SeekBar
        seekBarSalary.setMax(MAX_SALARY - MIN_SALARY); // SeekBar починається з 0

        // Виводимо межі зарплатні
        textViewMinSalary.setText(MIN_SALARY + " USD");
        textViewMaxSalary.setText(MAX_SALARY + " USD");

        // Слухач для зміни значення SeekBar
        seekBarSalary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int selectedSalary = progress + MIN_SALARY;
                textViewSelectedSalary.setText("Обрана зарплата: " + selectedSalary + " USD");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Нічого не робимо
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Нічого не робимо
            }
        });

        submitButton.setOnClickListener(v -> {
            if (validateForm()) {
                int score = calculateScore();
                if (score >= 10) {
                    resultTextView.setText("Вітаємо! Ви пройшли тест.\nКонтакти компанії: company@gmail.com");
                } else {
                    resultTextView.setText("На жаль, Ви не набрали достатньо балів.");
                }
                resultTextView.setVisibility(View.VISIBLE);
            }
        });
    }

    private boolean validateForm() {
        String name = editTextName.getText().toString().trim();
        String ageStr = editTextAge.getText().toString().trim();
        int salary = seekBarSalary.getProgress() + MIN_SALARY;  // Отримуємо значення зарплати з урахуванням мінімуму

        // Валідація ПІБ
        if (name.isEmpty()) {
            editTextName.setError("Введіть ПІБ");
            return false;
        }

        // Валідація віку
        if (ageStr.isEmpty()) {
            editTextAge.setError("Введіть вік");
            return false;
        }
        int age = Integer.parseInt(ageStr);
        if (age < 21 || age > 40) {
            editTextAge.setError("Вік має бути від 21 до 40 років");
            return false;
        }

        // Валідація бажаної зарплати
        if (salary < MIN_SALARY || salary > MAX_SALARY) {
            String salaryError = "Зарплата має бути між " + MIN_SALARY + " та " + MAX_SALARY + " USD";
            resultTextView.setText(salaryError);
            resultTextView.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }

    private int calculateScore() {
        int score = 0;

        // Перевірка правильних відповідей на питання
        if (radioGroup1.getCheckedRadioButtonId() == R.id.radioButton1_1) {
            score += 2;
        }
        if (radioGroup2.getCheckedRadioButtonId() == R.id.radioButton2_1) {
            score += 2;
        }
        if (radioGroup3.getCheckedRadioButtonId() == R.id.radioButton3_1) {
            score += 2;
        }
        if (radioGroup4.getCheckedRadioButtonId() == R.id.radioButton4_1) {
            score += 2;
        }
        if (radioGroup5.getCheckedRadioButtonId() == R.id.radioButton5_1) {
            score += 2;
        }

        // Перевірка додаткових умов
        if (checkBoxExperience.isChecked()) {
            score += 2;
        }
        if (checkBoxTeamwork.isChecked()) {
            score += 1;
        }
        if (checkBoxTrips.isChecked()) {
            score += 1;
        }

        return score;
    }
}