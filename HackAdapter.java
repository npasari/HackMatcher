//Nayan Pasari
//111868106
package com.example.hackmatcher;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class HackAdapter extends RecyclerView.Adapter<HackAdapter.HackViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public HackAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    @Override
    public HackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.data_display, parent,false);
        return new HackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HackViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex(HackContract.HackEntry.COLUMN_NAME));
        String school = mCursor.getString(mCursor.getColumnIndex(HackContract.HackEntry.COLUMN_SCHOOL));
        int amount = mCursor.getInt(mCursor.getColumnIndex(HackContract.HackEntry.COLUMN_AMOUNT));
        String major = mCursor.getString(mCursor.getColumnIndex(HackContract.HackEntry.COLUMN_MAJOR));
        int grad = mCursor.getInt(mCursor.getColumnIndex(HackContract.HackEntry.COLUMN_GRAD));
        String gender = mCursor.getString(mCursor.getColumnIndex(HackContract.HackEntry.COLUMN_GENDER));
        String lang = mCursor.getString(mCursor.getColumnIndex(HackContract.HackEntry.COLUMN_LANG));
        String link = mCursor.getString(mCursor.getColumnIndex(HackContract.HackEntry.COLUMN_LINK));
        long id = mCursor.getLong(mCursor.getColumnIndex(HackContract.HackEntry._ID));

        holder.nameText.setText(name);
        holder.schoolText.setText(school);
        holder.countText.setText(String.valueOf(amount));
        holder.majorText.setText(major);
        holder.gradText.setText(String.valueOf(grad));
        holder.genderText.setText(gender);
        holder.langText.setText(lang);
        holder.linkText.setText(link);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public class HackViewHolder extends RecyclerView.ViewHolder{

        public TextView nameText;
        public TextView schoolText;
        public TextView countText;
        public TextView majorText;
        public TextView gradText;
        public TextView genderText;
        public TextView langText;
        public TextView linkText;

        public HackViewHolder(View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.textview_stud_name);
            schoolText = itemView.findViewById(R.id.textview_school_name);
            countText = itemView.findViewById(R.id.textView_nohacks);
            majorText = itemView.findViewById(R.id.textview_major);
            gradText=itemView.findViewById(R.id.textview_grad_yr);
            genderText = itemView.findViewById(R.id.textview_gender);
            langText = itemView.findViewById(R.id.textView_lang);
            linkText = itemView.findViewById(R.id.textView_link);

        }
    }
}
