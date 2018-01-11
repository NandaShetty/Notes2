package sample.app.com.notes2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import database.DBhelper;
import model.Notes;

/**
 * Created by Nanda devi shetty b on 30-11-2017.
 */

public class NotesActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    private EditText uname;
    private EditText pswd;
    TextView result;
    Spinner spinner;

    private boolean isEdit;

    String editTitle;

    DBhelper db;
    SharedPreferences sharedPreferences;
    private Notes mNote;


    String[] category = {"Education", "Event", "Shopping", "Hospital", "Meeting", "Work"};


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        init();
    }

    private void init() {

        db = new DBhelper(this);
        uname = (EditText) findViewById(R.id.uname);
        pswd = (EditText) findViewById(R.id.password);
        result = (TextView) findViewById(R.id.result);


        spinner = (Spinner) findViewById(R.id.sp);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this,
                R.array.select_category, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        db = new DBhelper(getApplicationContext());
        Intent mIntent = getIntent();
        editTitle = mIntent.getStringExtra("title");
        isEdit = mIntent.getBooleanExtra("isEdit", false);


        if (!isEdit) {//editnote
            mNote=getIntent().getParcelableExtra("note");
            uname.setText(mNote.getName());
            pswd.setText(mNote.getPassword());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        db=new DBhelper(this);
        Notes u = new Notes();

        String name = uname.getText().toString();
        String content = pswd.getText().toString();
        String type=spinner.getSelectedItem().toString();

        if (id == R.id.action_save) {

            if (isEdit) {
                u.name = name;
                u.password = content;
                u.category = type;
                db.addUser(u);


                if (name.equals("") || content.equals("")) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.validation), Toast.LENGTH_LONG).show();

                }

                Intent intent = new Intent(NotesActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
            }

            else{

              db.updateNote(mNote.get_ID(),name,content,type);
              db.close();

              Intent intent = new Intent(NotesActivity.this, MainActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              finish();
              startActivity(intent);

                }
            }
            if (id == R.id.action_alarm) {

                Intent intent = new Intent(NotesActivity.this,SetAlarmActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("content",content);
                startActivity(intent);
            }

            if (id == R.id.action_share) {

                String n = uname.getText().toString();
                String p = pswd.getText().toString();
                String np="Title: "+n+"\nContent: "+p;

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, np);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
            }

            return super.onOptionsItemSelected(item);
        }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}