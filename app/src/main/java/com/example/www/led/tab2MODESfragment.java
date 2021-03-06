package com.example.www.led;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rtugeek.android.colorseekbar.ColorSeekBar;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

public class tab2MODESfragment extends Fragment {
    private static final String TAG = "tab2MODESfragment";

    private String stringData;
    private int mode = 1;

    private EditText redBox;
    private EditText greenBox;
    private EditText blueBox;
    private EditText alphaBox;

    private ColorSeekBar colorSeekBar;

    private Button sendDataButton;
    private Button setColorButton;
    private Button mode1Button;
    private Button mode2Button;
    private Button mode3Button;
    private Button mode4Button;
    private Button mode5Button;
    private Button mode6Button;
    private Button mode7Button;
    private Button mode8Button;
    private Button mode9Button;
    private Button mode10Button;
    private Button mode11Button;
    private Button mode12Button;

    int valRed;
    int valGreen;
    int valBlue;
    int valAlpha;
    int valColor;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_modes_fragment, container, false);

        redBox = view.findViewById(R.id.text_box_r);
        greenBox = view.findViewById(R.id.text_box_g);
        blueBox = view.findViewById(R.id.text_box_b);
        alphaBox = view.findViewById(R.id.text_box_alpha);
        redBox.setEnabled(false);
        greenBox.setEnabled(false);
        blueBox.setEnabled(false);
        alphaBox.setEnabled(false);



        setColorButton = view.findViewById(R.id.button_setColor);
        sendDataButton = view.findViewById(R.id.button_sendData);

        mode1Button = view.findViewById(R.id.button_mode1);
        mode2Button = view.findViewById(R.id.button_mode2);
        mode3Button = view.findViewById(R.id.button_mode3);
        mode4Button = view.findViewById(R.id.button_mode4);
        mode5Button = view.findViewById(R.id.button_mode5);
        mode6Button = view.findViewById(R.id.button_mode6);
        mode7Button = view.findViewById(R.id.button_mode7);
        mode8Button = view.findViewById(R.id.button_mode8);
        mode9Button = view.findViewById(R.id.button_mode9);
        mode10Button = view.findViewById(R.id.button_mode10);
        mode11Button = view.findViewById(R.id.button_mode11);
        mode12Button = view.findViewById(R.id.button_mode12);


        colorSeekBar = view.findViewById(R.id.colorPickerBar);

        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int i, int i1, int i2) {

                valRed = (i2 >> 16) & 0xFF;
                valGreen = (i2 >> 8) & 0xFF;
                valBlue = i2 & 0xFF;
                valAlpha = colorSeekBar.getAlphaValue();

                redBox.setText(String.format(Locale.getDefault(), "%d", valRed));
                greenBox.setText(String.format(Locale.getDefault(), "%d", valGreen));
                blueBox.setText(String.format(Locale.getDefault(), "%d", valBlue));
                alphaBox.setText(String.format(Locale.getDefault(), "%d", valAlpha));

                valColor = valAlpha;
                valColor = (valColor << 8) + Integer.parseInt(redBox.getText().toString());
                valColor = (valColor << 8) + Integer.parseInt(greenBox.getText().toString());
                valColor = (valColor << 8) + Integer.parseInt(blueBox.getText().toString());

            }
        });

        setColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (redBox.getText().toString().equals("")) {
                    redBox.setText("0");
                }
                if (greenBox.getText().toString().equals("")) {
                    greenBox.setText("0");
                }
                if (blueBox.getText().toString().equals("")) {
                    blueBox.setText("0");
                }

                valColor = Integer.parseInt(alphaBox.getText().toString());
                valColor = (valColor << 8) + Integer.parseInt(redBox.getText().toString());
                valColor = (valColor << 8) + Integer.parseInt(greenBox.getText().toString());
                valColor = (valColor << 8) + Integer.parseInt(blueBox.getText().toString());

                colorSeekBar.setColor(valColor);
            }
        });

        mode1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 1;
            }
        });
        mode2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 2;
            }
        });
        mode3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 3;
            }
        });
        mode4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 4;
            }
        });
        mode5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 5;
            }
        });
        mode6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 6;
            }
        });
        mode7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 7;
            }
        });
        mode8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 8;
            }
        });
        mode9Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 9;
            }
        });
        mode10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 10;
            }
        });
        mode11Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 11;
            }
        });
        mode12Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 12;
            }
        });

        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringData = "[" + Integer.toString(mode) + "]" + "{" + Integer.toString(valColor) + "}";
                Toast.makeText(requireContext(), stringData, Toast.LENGTH_LONG).show();
                EventBus.getDefault().post(new notifyEvent(stringData));
            }
        });

        return view;
    }
}
