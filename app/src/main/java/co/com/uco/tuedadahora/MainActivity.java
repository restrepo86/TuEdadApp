package co.com.uco.tuedadahora;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import co.com.uco.tuedadahora.constantes.Constantes;
import co.com.uco.tuedadahora.excepciones.TuEdadAhoraExcepcion;


public class MainActivity extends AppCompatActivity {

    private Button btnCalcularEdad;
    private EditText dateFeNacimiento;
    private EditText txtNombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents() {
        btnCalcularEdad = (Button) findViewById(R.id.btnCalcularEdad);
        dateFeNacimiento = (EditText) findViewById(R.id.dateFeNacimiento);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
    }

    public void mostrarDatePicker(View view) {

        int dia;
        int mes;
        int anio;

        if (view == dateFeNacimiento){
            final Calendar calendar = Calendar.getInstance();
            dia = calendar.get(Calendar.DAY_OF_MONTH);
            mes = calendar.get(Calendar.MONTH);
            anio = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                    dateFeNacimiento.setText(String.valueOf(dia).concat(Constantes.BARRA)
                                                                .concat(String.valueOf(mes + 1))
                                                                .concat(Constantes.BARRA)
                                                                .concat(String.valueOf(anio)));
                }
            }
            , dia, mes, anio);
            datePickerDialog.show();
        }

    }

    public void calcularEdad(View view) throws TuEdadAhoraExcepcion {

        try {
            String nombre = txtNombre.getText().toString().trim();
            int edadEnAnios = obtenerEdadEnAnios();
            if (nombre.isEmpty()) throw new TuEdadAhoraExcepcion(getString(R.string.error_campos_vacios));
            Intent intent = new Intent(MainActivity.this, EdadActivity.class);
            intent.putExtra(Constantes.EDAD, String.valueOf(edadEnAnios));
            intent.putExtra(Constantes.NOMBRE, nombre);
            txtNombre.setText("");
            dateFeNacimiento.setText("");
            startActivity(intent);

        } catch (TuEdadAhoraExcepcion excepcion) {
            Toast.makeText(getApplicationContext(), getString(R.string.error_campos_vacios), Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), getString(R.string.error_campos_vacios), Toast.LENGTH_SHORT).show();
        }

    }

    private int obtenerEdadEnAnios() throws TuEdadAhoraExcepcion {

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constantes.FORMATO_FECHA);
        Date fechaDeNacimiento = null;
        int anios = 0;
        try {
            fechaDeNacimiento = dateFormat.parse(dateFeNacimiento.getText().toString());
            Calendar feNacimiento = new GregorianCalendar();
            feNacimiento.setTime(fechaDeNacimiento);
            Calendar today = Calendar.getInstance();
            anios = today.get(Calendar.YEAR) - feNacimiento.get(Calendar.YEAR);
            int meses = today.get(Calendar.MONTH) - feNacimiento.get(Calendar.MONTH);
            int dias = today.get(Calendar.DAY_OF_MONTH) - feNacimiento.get(Calendar.DAY_OF_MONTH);
            if ( meses < 0 || (meses == 0 && dias < 0 )) {
                anios = anios - 1;
            }
        } catch (Exception e) {
            throw new TuEdadAhoraExcepcion(getString(R.string.error_campos_vacios), e);
        }

        return anios;
    }
}
