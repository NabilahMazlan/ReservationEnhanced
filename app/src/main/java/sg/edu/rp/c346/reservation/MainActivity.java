package sg.edu.rp.c346.reservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnReserve, reset;
    TextView name, phoneNum, pax;
    EditText etName, etPhoneNum, etPax, etDay, etTime;
    CheckBox smoking;
    String checked = "";

    int year, month, day, hour, min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReserve = findViewById(R.id.btReserve);
        reset = findViewById(R.id.btReset);
        name = findViewById(R.id.tvName);
        phoneNum = findViewById(R.id.tvTelephone);
        pax = findViewById(R.id.tvPax);
        etName = findViewById(R.id.etName);
        etPhoneNum = findViewById(R.id.etTelephone);
        etPax = findViewById(R.id.etPax);
        smoking = findViewById(R.id.checkBoxSmoking);
        etDay = findViewById(R.id.editDay);
        etTime = findViewById(R.id.editTime);



        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        etDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearOfMonth, int monthOfYear, int dayOfMonth) {
                        etDay.setText("Date: " + dayOfMonth + "/" + (monthOfYear+1) + "/" + yearOfMonth);
                    }
                };

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, year, month, day);
                myDateDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int min) {
                        etTime.setText("Time: "+hour+ ":"+min);
                        }
                    };
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener, hour, min, true);
                myTimeDialog.show();
            }
        });


        btnReserve.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.reserve, null);

                final String getName = etName.getText().toString().trim();
                final String getNum = etPhoneNum.getText().toString().trim();
                final String getPax = etPax.getText().toString().trim();
                final String getTime = etTime.getText().toString().trim();
                final String getDay = etDay.getText().toString().trim();
                etPhoneNum.requestFocus();
                etPax.requestFocus();
                if (smoking.isChecked()){
                    checked = "Yes";
                }else{
                    checked = "No";

                }
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Confirm Your Order");
                String msg = "New Reservation\nName: "+ getName+"\nTelephone: "+ getNum+"\nSmoking: "+checked+"\nSize: "+getPax
                        + "\nDate: "+ getDay+"\nTime: "+getTime;
                myBuilder.setMessage(msg);
                myBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Reservation confirmed", Toast.LENGTH_LONG ).show();
                    }
                });

                myBuilder.setNeutralButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();


            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            etName.setText(null);
            etPax.setText(null);
            etPhoneNum.setText(null);
            etTime.setText(null);
            etDay.setText(null);
            smoking.setChecked(false);
            }
        });




    }
}
