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


    private EditText redBox;
    private EditText greenBox;
    private EditText blueBox;
    private EditText alphaBox;

    private ColorSeekBar colorSeekBar;

    private Button sendDataButton;
    private Button setColorButton;

    int valRed;
    int valGreen;
    int valBlue;
    int valAlpha;
    int valColor;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_modes_fragment, container, false);

        redBox = (EditText) view.findViewById(R.id.text_box_r);
        greenBox = (EditText) view.findViewById(R.id.text_box_g);
        blueBox = (EditText) view.findViewById(R.id.text_box_b);
        alphaBox = (EditText) view.findViewById(R.id.text_box_alpha);

        setColorButton = (Button) view.findViewById(R.id.button_setColor);
        sendDataButton = (Button) view.findViewById(R.id.button_sendData);

        colorSeekBar = (ColorSeekBar) view.findViewById(R.id.colorPickerBar);

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

        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), Integer.toString(valColor), Toast.LENGTH_LONG).show();
                EventBus.getDefault().post(new notifyEvent(Integer.toString(valColor)));
            }
        });

        return view;
    }
}