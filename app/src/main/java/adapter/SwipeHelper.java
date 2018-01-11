package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import database.DBhelper;
import model.Notes;
import sample.app.com.notes2.NotesActivity;

/**
 * Created by Nanda devi shetty b on 30-11-2017.
 */

public class SwipeHelper extends ItemTouchHelper.SimpleCallback {

    public static final String TAG = SwipeHelper.class.getSimpleName();

    NotesAdapter notesAdapter;
    DBhelper db;
    private Context mContext;

   public SwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public SwipeHelper(NotesAdapter notesAdapter, Context context) {
        super(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.RIGHT);
        mContext = context;
        this.notesAdapter = notesAdapter;

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (direction == ItemTouchHelper.RIGHT) {
            db = new DBhelper(mContext);
            Notes notes = notesAdapter.getItem(viewHolder.getAdapterPosition());
            notesAdapter.dismiss(viewHolder.getAdapterPosition());
            db.deleteEntry(notes.get_ID());

        }
        else if(direction == ItemTouchHelper.LEFT) {

            Intent i= new Intent(mContext, NotesActivity.class);
            mContext.startActivity(i);

        }
    }
}