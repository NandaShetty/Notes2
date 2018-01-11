package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import model.Notes;
import sample.app.com.notes2.DetailNote;
import sample.app.com.notes2.R;

/**
 * Created by Nanda devi shetty b on 30-11-2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.LoginViewHolder> {

    Context mContext;
    List<Notes> notesList;

    public NotesAdapter(Context mContext, List<Notes> notesList) {
        this.mContext = mContext;
        this.notesList = notesList;

    }

    NotesAdapter() {
    }

    @Override
    public NotesAdapter.LoginViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_notes, parent,false);
        LoginViewHolder loginViewHolder = new LoginViewHolder(view);

        return loginViewHolder;
    }

    @Override
    public void onBindViewHolder(final LoginViewHolder holder, final int position) {
        final Notes note = notesList.get(position);
        holder.tvName.setText(note.getName());
        holder.category.setText(note.getCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailNote.class);
                i.putExtra("note", note);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void dismiss(int pos) {
        notesList.remove(pos);
        this.notifyItemRemoved(pos);
    }

    public Notes getItem(int position) {
        return notesList.get(position);
    }


    public class LoginViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView category;

        public LoginViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.title);
            category = (TextView) itemView.findViewById(R.id.category);
        }
    }
}
