package c.e.a2lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText text_name;
    TextView text_with_name;
    Button clearable_button, say_hello;
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_name = findViewById(R.id.text_name);
        text_with_name = findViewById(R.id.text_with_name);
        clearable_button= findViewById(R.id.clearable_button);
        say_hello = findViewById(R.id.say_hello);

        clearable_button.setOnClickListener(this);
        say_hello.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.clearable_button:
                text_name.getText().clear();
                break;
            case  R.id.say_hello:
                text_with_name.setText("Hello, " + text_name.getText().toString());
                text_name.getText().clear();
        }
    }

    public EditText getText_name() {
        return text_name;
    }
}
