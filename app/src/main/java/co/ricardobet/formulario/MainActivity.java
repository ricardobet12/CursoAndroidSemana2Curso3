package co.ricardobet.formulario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private DateFormat fFecha;

    TextInputEditText eNombre;
    TextInputEditText eFecha;
    TextInputEditText eTelefono;
    TextInputEditText eCorreo;
    TextInputEditText eDescripcion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        eNombre = findViewById(R.id.eNombre);
        eFecha = findViewById(R.id.eFecha);
        eTelefono = findViewById(R.id.eTelefono);
        eCorreo = findViewById(R.id.eCorreo);
        eDescripcion = findViewById(R.id.eDescripcion);
    }

    public void ingresarFecha(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        if (!eFecha.getText().toString().trim().isEmpty()) {
            try {
                newFragment.setSelectedDate(fFecha.parse(eFecha.getText().toString().trim()));
            } catch (ParseException e) {
            }
        }
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void confirmValues(View v) {
        Intent intent = new Intent(MainActivity.this, ConfirmarDatos.class);
        intent.putExtra(ConfirmarDatos.EXTRA_NOMBRE, eNombre.getText().toString());
        intent.putExtra(ConfirmarDatos.EXTRA_FECHA, eFecha.getText().toString());
        intent.putExtra(ConfirmarDatos.EXTRA_TELEFONO, eTelefono.getText().toString());
        intent.putExtra(ConfirmarDatos.EXTRA_CORREO, eCorreo.getText().toString());
        intent.putExtra(ConfirmarDatos.EXTRA_DESCRIPCION, eDescripcion.getText().toString());
        startActivity(intent);
    }

    private void selectedDate(Date date) {
        eFecha.setText(fFecha.format(date));
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private Date seleccionarFecha;

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            if (seleccionarFecha != null) {
                c.setTime(seleccionarFecha);
            }
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            final Calendar c = Calendar.getInstance();
            c.set(year, month, day, 0, 0);

            ((MainActivity) getActivity()).selectedDate(c.getTime());
        }

        public void setSelectedDate(Date selectedDate) {
            this.seleccionarFecha = selectedDate;
        }
    }
}