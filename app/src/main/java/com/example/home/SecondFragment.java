package com.example.home;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.home.databinding.FragmentSecondBinding;


import java.io.IOException;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.home.databinding.FragmentSecondBinding;

import java.util.Calendar;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        String serverResponse = "";
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Aggiorna la TextView con il nuovo valore del SeekBar
                binding.textViewslidebar.setText("Giorno: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        vet = new String[max];
        binding.seekBar.setMax(31);


        binding.setcalendario.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            long millis = calendar.getTimeInMillis();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            binding.calendarView.setDate(millis);
            binding.textView4.setText("La data di oggi: "+sdf.format(new Date(millis)));

        });


        binding.buttonrichiedi.setOnClickListener(v -> {
            String url = "http://192.168.1.180:8080/chatgpt?prompt=";
            url = url + String.valueOf(binding.textView77.getText());
            binding.textView77.setText("");
            makeHttpRequest(url);
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
    private void makeHttpRequest(String url) {
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();
                String serverResponse = response.body() != null ? response.body().string() : "";

                requireActivity().runOnUiThread(() -> binding.textViewrequest.setText(serverResponse));
            } catch (IOException e) {
                requireActivity().runOnUiThread(() -> binding.textViewrequest.setText("Errore: " + e.getMessage()));
            }
        }).start();
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