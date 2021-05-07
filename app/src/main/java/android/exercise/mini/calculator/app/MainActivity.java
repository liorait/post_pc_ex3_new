package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

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
      calculator.insertDigit(0);
      calcOutput.setText(calculator.output());
    });

    button1.setOnClickListener(v-> {
      calculator.insertDigit(1);
      calcOutput.setText(calculator.output());
    });

    button2.setOnClickListener(v-> {
      calculator.insertDigit(2);
      calcOutput.setText(calculator.output());
    });

    button3.setOnClickListener(v-> {
      calculator.insertDigit(3);
      calcOutput.setText(calculator.output());
    });

    button4.setOnClickListener(v-> {
      calculator.insertDigit(4);
      calcOutput.setText(calculator.output());
    });

    button5.setOnClickListener(v-> {
      calculator.insertDigit(5);
      calcOutput.setText(calculator.output());
    });

    button6.setOnClickListener(v-> {
      calculator.insertDigit(6);
      calcOutput.setText(calculator.output());
    });

    button7.setOnClickListener(v-> {
      calculator.insertDigit(7);
      calcOutput.setText(calculator.output());
    });

    button8.setOnClickListener(v-> {
      calculator.insertDigit(8);
      calcOutput.setText(calculator.output());
    });

    button9.setOnClickListener(v-> {
      calculator.insertDigit(9);
      calcOutput.setText(calculator.output());
    });

    equalsBtn.setOnClickListener(v-> {
      calculator.insertEquals();
      calcOutput.setText(calculator.output());
    });

    plusBtn.setOnClickListener(v-> {
      calculator.insertPlus();
      calcOutput.setText(calculator.output());
    });

    minusBtn.setOnClickListener(v-> {
      calculator.insertMinus();
      calcOutput.setText(calculator.output());
    });

    clearBtn.setOnClickListener(v-> {
      calculator.clear();
      calcOutput.setText("0");
    });

    backspaceBtn.setOnClickListener(v-> {
      calculator.deleteLast();
      calcOutput.setText(calculator.output());
    });

    if (savedInstanceState != null){
      Serializable saved_output = savedInstanceState.getSerializable("saved_state");
      SimpleCalculatorImpl typo = new SimpleCalculatorImpl();
      typo.loadState(saved_output);
      String prev_output = typo.output();
      calcOutput.setText(prev_output);
    }
    else{
      // default output should be zero
      calcOutput.setText(calculator.output());
    }
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("saved_state", calculator.saveState());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    Serializable saved_output = savedInstanceState.getSerializable("saved_state");
    SimpleCalculatorImpl typo = new SimpleCalculatorImpl();
    typo.loadState(saved_output);
    String prev_output = typo.output();
    TextView calcOutput = findViewById(R.id.textViewCalculatorOutput);
    calcOutput.setText(prev_output);
  }
}