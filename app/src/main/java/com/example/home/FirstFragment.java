package com.example.home;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.home.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;




    int conta=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Infla il layout del fragment utilizzando il binding
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    // Chiamato subito dopo che il layout del fragment è stato inflato
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.button.setOnClickListener(v -> {
            conta+=2;
            binding.progressBar.setProgress(conta);
        });


        // Imposta un listener per il click del pulsante
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Naviga al secondo fragment quando il pulsante viene premuto
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
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