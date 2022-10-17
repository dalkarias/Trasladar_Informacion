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

    private void mostrarAlumnos(){
        binding.contentMain.contenedorAlumnoMain.removeAllViews();

        for (Alumno a:listaAlumno) {
            TextView txtAlumno = new TextView(MainActivity.this);//creamos un text view por codigo
            txtAlumno.setText(a.toString());
            binding.contentMain.contenedorAlumnoMain.addView(txtAlumno);
        }
        listaAlumno.clear();
    }
}