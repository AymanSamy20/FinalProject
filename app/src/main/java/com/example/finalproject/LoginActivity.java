package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.finalproject.databinding.ActivityLoginBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements ToggleLoginListener {
    ActivityLoginBinding binding;
    int relativeImageAppHeight;
    int cardLoginHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.relativeImageApp.post(() -> {
            // طول حاوية الصورة
            relativeImageAppHeight = binding.relativeImageApp.getHeight();
            // بخلي طول الكارد بطول الشاشة كلها - طول الحاوية
            cardLoginHeight = UtilsDraw.getFullScreenHeight(LoginActivity.this) - relativeImageAppHeight;
            ViewGroup.LayoutParams params = binding.cardLogin.getLayoutParams();
            params.height = cardLoginHeight;
            binding.cardLogin.setLayoutParams(params);
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new LoginFragment(false));
        arrayList.add(new LoginFragment(true));


        MyViewpagerAdapter viewpagerAdapter = new MyViewpagerAdapter(fragmentManager, getLifecycle(), arrayList);
        binding.ViewPager2.setAdapter(viewpagerAdapter);

        new TabLayoutMediator(binding.TabLayout, binding.ViewPager2, (tab, position) -> {
            if (position == 0) {
                tab.setText("Service provider");
            } else {
                tab.setText("Customer");
            }
        }).attach();
    }
    @Override
    public void onToggleLogin(boolean isLogin) {
        UtilsAnimation.alphaAnimationView(binding.imageApp, !isLogin);
        UtilsAnimation.expandedCardLogin(binding.cardLogin, !isLogin, cardLoginHeight, relativeImageAppHeight);
        // بظهر الزر علشان بكون مخفي في البداية
        binding.buBack.setVisibility(View.VISIBLE);
        UtilsAnimation.alphaAnimationView(binding.buBack, isLogin);
    }


}