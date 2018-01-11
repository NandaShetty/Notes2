package sample.app.com.notes2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import adapter.NotesAdapter;
import adapter.SwipeHelper;
import database.DBhelper;
import model.Notes;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvNotes;
    TextView subC;
    DBhelper db;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNotes = (RecyclerView) findViewById(R.id.rvNotes);

        displayAll();
    }

    private void displayAll() {

        db = new DBhelper(this);
        List<Notes> notesList = db.displayAll();

        if (null != notesList)

            notesAdapter = new NotesAdapter(getApplicationContext(), notesList);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            rvNotes.setLayoutManager(new LinearLayoutManager(this));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            rvNotes.setAdapter(notesAdapter);
        }

        ItemTouchHelper.Callback callback = new SwipeHelper(notesAdapter, this);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvNotes);

        Bundle myBundle = this.getIntent().getExtras();
        if (myBundle == null) {
            return;
        }

        String strRecieved = myBundle.getString("SPINNER_VALUE");
        if (strRecieved != null) {
            subC.setText(strRecieved);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_add) {

            Intent add = new Intent(this, NotesActivity.class);
            add.putExtra("isEdit",true);
            startActivity(add);
        }

        return super.onOptionsItemSelected(item);
    }
}
