package kr.ac.kopo.a2501mid_bmi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText nameInput, weightInput, heightInput;
    Button btn;
    TextView textResult;
    ImageView imgv;

    int caseBMI = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameInput = findViewById(R.id.nameInput);
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        btn = findViewById(R.id.btn);
        imgv = findViewById(R.id.imgv);
        textResult = findViewById(R.id.textResult);

        btn.setOnClickListener(btnClilkListener);
    }

    View.OnClickListener btnClilkListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String numWStr = weightInput.getText().toString();
            String numHStr = heightInput.getText().toString();
            String nameStr = nameInput.getText().toString();


            if (numWStr.equals("") || numHStr.equals("") || nameStr.equals("") ) {
                Toast.makeText(getApplicationContext(), "정보가 입력 되지 않았습니다.", Toast.LENGTH_SHORT).show();
                if (numWStr.equals("")) {
                    weightInput.setFocusable(true);
                } else if (numHStr.equals("")) {
                    heightInput.setFocusable(true);
                } else {
                    nameInput.setFocusable(true);
                }
            } else {
                double numW = Double.parseDouble(numWStr);
                double numH = Double.parseDouble(numHStr);

                double resultBMI = numW / Math.pow(numH/100, 2);

                if (resultBMI < 18.5)
                    caseBMI = 1;
                else if (resultBMI < 24.9)
                    caseBMI = 2;
                else if (resultBMI < 29.9)
                    caseBMI = 3;
                else if (resultBMI < 34.9)
                    caseBMI = 4;
                else
                    caseBMI = 5;

                String result = "";
                switch (caseBMI) {
                    case 1:
                        result = "저체중";
                        imgv.setImageResource(R.drawable.bmi1);
                        break;
                    case 2:
                        result = "표준체중";
                        imgv.setImageResource(R.drawable.bmi2);
                        break;
                    case 3:
                        result = "과체중";
                        imgv.setImageResource(R.drawable.bmi3);
                        break;
                    case 4:
                        result = "비만";
                        imgv.setImageResource(R.drawable.bmi4);
                        break;
                    case 5:
                        result = "고도 비만";
                        imgv.setImageResource(R.drawable.bmi5);
                        break;
                }

                String bmi = String.format("%.1f", resultBMI);

                textResult.setText(String.format("%s 님은 현재 %s\n(BMI : %s) 입니다.\n(체중 %s kg, 키 %s cm 기준)", nameStr, result, bmi, numWStr, numHStr));
            }
        }
    };
}
