package com.luti.saveme;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private Adaptador_ViewPagerPrincipal Adaptador_ViewPagerPrincipal;
    private ViewPager ViewPager;
    private TextView formatTxt, contentTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se Instancia el Campo de Texto para el nombre del formato de código de barra
        //formatTxt = (TextView)findViewById(R.id.scan_format);
        //Se Instancia el Campo de Texto para el contenido  del código de barra
        //contentTxt = (TextView)findViewById(R.id.scan_content);

        // Iniciamos la barra de herramientas.
        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolbarPrincipal);
        setSupportActionBar(toolbar);

        // Iniciamos la barra de tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.TabLayoutPrincipal);

// Añadimos las 3 tabs de las secciones.
// Le damos modo "fixed" para que todas las tabs tengan el mismo tamaño. También le asignamos una gravedad centrada.
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());


        // Iniciamos el viewPager.
        ViewPager = (ViewPager) findViewById(R.id.ViewPagerPrincipal);

// Creamos el adaptador, al cual le pasamos por parámtro el gestor de Fragmentos y muy importante, el nº de tabs o secciones que hemos creado.
        Adaptador_ViewPagerPrincipal = new Adaptador_ViewPagerPrincipal(getSupportFragmentManager(),tabLayout.getTabCount());

// Y los vinculamos.
        ViewPager.setAdapter(Adaptador_ViewPagerPrincipal);

        // Y por último, vinculamos el viewpager con el control de tabs para sincronizar ambos.
        tabLayout.setupWithViewPager(ViewPager);




    }


    public void read(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);

        integrator.addExtra("SCAN_WIDTH", 800);
        integrator.addExtra("SCAN_HEIGHT", 800);
        integrator.addExtra("PROMPT_MESSAGE", "Busque un código para escanear");

//        integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
        integrator.initiateScan();


    }

    public void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.scanning_content);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.show();
        //contentTxt.setText(message);
    }



  /*  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {

           // System.out.println("Información encontrada");
            //System.out.println(scanResult.getContents());
            //System.out.println(scanResult.getFormatName());

            //Quiere decir que se obtuvo resultado pro lo tanto:
            //Desplegamos en pantalla el contenido del código de barra scaneado


            //Desplegamos en pantalla el nombre del formato del código de barra scaneado
            //String scanFormat = scanResult.getFormatName();
            //formatTxt.setText("Formato: " + scanFormat.toString());

            //showDialog(scanResult.getContents());
            Bundle bundle = new Bundle();
            String scanContent = scanResult.getContents();
            bundle.putString("EXTRA_MESSAGE", scanContent);
            // set Fragmentclass Arguments
            Fragment_seccion1 fragobj = new Fragment_seccion1();
            fragobj.setArguments(bundle);



            //contentTxt.setText("Contenido: " + scanContent.toString());
        }
    }*/

    public void write(View view) {
        // CONTACT

        Bundle bundle = new Bundle();
        bundle.putString(ContactsContract.Intents.Insert.NAME, "Lutgardo Jiménez");
        bundle.putString(ContactsContract.Intents.Insert.PHONE, "699221787");
        bundle.putString(ContactsContract.Intents.Insert.EMAIL, "lutgardo1983@hotmail.com");
//        bundle.putString(ContactsContract.Intents.Insert.POSTAL, "123 Fake St. San Francisco, CA 94102");

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.addExtra("ENCODE_DATA", bundle);
        integrator.shareText(bundle.toString(), "CONTACT_TYPE");

        // LOCATION
/*
        Bundle bundle = new Bundle();
        bundle.putFloat("LAT", 40.829208f);
        bundle.putFloat("LONG", -74.191279f);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.addExtra("ENCODE_DATA", bundle);
        integrator.shareText(bundle.toString(), "LOCATION_TYPE");
        */

//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.shareText("http://alvarez.tech", "TEXT_TYPE");


        // EMAIL_TYPE, PHONE_TYPE, SMS_TYPE
    }








}
