package ba.leftor.exercises.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 18.1.2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> dataSet;

    /**
     * Constructor
     * @param myDataSet
     */
    public MyAdapter(ArrayList<String> myDataSet) {
        dataSet = myDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = dataSet.get(position);
        holder.txtHeader.setText("Header " +dataSet.get(position));
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });
        holder.txtFooter.setText("Footer " + name);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void add(int position, String item){
        dataSet.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position){
        dataSet.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            txtHeader = (TextView)itemView.findViewById(R.id.firstLine);
            txtFooter = (TextView)itemView.findViewById(R.id.secondLine);
        }
    }

}
