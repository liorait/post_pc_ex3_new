package android.exercise.mini.calculator.app;

import android.app.DownloadManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class SimpleCalculatorImpl implements SimpleCalculator {

  // todo: add fields as needed
  private int current_result = 0;
  private String current_output = "";
  private Stack<String> inputValues = new Stack<>();
  String PLUS = "+";
  String MINUS = "-";
  String EQUALS = "=";


  protected boolean isValidOperation(String val)
  {
    return val.equals(PLUS) || val.equals(MINUS) || val.equals(EQUALS);
  }

  @Override
  public String output() {
    // todo: return output based on the current state
    if (inputValues.empty()){
      if (current_result == 0) {
        return "0";
      }
      else{
        return current_output;
      }
    }
    return current_output;
  }

  @Override
  public void insertDigit(int digit) {
    // todo: insert a digit

    // If the digit is valid, adds it on top of the integers stack
    if (digit < 0 || digit > 9) {
      throw new IllegalArgumentException();
    }

     if (inputValues.empty()){
      if (digit != 0) {
        String to_push = Integer.toString(digit);
       inputValues.push(to_push);
       current_output = "";
       current_output = current_output.concat(to_push);
      }
     }
    else {
      String last_value = inputValues.peek();
      if (!isValidOperation(last_value)) {
        if (!last_value.equals("0")) {
          last_value = inputValues.pop();
          int int_last_value = Integer.parseInt(last_value);
          int new_value = int_last_value * 10 + digit;

          inputValues.push(Integer.toString(new_value));
          current_output = current_output.concat(Integer.toString(digit));
        }
      } else {
        inputValues.push(Integer.toString(digit));
        current_output = current_output.concat(Integer.toString(digit));
      }
    }
  }

  @Override
  public void insertPlus() {
    // todo: insert a plus
    if (inputValues.empty()){
      inputValues.push(PLUS);
      current_output = "0";
      current_output = current_output.concat(PLUS);
      return;
    }

    String currentValue = inputValues.peek();
    // to prevent adding ++
    if (currentValue.equals(PLUS) || currentValue.equals(MINUS)) {
      return;
    }
    else{
      // Adds the last operation on top of the values stack
      inputValues.push(PLUS);
      current_output = current_output.concat(PLUS);
    }
  }

  @Override
  public void insertMinus() {

    if (inputValues.empty()){
      inputValues.push(MINUS);
      current_output = "0";
      current_output= current_output.concat(MINUS);
      return;
    }

    String currentValue = inputValues.peek();
    // to prevent adding ++
    if (currentValue.equals(PLUS) || currentValue.equals(MINUS)) {
      return;
    }
    else{
      // Adds the last operation on top of the values stack
      inputValues.push(MINUS);
      current_output = current_output.concat(MINUS);
    }
  }

  @Override
  public void insertEquals() {
    int i = current_output.length() - 1;
    int current_sum = 0;
    int minus_flag = 0;
    String current_opp = "";

    for (int j = 0; j <= i; j++){
      char first = current_output.charAt(j);
      if (first == '+'){
        current_opp = PLUS;
      }
      else if(first == '-'){
        current_opp = MINUS;
      }
      else{
        int digit = Integer.parseInt(first + "");
        if (minus_flag == 1){
          digit = digit * (-1);
          minus_flag = 0;
        }
        int next_digit = 0;

        // first is a digit - get the whole number
        int k = j + 1;
        if (k <= i) {
          char next = current_output.charAt(k); // read next char
          while ((next != '+') && (next != '-') && (k <= i)) {
            next_digit = Integer.parseInt(next + "");
            digit = digit * 10 + next_digit;
            k = k + 1;
            j = j + 1;
            if (k <= i) {
              next = current_output.charAt(k);
            }
          }
        }
        // now we have the whole number
        if (current_opp.equals("")){
          current_sum = digit;
        }
        else if(current_opp.equals(PLUS)){
          current_sum += digit;
        }
        else if(current_opp.equals(MINUS)){
          current_sum -= digit;
        }
        else{
          // error
        }
      }
    }
    current_output = Integer.toString(current_sum);

    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
    if (!inputValues.empty()) {
      String last_value = inputValues.pop();
      if (isValidOperation(last_value)) {
        int last_index = current_output.length() - 1;
        current_output = current_output.substring(0, last_index);
      } // an int
      else{
        int number = Integer.parseInt(last_value);
        number = number / 10;
        String new_value = Integer.toString(number);
        int last_index = current_output.length() - 1 ;
        if (last_index == 0){
          current_output = "";
        }
        else {
          current_output = current_output.substring(0, last_index);
        }
        if (number != 0) {
          inputValues.push(new_value);
        }
      }
    }
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    current_output = "";
    inputValues.clear();
  }

  @Override
  public Serializable saveState() {
    // todo: insert all data to the state, so in the future we can load from this state
    CalculatorState calc_state = new CalculatorState();
    calc_state.string_values = this.current_output;
    calc_state.input_values = new Stack<>();

    while (!this.inputValues.empty()){
      String value = this.inputValues.pop();
      calc_state.input_values.push(value);
    }
    return calc_state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    // todo: use the CalculatorState to load
    this.current_output = casted.string_values;
    this.inputValues = new Stack<>();

    while (!casted.input_values.empty()){
      String value = casted.input_values.pop();
      inputValues.push(value);
    }
  }

  private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    private String string_values;
    private Stack<String> input_values;
  }
}
