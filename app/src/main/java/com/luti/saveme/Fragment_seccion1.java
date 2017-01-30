package com.luti.saveme;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.common.StringUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Luti on 28/1/17.
 */
public class Fragment_seccion1 extends Fragment {

    private TextView formatTxt, contentTxt;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //String num = getActivity().getIntent().getStringExtra("com.sample.MESSAGE");

        //contentTxt = (TextView) getView().findViewById(R.id.scan_content);

        //  "Inflamos" el archivo XML correspondiente a esta sección.
        View v =  inflater.inflate(R.layout.fragment_seccion1,container,false);

        Button buttonScan = (Button) v.findViewById(R.id.scan_button);
        buttonScan .setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "BAR_CODE_MODE");
                startActivityForResult(intent, 0);
            }
        });
        return v;
    }

    @Override
    public void  onActivityResult(int requestCode, int resultCode, Intent intent) {

        contentTxt = (TextView) getView().findViewById(R.id.scan_content);
        formatTxt = (TextView) getView().findViewById(R.id.scan_format);
        if (requestCode == 0) {
            if (resultCode == getActivity().RESULT_OK) {

                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");



                Toast.makeText(getActivity(),"SCAN_RESULT --- >>>   " + contents ,
                        Toast.LENGTH_LONG).show();
                formatTxt.setText("Formato:\n" + format.toString());
                // Handle successful scan
                contentTxt.setText("Contenido:\n" + contents.toString());


            } else if (resultCode == getActivity().RESULT_CANCELED) {
                // Handle cancel
                Log.i("App","Scan unsuccessful");
            }
        }
    }

    //El Activity que contiene el Fragment ha terminado su creación
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
