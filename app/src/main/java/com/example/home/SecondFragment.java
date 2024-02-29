package com.example.home;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.home.databinding.FragmentSecondBinding;

import java.util.Calendar;
import java.util.Date;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    final int max=100;
    String txt;


    String vet[];

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Infla il layout del fragment utilizzando il binding
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    // Chiamato subito dopo che il layout del fragment è stato inflato
    @SuppressLint("SetJavaScriptEnabled")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Aggiorna la TextView con il nuovo valore del SeekBar
                binding.textViewslidebar.setText("Valore: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        vet = new String[max];


        binding.setcalendario.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            long millis = calendar.getTimeInMillis();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            binding.calendarView.setDate(millis);
            binding.textView4.setText("La data di oggi: "+sdf.format(new Date(millis)));

        });

        binding.button2.setOnClickListener(v -> {
            //Calendar calendar = Calendar.getInstance();
            //long millis = calendar.getTimeInMillis();
            //@SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            txt = String.valueOf(binding.multiAutoCompleteTextView.getText());

            //String ok = String.valueOf(sdf.format(new Date(millis)));
            //int i = Integer.parseInt(ok.substring(0, 2));
            vet[binding.seekBar.getProgress()]=txt;


            binding.multiAutoCompleteTextView.setText("");


        });

        binding.button.setOnClickListener(v -> {
            binding.vettore.setText("Nota del giorno "+binding.seekBar.getProgress()+ " : "+vet[binding.seekBar.getProgress()]);
        });


    }

    // Chiamato quando la vista del fragment viene distrutta
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Imposta il binding su null per evitare memory leaks
        binding = null;
    }

}

/*WebView web = binding.web;
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

        web.loadUrl("https://youtube.com/");*/