package com.example.manipulandopastas.activity.ui;

import static com.example.manipulandopastas.activity.ui.ConstantesActivities.CHAVE_ALUNO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.manipulandopastas.DAO.AlunoDAO;
import com.example.manipulandopastas.R;
import com.example.manipulandopastas.model.Aluno;

public class Formulario_alunos extends AppCompatActivity {
    private final String TITULO_APP_BAR="Novo Aluno";
    private final String TITULO_EDICAO="Editar Aluno";
    private EditText campoNome;
    private EditText campoTel;
    private EditText campoMail;
    final  AlunoDAO dao= new AlunoDAO();
    private Aluno aluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario_alunos);
        inicializacaoDosCampos();
        carregaAluno();
        //1234
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_menu_aluno,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==R.id.activity_aluno_menu_salvar){
            finalizaformulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados=getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)){
            setTitle(TITULO_EDICAO);

            aluno=(Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else{
            setTitle(TITULO_APP_BAR);

            aluno=new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoMail.setText(aluno.getTelefone());
        campoTel.setText(aluno.getEmail());
    }

    private void finalizaformulario() {
        preencheAluno();
        if(aluno.temId()){
            dao.editaAluno(aluno);

        }
        else{
            dao.salvar(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome=findViewById(R.id.activity_aluno_nome);
        campoTel=findViewById(R.id.activity_aluno_telefone);
        campoMail=findViewById(R.id.activity_aluno_email);
    }


    private void preencheAluno() {
        String nome= campoNome.getText().toString();
        String telefone= campoTel.getText().toString();
        String email= campoMail.getText().toString();

        aluno.setEmail(email);
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
    }
}