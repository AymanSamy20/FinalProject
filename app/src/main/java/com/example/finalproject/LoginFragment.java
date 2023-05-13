package com.example.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.finalproject.databinding.FragmentLoginBinding;

import java.util.ArrayList;
import java.util.Objects;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    private boolean isCustomer;
    private boolean isLogin = true;
    ToggleLoginListener listener;


    public LoginFragment() {
    }

    public LoginFragment(boolean isCustomer) {
        this.isCustomer = isCustomer;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater(), container, false);


        ArrayList<String> services = new ArrayList<>();
        services.add("نجار");
        services.add("حداد");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.item_spinner_service, services);
        binding.spinnerServices.setAdapter(adapter);



        return binding.getRoot();
    }






}