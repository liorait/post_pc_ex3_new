package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    /*
    TODO:
    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */
    TextView button0 = findViewById(R.id.button0);
    TextView button1 = findViewById(R.id.button1);
    TextView button2 = findViewById(R.id.button2);
    TextView button3 = findViewById(R.id.button3);
    TextView button4 = findViewById(R.id.button4);
    TextView button5 = findViewById(R.id.button5);
    TextView button6 = findViewById(R.id.button6);
    TextView button7 = findViewById(R.id.button7);
    TextView button8 = findViewById(R.id.button8);
    TextView button9 = findViewById(R.id.button9);
    TextView equalsBtn = findViewById(R.id.buttonEquals);
    TextView plusBtn = findViewById(R.id.buttonPlus);
    TextView minusBtn = findViewById(R.id.buttonMinus);
    TextView clearBtn = findViewById(R.id.buttonClear);
    View spaceView = findViewById(R.id.spaceBelowButton1);
    View backspaceBtn = findViewById(R.id.buttonBackSpace);
    ImageView backSpaceImg = findViewById(R.id.backSpaceImage);
    TextView calcOutput = findViewById(R.id.textViewCalculatorOutput);

    button0.setOnClickListener(v-> {
      //calcOutput.setText(button0.getText().toString());
      calculator.insertDigit(0);
      calcOutput.setText(calculator.output());
    });

    button1.setOnClickListener(v-> {
     // calcOutput.setText(button1.getText().toString());
      calculator.insertDigit(1);
      calcOutput.setText(calculator.output());
    });

    button2.setOnClickListener(v-> {
     // calcOutput.setText(button2.getText().toString());
      calculator.insertDigit(2);
      calcOutput.setText(calculator.output());
    });

    button3.setOnClickListener(v-> {
     // calcOutput.setText(button3.getText().toString());
      calculator.insertDigit(3);
      calcOutput.setText(calculator.output());
    });

    button4.setOnClickListener(v-> {
      //calcOutput.setText(button4.getText().toString());
      calculator.insertDigit(4);
      calcOutput.setText(calculator.output());
    });

    button5.setOnClickListener(v-> {
     // calcOutput.setText(button5.getText().toString());
      calculator.insertDigit(5);
      calcOutput.setText(calculator.output());
    });

    button6.setOnClickListener(v-> {
     // calcOutput.setText(button6.getText().toString());
      calculator.insertDigit(6);
      calcOutput.setText(calculator.output());
    });

    button7.setOnClickListener(v-> {
     // calcOutput.setText(button7.getText().toString());
      calculator.insertDigit(7);
      calcOutput.setText(calculator.output());
    });

    button8.setOnClickListener(v-> {
     // calcOutput.setText(button8.getText().toString());
      calculator.insertDigit(8);
      calcOutput.setText(calculator.output());
    });

    button9.setOnClickListener(v-> {
      //calcOutput.setText(button9.getText().toString());
      calculator.insertDigit(9);
      calcOutput.setText(calculator.output());
    });

    equalsBtn.setOnClickListener(v-> {
      calculator.insertEquals();
      calcOutput.setText(calculator.output());
      //calcOutput.setText(equalsBtn.getText().toString());
    });

    plusBtn.setOnClickListener(v-> {
      calculator.insertPlus();
      calcOutput.setText(calculator.output());
     // calcOutput.setText(plusBtn.getText().toString());
    });

    minusBtn.setOnClickListener(v-> {
      calculator.insertMinus();
      calcOutput.setText(calculator.output());
      //calcOutput.setText(minusBtn.getText().toString());
    });

    clearBtn.setOnClickListener(v-> {
      //calcOutput.setText("");
      calculator.clear();
      calcOutput.setText("0");
     // calcOutput.setText(clearBtn.getText().toString());
    });

    backspaceBtn.setOnClickListener(v-> {
      calculator.deleteLast();
      calcOutput.setText(calculator.output());
      //calcOutput.setText("");
    });

    // default output should be zero
    calcOutput.setText(calculator.output());
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    // todo: save calculator state into the bundle
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
  }
}