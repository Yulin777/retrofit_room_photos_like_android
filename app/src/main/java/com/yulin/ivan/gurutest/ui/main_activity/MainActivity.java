package com.yulin.ivan.gurutest.ui.main_activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelStoreOwner;

import com.yulin.ivan.gurutest.R;
import com.yulin.ivan.gurutest.ui.a_frag.AFrag;
import com.yulin.ivan.gurutest.ui.a_frag.APresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openAFrag();
    }


    private void openAFrag() {
        AFrag AFrag = new AFrag();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.a_container, AFrag)
                .commit();

        APresenter APresenter = new APresenter(this, AFrag);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack("fragB", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
