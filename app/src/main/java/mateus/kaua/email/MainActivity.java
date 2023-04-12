package mateus.kaua.email;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Pegando itens da view */
                EditText etEmail = findViewById(R.id.etEmail);
                EditText etAssunto = findViewById(R.id.etAssunto);
                EditText etTexto = findViewById(R.id.etTexto);
                /* pegando dados dos itens */
                String email = etEmail.getText().toString();
                String assunto = etAssunto.getText().toString();
                String Texto = etTexto.getText().toString();
                /* criando a intenção de mudar de efetuar algo */
                Intent i = new Intent(Intent.ACTION_SENDTO);
                /* Buscando apps com a função mailto "e-mail para" */
                i.setData(Uri.parse("mailto:"));
                /* adicionando os parâmetros */
                String[] emails = new String[] {email};
                i.putExtra(Intent.EXTRA_EMAIL,emails);
                i.putExtra(Intent.EXTRA_SUBJECT,assunto);
                i.putExtra(Intent.EXTRA_TEXT,Texto);
                /* usando um try e catch para caso não existam apps com essa função no dispositivo */
                try {
                    /* abra uma aba para escolher o app */
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                catch (ActivityNotFoundException e){
                    /* Mostrando um aviso que o dispositivo não possui apps que resolvam a solicitação */
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}