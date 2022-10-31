package com.example.guessnumberdynamic.iu;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.guessnumberdynamic.R;
import com.example.guessnumberdynamic.data.GuessNumber;

/**
 * <h1>Projecto 01 - Guess Number</h1>
 * <h3>Main Activity</h3>
 *
 * Esta activity es la única que existe. Es donde se muestran los diferentes fragments y es la encargada de gestionar las interfaces
 * que tienen los diferentes fragments, haciendo también las transiciones para poder cambiar de un fragment a otro.
 *
 * @author Daniel
 * @version 1.0.0
 */


public class MainActivity extends AppCompatActivity implements FragmentConfig.OnSetDataGuessNumber , FragmentPlay.OnSendDataGuessNumber , FragmentEndPlay.OnVolverAJugar {

    private Fragment fragmentplay;
    private Fragment fragmentconfig;
    private Fragment fragmentendplay;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentconfig = getSupportFragmentManager().findFragmentByTag(FragmentConfig.TAG);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentconfig = new FragmentConfig();
            fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(android.R.id.content, fragmentconfig);
            fragmentTransaction.commit();
        }
        Log.d(TAG, "MainActivity -> OnCreate()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"MainActivity -> OnStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"MainActivity -> OnResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"MainActivity -> OnPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"MainActivity -> OnStop()");
    }

    @Override
    public void onSetDataGuessNumber(GuessNumber guessNumber) {
        fragmentplay = new FragmentPlay();
        Bundle bundle = new Bundle();
        bundle.putParcelable(GuessNumber.KEY, guessNumber);
        fragmentplay.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, fragmentplay);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onSendDataGuessNumber(GuessNumber guessNumber) {
        fragmentendplay = new FragmentEndPlay();
        Bundle bundle = new Bundle();
        bundle.putParcelable(GuessNumber.KEY, guessNumber);
        fragmentendplay.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, fragmentendplay);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onVolverAJugar() {
        fragmentconfig = new FragmentConfig();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.replace(android.R.id.content, fragmentconfig);
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity -> OnDestroy()");
    }
}