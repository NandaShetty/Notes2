
package sample.app.com.notes2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import database.DBhelper;
import model.Notes;

public class DetailNote extends AppCompatActivity {
    private static final String TAG = DetailNote.class.getSimpleName();

     TextView noteTitle;
     TextView createdAt;
     TextView noteContent;
     DBhelper dbhelper;
     Notes mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);

        mNote = getIntent().getParcelableExtra("note");
        Log.d(TAG,"DetailNote"+mNote);
        dbhelper = new DBhelper(getApplicationContext());

        noteTitle = (TextView) findViewById(R.id.noteTitle);
        noteContent = (TextView) findViewById(R.id.noteContent);
        createdAt = (TextView) findViewById(R.id.createdAt);

        Intent intent = getIntent();
        if(null!= intent) {
            noteTitle.setText(intent.getExtras().getString("title"));
            noteContent.setText(intent.getExtras().getString("details"));
        }

        if (mNote != null){
            noteTitle.setText(mNote.getName());
            noteContent.setText(mNote.getPassword());
            createdAt.setText(mNote.getAlarmDate());
    }
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.detail_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_edit) {
            Intent intent=new Intent(this,NotesActivity.class);
            intent.putExtra("note",mNote);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
