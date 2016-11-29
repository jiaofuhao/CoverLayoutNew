package jfh.viewgroup.demo;

import android.app.Activity;
import android.os.Bundle;

import jfh.viewgroup.CoverLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoverLayout coverLayout = (CoverLayout) findViewById(R.id.coverlayout);
    }
}
