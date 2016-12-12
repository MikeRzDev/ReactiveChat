package co.mikerzdev.reactivechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.mikerzdev.reactivechat.service.MessageManagerService;
import co.mikerzdev.reactivechat.ui.login.StartFragment;
import co.mikerzdev.reactivechat.utils.FragmentNavigator;
import co.mikerzdev.reactivechat.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
        initViews();
    }

    private void initVars(){
        Intent serviceIntent = new Intent(this,MessageManagerService.class);
        if (!Utils.isServiceRunning(this,MessageManagerService.class))
            startService(serviceIntent);
    }

    private void initViews(){
        FragmentNavigator.changeFragment(R.id.container,getSupportFragmentManager(),new StartFragment());
    }
}
