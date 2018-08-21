package co.com.uco.tuedadahora;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import co.com.uco.tuedadahora.constantes.Constantes;

public class EdadActivity extends AppCompatActivity {

    private TextView resultadoEdad;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edad);
        initComponents();

    }

    private void initComponents() {

        resultadoEdad = (TextView) findViewById(R.id.resultadoEdad);
        intent = getIntent();
        String nombre = intent.getStringExtra(Constantes.NOMBRE);
        String edad = intent.getStringExtra(Constantes.EDAD);
        Resources resources = getResources();
        String recurso = resources.getString(R.string.respuesta_formateada);
        String respuesta = String.format(recurso, nombre, edad);
        resultadoEdad.setText(respuesta);

    }
}
