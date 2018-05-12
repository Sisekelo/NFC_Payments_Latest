package com.example.admin2.payments;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


public class Enter extends Activity {

    private ProgressDialog progress;

    TextView tvReader;
    Button button;
    String name;
    String country;

    private PendingIntent pendingIntent;
    private IntentFilter[] intentFiltersArray;
    private String[][] techListsArray;
    private NfcAdapter mAdapter;
    private TextView ReaderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);

        ReaderID = (TextView) findViewById(R.id.ReaderID);

        getData(getIntent());

        if (Build.VERSION.SDK_INT >= 10) {
            pendingIntent = PendingIntent.getActivity(
                    this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

            mAdapter = NfcAdapter.getDefaultAdapter(this);

            IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
            try {
                ndef.addDataType("*/*");
            } catch (MalformedMimeTypeException e) {
                throw new RuntimeException("Malformed Mime Type", e);
            }
            intentFiltersArray = new IntentFilter[]{ndef};

            techListsArray = new String[][]{new String[]{MifareClassic.class.getName(), NfcA.class.getName(), NdefFormatable.class.getName()}};
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // NFC Foreground
        if (Build.VERSION.SDK_INT >= 10) {
            enableForegroundDespatch(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // NFC Foreground
        if (Build.VERSION.SDK_INT >= 10) {
            disableForegroundDespatch(this);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        getData(intent);
    }

    public void enableForegroundDespatch(Activity activity) {
        if (Build.VERSION.SDK_INT >= 10) {
            if (mAdapter != null && mAdapter.isEnabled()) {
                mAdapter.enableForegroundDispatch(activity, pendingIntent, intentFiltersArray, techListsArray);
            }
        }
    }

    public void disableForegroundDespatch(Activity activity) {
        if (Build.VERSION.SDK_INT >= 10) {
            if (mAdapter != null && mAdapter.isEnabled()) {
                mAdapter.disableForegroundDispatch(activity);
            }
        }
    }

    private void getData(Intent intent) {
        // NFC Background
        String action = intent.getAction();

        if (Build.VERSION.SDK_INT >= 10) {
            if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
                Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                handleNfc(tagFromIntent);
            }
        }
    }

    private void handleNfc(Tag tag) {
        if (Build.VERSION.SDK_INT >= 10) {
            String TagUID = Util.ConvertbyteArrayToHexString(tag.getId());

            ReaderID.setText(TagUID);

            name=ReaderID.getText().toString();

            Toast.makeText(getApplicationContext(),TagUID,
                    Toast.LENGTH_LONG).show();


        }
    }

}

