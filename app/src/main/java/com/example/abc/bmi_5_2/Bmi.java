package com.example.abc.bmi_5_2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Bmi extends AppCompatActivity implements View.OnClickListener {

    private static final int MENU_ABOUT = Menu.FIRST;
    private static final int MENU_QUIT = Menu.FIRST + 1;
    AlertDialog dialog;
    String number = "";
    View.OnClickListener dialogListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = ((Button) v).getId();
            if (id == R.id.buttonClear)
                number = "";
            else if (id == R.id.buttonEnter) {
                dialog.dismiss();
            } else {
                number += ((Button) v).getText();
                Toast.makeText(Bmi.this, number, Toast.LENGTH_SHORT).show();
            }
        }
    };
    private EditText field_height;
    private EditText field_weight;
    private Button submit;
    private TextView result;
    private TextView suggest;
    private View rootView;
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonEnter, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        findViews();
        setListeners();
    }

    private void findViews() {
        submit = findViewById(R.id.submit);
        field_height = findViewById(R.id.field_height);
        field_weight = findViewById(R.id.field_weight);
        result = findViewById(R.id.result);
        suggest = findViewById(R.id.suggest);
        findDialogViews();
    }

    private void findDialogViews() {
        rootView = getLayoutInflater().inflate(R.layout.dial_keypad, null);
        button0 = rootView.findViewById(R.id.button0);
        button1 = rootView.findViewById(R.id.button1);
        button2 = rootView.findViewById(R.id.button2);
        button3 = rootView.findViewById(R.id.button3);
        button4 = rootView.findViewById(R.id.button4);
        button5 = rootView.findViewById(R.id.button5);
        button6 = rootView.findViewById(R.id.button6);
        button7 = rootView.findViewById(R.id.button7);
        button8 = rootView.findViewById(R.id.button8);
        button9 = rootView.findViewById(R.id.button9);
        buttonEnter = rootView.findViewById(R.id.buttonEnter);
        buttonClear = rootView.findViewById(R.id.buttonClear);
    }

    private void setListeners() {
        submit.setOnClickListener(this);
        button0.setOnClickListener(dialogListener);
        button1.setOnClickListener(dialogListener);
        button2.setOnClickListener(dialogListener);
        button3.setOnClickListener(dialogListener);
        button4.setOnClickListener(dialogListener);
        button5.setOnClickListener(dialogListener);
        button6.setOnClickListener(dialogListener);
        button7.setOnClickListener(dialogListener);
        button8.setOnClickListener(dialogListener);
        button9.setOnClickListener(dialogListener);
        buttonEnter.setOnClickListener(dialogListener);
        buttonClear.setOnClickListener(dialogListener);
    }

    @Override
    public void onClick(View v) {
        DecimalFormat df = new DecimalFormat("0.00");

        double height = Double.parseDouble(field_height.getText().toString()) / 100;
        double weight = Double.parseDouble(field_weight.getText().toString());
        double BMI = weight / (height * height);

        result.setText("你的BMI值 = " + df.format(BMI));

        if (BMI > 25)
            suggest.setText(R.string.advice_heavy);
        else if (BMI < 20)
            suggest.setText(R.string.advice_light);
        else
            suggest.setText(R.string.advice_average);

        openOptionsDialog();
    }

    void openOptionsDialog() {
        number = "";
        findDialogViews();
        setListeners();
        AlertDialog.Builder builder = new AlertDialog.Builder(Bmi.this);
        builder.setTitle("我的撥號鍵盤");
        builder.setView(rootView);
        dialog = builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, MENU_ABOUT, 0, "About");
        menu.add(0, MENU_QUIT, 0, "Exit");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case MENU_ABOUT:
                openOptionsDialog();
                break;

            case MENU_QUIT:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}