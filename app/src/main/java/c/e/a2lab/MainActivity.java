package c.e.a2lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    public EditText textName;
    TextView textWithName;
    Button clearableButton, sayHello;
    public String name;

    @Override
    protected void onCreate(Bundle mSavedInstanceState)
    {
        super.onCreate(mSavedInstanceState);
        setContentView(R.layout.activity_main);
        textName = findViewById(R.id.text_name);
        textWithName = findViewById(R.id.text_with_name);
        clearableButton = findViewById(R.id.clearable_button);
        sayHello = findViewById(R.id.say_hello);

        clearableButton.setOnClickListener(this);
        sayHello.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.clearable_button:
                textName.getText().clear();
                break;
            case R.id.say_hello:
                textWithName.setText("Hello, " + textName.getText().toString());
                textName.getText().clear();
        }
    }
}