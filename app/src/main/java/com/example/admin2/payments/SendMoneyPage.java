package com.example.admin2.payments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SendMoneyPage extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private IntentFilter[] intentFiltersArray;
    private String[][] techListsArray;
    private NfcAdapter mAdapter;
    private TextView ReaderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ReaderID = (TextView) findViewById(R.id.etUsername);

        getData(getIntent());

        if (Build.VERSION.SDK_INT >= 10) {
            pendingIntent = PendingIntent.getActivity(
                    this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

            mAdapter = NfcAdapter.getDefaultAdapter(this);

            IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
            try {
                ndef.addDataType("*/*");
            } catch (IntentFilter.MalformedMimeTypeException e) {
                throw new RuntimeException("Malformed Mime Type", e);
            }
            intentFiltersArray = new IntentFilter[]{ndef};

            //MifareUltralight.class.getName() ,

            techListsArray = new String[][]{new String[]{MifareClassic.class.getName(),NfcA.class.getName(), NdefFormatable.class.getName()}};
        }

        final Intent registerIntent = getIntent();
        final String username = registerIntent.getStringExtra("username");

        final EditText etName = (EditText) findViewById(R.id.etCompanyName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etAmount = (EditText) findViewById(R.id.etAmount);
        final Button bRegister = (Button) findViewById(R.id.bRegister);



        etName.setText(username);
        Log.v("checking", "this is "+ username);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String sender = etName.getText().toString();
                final String receiver = etUsername.getText().toString();
                int amount = Integer.parseInt(etAmount.getText().toString());


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {

                                int balance = jsonResponse.getInt("balance");

                                Intent intent = new Intent(SendMoneyPage.this, UserArea.class);
                                intent.putExtra("name", sender);
                                intent.putExtra("balance", balance);
                                intent.putExtra("username", username);
                                SendMoneyPage.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SendMoneyPage.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                SendMoneyController sendMoneyController = new SendMoneyController(sender, receiver, amount,responseListener);
                RequestQueue queue = Volley.newRequestQueue(SendMoneyPage.this);
                queue.add(sendMoneyController);

    }
        }
        )
        ;
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
            disableForegroundDispatch(this);
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

    public void disableForegroundDispatch(Activity activity) {
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

            Toast.makeText(getApplicationContext(), TagUID,
                    Toast.LENGTH_LONG).show();

        }
    }
}



