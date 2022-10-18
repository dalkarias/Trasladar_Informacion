package danielpl.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import danielpl.myapplication.databinding.ActivityMainBinding;
import danielpl.myapplication.modelos_datos.Alumno;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;//se declara para asocia la actividad con los xml

    private ActivityResultLauncher<Intent> add_Alumno_Launcher; // creamos el launcher para que se pueda conectar con la tuberia

    private ArrayList<Alumno> listaAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());//se instancia para que haga referencia al ativity_main.xml
        setContentView(binding.getRoot());//damos de alta el binding
        setSupportActionBar(binding.toolbar);

        listaAlumno = new ArrayList<>();//declaramos el arraylist que recogera los alumnos

        inicializaLauncher();//dar de alta los launchers


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_Alumno_Launcher.launch(new Intent(MainActivity.this,AddAlumnoActivity.class)); //1:59:00min
            }
        });


    }

    private void inicializaLauncher() {

        add_Alumno_Launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //LOGICA PARA RECOGER EL OBJETO DE OTRA VENTANA
                         if(result.getResultCode() == RESULT_OK){
                             if(result.getData() != null && result.getData().getExtras() != null){
                                 Alumno al = (Alumno) result.getData().getExtras().getSerializable("ALUMNO");
                                if(al != null){
                                    listaAlumno.add(al);
                                    mostrarAlumnos();
                                }
                             }
                         }
                    }
                }
        );
    }
        // usamos un text view para mostrar información pero no se suele usar ES MUY SIMPLE
    private void mostrarAlumnos(){
        binding.contentMain.contenedorAlumnoMain.removeAllViews();

        for (Alumno a:listaAlumno) {
            /*
            TextView txtAlumno = new TextView(MainActivity.this);//creamos un text view por codigo
            txtAlumno.setText(a.toString());

             */
            //conectamos el layout alumno model que es el que va a contener la informacion
            LayoutInflater lif = LayoutInflater.from(MainActivity.this);
            lif.inflate(R.layout.alumno_model_view, null);

            View alumnoView = lif.inflate(R.layout.alumno_model_view,null); //dentor de alumno view esta todo el layout alumno model

            //conectamos toda la informacion
            TextView txtNombre = alumnoView.findViewById(R.id.lbl_nombre_alumno_view);
            TextView txtApellido = alumnoView.findViewById(R.id.lbl_Apellidos_alumno_view);
            TextView txtCiclo = alumnoView.findViewById(R.id.lbl_ciclo_alumno_view);
            TextView txtGrupo = alumnoView.findViewById(R.id.lbl_grupo_alumno_view);

            txtNombre.setText(a.getNombre());
            txtApellido.setText(a.getApellido());
            txtCiclo.setText(a.getCiclo());
            txtGrupo.setText(String.valueOf(a.getGrupo()));
            //MOSTRAMOS INFORMACION POR PANTALLA
            //binding.contentMain.contenedorAlumnoMain.addView(txtAlumno);
            binding.contentMain.contenedorAlumnoMain.addView(alumnoView);
        }

    }


}