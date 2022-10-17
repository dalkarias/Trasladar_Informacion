package danielpl.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import danielpl.myapplication.databinding.ActivityAddAlumnoBinding;
import danielpl.myapplication.modelos_datos.Alumno;

public class AddAlumnoActivity extends AppCompatActivity {

    private ActivityAddAlumnoBinding binding; //esta clase siempre la podremos usar siempre que este declarado en el GRADLE




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alumno);

        binding = ActivityAddAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());//dar de alta el binding



        binding.btnCancelarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);//indica que termina la actividad
                finish();//cierra la actividad
            }
        });

        binding.btnCrearAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alumno al = crearAlumno();
                //si el alumno esta completo
                if (al != null) {
                    Bundle mochila = new Bundle();//lo introducira en la mochila
                    mochila.putSerializable("ALUMNO", al); //lo mandamos con la etiqueta "ALUMNO"

                    Intent tuberia = new Intent(); // creamos la conexion con el main
                    tuberia.putExtras(mochila); // mandamos la mochila
                    setResult(RESULT_OK, tuberia); // todo a salido bien
                    finish(); // se cierra la venta
                }
                else
                {
                    // en caso que nos falte algun dato en alumno nos mostrara el siguiente mensaje
                    Toast.makeText(AddAlumnoActivity.this, "Faltan Datos", Toast.LENGTH_SHORT).show();
                }
            }
            
        });
    }

    private Alumno crearAlumno() {
        //binding hace referencia al findbyid/rocogeTexto/en string/limpias espacios/esta vacio true
        if(binding.txtNombreAlumno.getText().toString().isEmpty() || binding.txtApellidoAlumno.getText().toString().isEmpty())
        {
            return null;//si faltan o nombre o apellido nos devuelve un null de alumno
        }
        if(binding.spCiclosAlumno.getSelectedItemPosition() == 0) {
            return null;
        }
        if(!binding.rbGrupoAAlumno.isChecked() && !binding.rbGrupoBAlumno.isChecked() && !binding.rbGrupoCAlumno.isChecked())
        {
            return null;
        }

        String ciclo = (String) binding.spCiclosAlumno.getSelectedItem(); //debemos castearlo ya que sino nos devovera un obj y no puedes introducirlo en el String
        RadioButton rb = findViewById(binding.rgGrupoAlumno.getCheckedRadioButtonId());//le añades el array de strings
        char grupo = rb.getText().charAt(0); // le mandamos el primer string que se encuentra en la posicion 0

        //si todo se cumple creamos el alumno y lo mandamos para crear el ALUMNO
        return new Alumno(
                binding.txtNombreAlumno.getText().toString().trim(),
                binding.txtApellidoAlumno.getText().toString().trim(),
                ciclo,
                grupo
        );
    }


}