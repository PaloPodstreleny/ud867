package com.udacity.gradle.builditbigger;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class EspressoTestUtil {

    public static void disableProgressBarAnimations(ActivityTestRule<SimpleFragmentActivityTest> testRule) {
        testRule.getActivity().getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
                traverseViews(v);
            }
        }, true);

    }

    private static void traverseViews(View view) {
        if (view instanceof ViewGroup) {
            traverseViewGroup((ViewGroup) view);
        } else if (view instanceof ProgressBar) {
            disableProgressBarAnimation((ProgressBar) view);
        }
    }

    private static void traverseViewGroup(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        if (count != 0) {
            while (count > 0) {
                traverseViews(viewGroup.getChildAt(--count));
            }

        }
    }

    private static void disableProgressBarAnimation(ProgressBar progressBar) {
        progressBar.setIndeterminateDrawable(new ColorDrawable(Color.BLACK));
    }

}