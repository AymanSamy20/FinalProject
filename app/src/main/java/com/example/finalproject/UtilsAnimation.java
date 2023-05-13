package com.example.finalproject;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.button.MaterialButton;

public class UtilsAnimation {

    public static void expandedLoginViews(ViewGroup parentView, View expandedView, View expandedView2,
                                          View expandedView3, View expandedView4, boolean isCustomer, boolean expand) {
        ChangeBounds animation = new ChangeBounds();
        animation.setDuration(200);
        animation.setInterpolator(new LinearOutSlowInInterpolator());
        TransitionManager.beginDelayedTransition(parentView, animation);
        expandedView.setVisibility(expand ? View.VISIBLE : View.GONE);
        if (!isCustomer) {
            expandedView2.setVisibility(expand ? View.VISIBLE : View.GONE);
        }
        expandedView3.setVisibility(expand ?View.VISIBLE : View.GONE);
        expandedView4.setVisibility(expand ? View.GONE : View.VISIBLE);
    }

    public static void flipLoginGroup(CheckBox checkBoxRememberMe, TextView tvNewMember,
                                      TextView tvSignUp, MaterialButton buLogin, LinearLayout loginGroup, boolean isLogin) {
        YoYo.with(Techniques.FlipOutX)
                .duration(250)
                .onEnd(animator -> {
                    // اول ما يختفي ببدل النصوص وبرجع بظهره
                    checkBoxRememberMe.setText(isLogin ? R.string.remember_me : R.string.read_and_accept);
                    tvNewMember.setText(isLogin ? R.string.new_member : R.string.have_account);
                    tvSignUp.setText(isLogin ? R.string.sign_up_under_line : R.string.sign_in_under_line);
                    buLogin.setText(isLogin ? R.string.login : R.string.sign_up);
                    YoYo.with(Techniques.FlipInX)
                            .duration(250)
                            .playOn(loginGroup);
                })
                .playOn(loginGroup);
    }

    public static void expandedCardLogin(View cardLogin, boolean expanded, int cardLoginHeight, int relativeImageAppHeight) {
        // النقطة يلي رح يبدأ الحركة من عندها
        // اذا كان متمدد قبل هيك رح يبدأ من عند النقطة يلي انتها
        // relativeImageAppHeight * 0.3  رح يغطي ثلث مساحة الصورة تقريبا
        int startHeight = expanded ? cardLoginHeight : (int) (cardLoginHeight + (relativeImageAppHeight * 0.3));
        ConstraintLayout.LayoutParams layoutParamsCard = (ConstraintLayout.LayoutParams) cardLogin.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt((int) (relativeImageAppHeight * 0.3));
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.setDuration(500);
        animator.addUpdateListener(valueAnimator -> {
            int animValue = ((int) valueAnimator.getAnimatedValue());
            // اذا كان متمدد قبل هيك رح يبدا ينقص من طول العنصر
            layoutParamsCard.height = startHeight + (expanded ? animValue : -animValue);
            cardLogin.setLayoutParams(layoutParamsCard);
        });
        animator.start();
    }

    public static float lastPoint = 0f;

    public static void translateLineIndicator(View view, int position) {
        float endPoint = 0f;

        if (position > 1) {
            int screenWidth = UtilsDraw.getScreenWidth();
            endPoint = (float) ((screenWidth * 0.25) * (position - 1));
        }

        TranslateAnimation translate = new TranslateAnimation(lastPoint, endPoint, 0, 0);
        translate.setDuration(300);
        translate.setFillAfter(true);
        translate.setInterpolator(new LinearInterpolator());
        view.startAnimation(translate);
        // علشان يبدا حركة من اخر مكان راحلو
        lastPoint = endPoint;
    }

    public static void alphaAnimationView(View view, boolean hide) {
        float fromAlpha = 1f;
        float toAlpha = 0f;
        if (!hide) {
            fromAlpha = 0f;
            toAlpha = 1f;
        }

        AlphaAnimation animation = new AlphaAnimation(fromAlpha, toAlpha);
        animation.setInterpolator(new LinearOutSlowInInterpolator());
        animation.setDuration(500);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

}
