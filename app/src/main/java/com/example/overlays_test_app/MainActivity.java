package com.example.overlays_test_app;

import android.app.ActionBar;
import android.os.Build.VERSION_CODES;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.WindowMetrics;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import androidx.core.graphics.Insets;
// import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // requestWindowFeature(WindowCompat.FEATURE_ACTION_MODE_OVERLAY);
    // WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
    // setContentView(R.layout.activity_main);
  }

  @RequiresApi(api = VERSION_CODES.R)
  @Override
  protected void onStart() {
    super.onStart();
    Window window = getWindow();
    View view = window.getDecorView();
    // View view = WindowCompat.requireViewById(window, android.R.id.content);
    // ActionBar actionBar = getActionBar();

    WindowInsetsControllerCompat windowInsetsControllerCompat = WindowCompat.getInsetsController(window, view);
        //new WindowInsetsControllerCompat(window, view);
    int DEFAULT_SYSTEM_UI =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

    String test_case = "LEAN_BACK";

    switch(test_case) {
      case "LEAN_BACK": //TODO: fix action bar :(
      /* Everything (status, app, nav bars) hidden but upon swipe down, swipe up, everything appears and stays */
        int enabledOverlays =
        // [THE PRIMERS] Set the content to appear under the system bars so that the
        // content doesn't resize when the system bars hide and show. (https://developer.android.com/training/system-ui/immersive)
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
             View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
             // below: justifies app content behind status bar, so that when it hides, the content does not have to resize
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
       // [THE JOB] Hide the nav bar and status bar
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hides the navigation bar on the bottom
            | View.SYSTEM_UI_FLAG_FULLSCREEN; // makes the content full screen (no top status bar nor title bar)

        // view.setSystemUiVisibility(enabledOverlays);
       //
        WindowCompat.setDecorFitsSystemWindows(window, false);
        windowInsetsControllerCompat.setSystemBarsBehavior(
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars());
       //  LayoutParams lp = (LayoutParams) view.getLayoutParams();
       //  lp.setFitInsetsTypes(WindowInsets.Type.systemBars());

        // windowInsetsControllerCompat.hide(WindowInsets.Type.navigationBars() | WindowInsets.Type.statusBars());
        //
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
          Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
          // Apply the insets as a margin to the view. Here the system is setting
          // only the bottom, left, and right dimensions, but apply whichever insets are
          // appropriate to your layout. You can also update the view padding
          // if that's more appropriate.
          //  windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars());

          LayoutParams lp = (LayoutParams) v.getLayoutParams();
          // lp.setFitInsetsTypes(WindowInsets.Type.systemBars());

          Log.e("INSETS", insets.toString());
          Log.e("LAYOUT_PARAMS", String.valueOf(lp.verticalMargin));
          // mlp.leftMargin = insets.left;
          // lp.horizontalMargin = insets.left;
          // mlp.bottomMargin = insets.bottom;
          lp.verticalMargin = 100;
          // mlp.rightMargin = insets.right;
          v.setLayoutParams(lp);
          Log.e("LAYOUT_PARAMS", String.valueOf(lp.verticalMargin));

          // v.setLayoutParams(lp);

          // windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.statusBars());


          // Return CONSUMED if you don't want want the window insets to keep being
          // passed down to descendant views.
          return WindowInsetsCompat.CONSUMED;
        });
        // windowInsetsControllerCompat.hide(WindowInsets.Type.systemBars());


        break;
      case "IMMERSIVE": //TODO: fix action bar :(
        /* Everything (status, app, nav bars) hidden but upon swipe down, swipe up, everything appears and stays */
        enabledOverlays =
            // the first line is the only difference between IMMERSIVE and LEAN_BACK
            View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
         view.setSystemUiVisibility(enabledOverlays);
      // window.setDecorFitsSystemWindows(false);
      // windowInsetsControllerCompat.hide(WindowInsets.Type.statusBars());
      // windowInsetsControllerCompat.hide(WindowInsets.Type.navigationBars());
      //   windowInsetsControllerCompat.setSystemBarsBehavior(
      //       WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE); //verified this
      //   windowInsetsControllerCompat.setSystemBarsBehavior(
      //       WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_TOUCH);


        break;
      case "STICKY_IMMERSIVE": //TODO: fix action bar :(
        enabledOverlays =
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN;

        view.setSystemUiVisibility(enabledOverlays);

        // windowInsetsControllerCompat.setSystemBarsBehavior(
        //     WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE); //verified
        // window.setDecorFitsSystemWindows(false);
        // windowInsetsControllerCompat.hide(WindowInsets.Type.statusBars());
        // windowInsetsControllerCompat.hide(WindowInsets.Type.navigationBars());
        break;
      case "EDGE_TO_EDGE": //TODO: verify accuracy :)
        /* Everything present and everything is part of the app. Navigation bar in black though. Status bar same color as app bar. */
        // enabledOverlays =
        //     View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        //         | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        //         | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        // view.setSystemUiVisibility(enabledOverlays);

        WindowCompat.setDecorFitsSystemWindows(window, false);
        //
        // ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
        //   Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        //   // Apply the insets as a margin to the view. Here the system is setting
        //   // only the bottom, left, and right dimensions, but apply whichever insets are
        //   // appropriate to your layout. You can also update the view padding
        //   // if that's more appropriate.
        //   MarginLayoutParams mlp = (MarginLayoutParams) v. v();
        //   mlp.leftMargin = insets.left;
        //   mlp.bottomMargin = insets.bottom;
        //   mlp.rightMargin = insets.right;
        //   v.setLayoutParams(mlp);
        //
        //   // Return CONSUMED if you don't want want the window insets to keep being
        //   // passed down to descendant views.
        //   return WindowInsetsCompat.CONSUMED;
        // });

        // ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
        //   Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemGestures());
        //   // Apply the insets as padding to the view. Here we're setting all of the
        //   // dimensions, but apply as appropriate to your layout. You could also
        //   // update the views margin if more appropriate.
        //   view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
        //
        //   // Return CONSUMED if we don't want the window insets to keep being passed
        //   // down to descendant views.
        //   return WindowInsetsCompat.CONSUMED;
        // });



        break;
      case "OVERLAYS_STICKY_IMMERSIVE": //TODO: fix action bar :(
        // enabledOverlays =
        //     DEFAULT_SYSTEM_UI
        //         | View.SYSTEM_UI_FLAG_FULLSCREEN
        //         | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        //         | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        // enabledOverlays |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY; //if tried in conjunction with next case, navigation bar just disappears, as expected
        // view.setSystemUiVisibility(enabledOverlays);

        WindowCompat.setDecorFitsSystemWindows(window,false);
        windowInsetsControllerCompat.hide(WindowInsets.Type.statusBars());
        windowInsetsControllerCompat.hide(WindowInsets.Type.navigationBars());
        windowInsetsControllerCompat.setSystemBarsBehavior(
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        break;
      case "READD_TOP_OVERLAYS": //TODO: verify accuracy :)
        // enabledOverlays =
        //     DEFAULT_SYSTEM_UI
        //         | View.SYSTEM_UI_FLAG_FULLSCREEN
        //         | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        //         | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        // // line below deletes flag from overlays (even if called more than once)
        // enabledOverlays &= ~View.SYSTEM_UI_FLAG_FULLSCREEN;
        //
        // view.setSystemUiVisibility(enabledOverlays);

        // windowInsetsControllerCompat.hide(WindowInsets.Type.statusBars());
        // window.setDecorFitsSystemWindows(false);
        // windowInsetsControllerCompat.hide(WindowInsets.Type.navigationBars());
        //
        // windowInsetsControllerCompat.show(WindowInsets.Type.statusBars());

        break;
      case "READD_BOTTOM_OVERLAYS": //TODO: fix action bar :(
        // enabledOverlays =
        //     DEFAULT_SYSTEM_UI
        //         | View.SYSTEM_UI_FLAG_FULLSCREEN
        //         | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        //         | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        // // brings back navigation bars
        // enabledOverlays &= ~View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        // enabledOverlays &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        //
        // enabledOverlays = View.SYSTEM_UI_FLAG_FULLSCREEN
        //                          | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        // view.setSystemUiVisibility(enabledOverlays);

        // WindowCompat.setDecorFitsSystemWindows(window, false);
        // windowInsetsControllerCompat.hide(WindowInsets.Type.navigationBars());
        // windowInsetsControllerCompat.hide(WindowInsets.Type.statusBars());
        //
        //
        // // windowInsetsControllerCompat.show(WindowInsets.Type.navigationBars());

        break;
      case "JUST_DEFAULTS":
        // view.setSystemUiVisibility(DEFAULT_SYSTEM_UI);

        WindowCompat.setDecorFitsSystemWindows(window, false);
        break;
      case "EXPERIMENT":
          // WindowMetrics windowMetrics = getWindowManager().getCurrentWindowMetrics();
          // Log.e("WINDOW_METRICS", windowMetrics.toString());
          // WindowInsets windowInsets = windowMetrics.getWindowInsets();
          // Log.e("WINDOW_INSETS", windowInsets.toString());
          // Insets i = windowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.statusBars()); //TODO: windowinsetscompat vs windowinsets
          // Log.e("INSETS_BABY", i.toString());

        WindowCompat.setDecorFitsSystemWindows(window, false);

        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
          Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
          // Apply the insets as a margin to the view. Here the system is setting
          // only the bottom, left, and right dimensions, but apply whichever insets are
          // appropriate to your layout. You can also update the view padding
          // if that's more appropriate.
          MarginLayoutParams mlp = (MarginLayoutParams) v.getLayoutParams();
          mlp.leftMargin = insets.left;
          mlp.bottomMargin = insets.bottom;
          mlp.rightMargin = insets.right;
          v.setLayoutParams(mlp);

          // Return CONSUMED if you don't want want the window insets to keep being
          // passed down to descendant views.
          return WindowInsetsCompat.CONSUMED;
        });


        // Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemGestures());
        // view.setPadding(insets.left, insets.top, insets.right, insets.bottom);



        break;
      default: //TODO: fix action bar :(
        // enabledOverlays =
        //     DEFAULT_SYSTEM_UI
        //         | View.SYSTEM_UI_FLAG_FULLSCREEN
        //         | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        //         | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        // view.setSystemUiVisibility(enabledOverlays);

        // windowInsetsControllerCompat.hide(WindowInsets.Type.statusBars());
        // windowInsetsControllerCompat.hide(WindowInsets.Type.navigationBars());
        // window.setDecorFitsSystemWindows(false);

        break;
    }
  }
}