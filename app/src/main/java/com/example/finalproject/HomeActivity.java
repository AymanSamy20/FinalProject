package com.example.finalproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.finalproject.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    private final int ID_HOME = 1;
    private final int ID_MASSEG = 2;
    private final int ID_NOU = 3;
    private final int ID_MOER = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.image_app));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.receipt));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.porfile));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.more));



        binding.bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment = null;
                switch (item.getId()){
                    case 1 :
                        fragment = new BlankFragment();
                    case 2:
                        fragment =new BlankFragment2();
                    case 3:
                        fragment= new BlankFragment3();
                    case 4:
                        fragment =new BlankFragment4();
                }
                loadFragment(fragment);

                }

        });
        binding.bottomNavigation.show(1,true);

        binding.bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(HomeActivity.this, "you clicked"+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        binding.bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(HomeActivity.this, "You reselected"+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameActivityHome,fragment)
                .commit();
    }


}