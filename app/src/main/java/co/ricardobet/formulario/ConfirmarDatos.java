package co.ricardobet.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.os.Bundle;

public class ConfirmarDatos extends AppCompatActivity {


    public static final String EXTRA_NOMBRE = "nombre";
    public static final String EXTRA_FECHA = "fecha";
    public static final String EXTRA_TELEFONO = "telefono";
    public static final String EXTRA_CORREO = "correo";
    public static final String EXTRA_DESCRIPCION = "descripcion";

    TextView nombre;
    TextView fecha;
    TextView telefono;
    TextView correo;
    TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        nombre = findViewById(R.id.tNombre);
        fecha = findViewById(R.id.tFecha);
        telefono = findViewById(R.id.tTelefono);
        correo = findViewById(R.id.tCorreo);
        descripcion = findViewById(R.id.tDescripcion);

        Bundle params = getIntent().getExtras();
        nombre.setText(params.getString(EXTRA_NOMBRE, ""));
        fecha.setText(params.getString(EXTRA_FECHA, ""));
        telefono.setText(params.getString(EXTRA_TELEFONO, ""));
        correo.setText(params.getString(EXTRA_CORREO, ""));
        descripcion.setText(params.getString(EXTRA_DESCRIPCION, ""));


    }



    public void editarDatos(View v) {
        finish();
    }
}