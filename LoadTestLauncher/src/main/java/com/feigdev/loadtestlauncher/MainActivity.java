package com.feigdev.loadtestlauncher;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {
    private static final String KILL_NOW = "KILL_NOW";
    private static final String KEEP_ALIVE = "KEEP_ALIVE";
    private static final String CPU_ENABLED = "CPU_ENABLED";
    private static final String RAM_ENABLED = "RAM_ENABLED";
    private static final String NET_ENABLED = "NET_ENABLED";
    private static final String MODE = "MODE";
    private static final String LOW = "LOW";
    private static final String MEDIUM = "MEDIUM";
    private static final String HIGH = "HIGH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        /*
        Below is the important part! We configure the load here, then launch it
         */
        Intent launchLoadTest = new Intent();
        launchLoadTest.setAction("com.feigdev.loadtester.api");
        long keepAlive = 20 * 1000;
        launchLoadTest.putExtra(KEEP_ALIVE, keepAlive);
        launchLoadTest.putExtra(CPU_ENABLED, true);
        launchLoadTest.putExtra(RAM_ENABLED, true);
        launchLoadTest.putExtra(NET_ENABLED, true);
        launchLoadTest.putExtra(MODE, LOW);
        sendBroadcast(launchLoadTest);
    }

    @Override
    public void onDestroy() {
        /*
        Whenever we leave our test app, we're going to send a message to kill the load app
         */
        Intent launchLoadTest = new Intent();
        launchLoadTest.setAction("com.feigdev.loadtester.api");
        launchLoadTest.putExtra(KILL_NOW, KILL_NOW);
        sendBroadcast(launchLoadTest);
        super.onDestroy();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
