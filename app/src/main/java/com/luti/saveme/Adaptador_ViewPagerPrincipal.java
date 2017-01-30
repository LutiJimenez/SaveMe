package com.luti.saveme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Luti on 28/1/17.
 */
public class Adaptador_ViewPagerPrincipal extends FragmentPagerAdapter {

    int numeroDeSecciones;


    public Adaptador_ViewPagerPrincipal(FragmentManager fm, int numeroDeSecciones) {
        super(fm);
        this.numeroDeSecciones = numeroDeSecciones;
    }


    @Override
    public Fragment getItem(int position) {
        // recibimos la posición por parámetro y en función de ella devolvemos el Fragment correspondiente a esa sección.
        switch (position) {

            case 0: // siempre empieza desde 0
                return new Fragment_seccion1();

            case 1:
                return new Fragment_seccion2();


            // si la posición recibida no se corresponde a ninguna sección
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numeroDeSecciones;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        // recibimos la posición por parámetro y en función de ella devolvemos el titulo correspondiente.
        switch (position) {

            case 0: // siempre empieza desde 0, la primera Tab es 0
                return "Leer";
            case 1:
                return "Crear";

            // si la posición recibida no se corresponde a ninguna sección
            default:
                return null;
        }

    }
}
