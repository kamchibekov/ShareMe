package com.zuhra.sharemebackendv2.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.BackendlessUser;
import com.zuhra.sharemebackendv2.R;

import java.util.ArrayList;

/**
 * Created by zuhra on 13.07.2016.
 */
public class ApplyRecyclerViewAdapter extends RecyclerView.Adapter<ApplyRecyclerViewAdapter.ApplyViewHolder> {
    private static final String TAG = ApplyRecyclerViewAdapter.class.getName();
    public static final String YES = "yesssssssssssss";
    public static final String NO = "nooooooooooooo";
    private AdapterListener listener;

    public interface AdapterListener {
        void showMessage(String message);

        void changeStatus(String type, BackendlessUser backendlessUser);

    }

    private ArrayList<BackendlessUser> list = new ArrayList<>();


    public ApplyRecyclerViewAdapter(AdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public ApplyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apply_item, parent, false);

        Log.d("ApplyViewHolder", "onCreateViewHolder: ");

        final ApplyViewHolder holder = new ApplyViewHolder(view);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION) {
//
//                    Log.d("recyclerViewAdapter", "onClick: textView ");
//
//                }
//            }
//        });


        return holder;
    }

    @Override
    public void onBindViewHolder(ApplyViewHolder holder, int position) {
        holder.setData(list.get(position).getEmail());
        Log.d("ApplyViewHolder", "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void remove(int position) {
        if (position >= 0 && position < list.size()) {
            list.remove(position);
            notifyItemRemoved(position);
        }

    }

    public void addItem(BackendlessUser user) {
        list.add(user);
        notifyItemInserted(list.size());
    }

    public void setData(ArrayList<BackendlessUser> listLocal) {
        list = listLocal;
        notifyDataSetChanged();
    }

    class ApplyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView item;

        public ApplyViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.item);
            Button button_no = (Button) itemView.findViewById(R.id.item_button_no);
            Button button_yes = (Button) itemView.findViewById(R.id.item_button_yes);
            button_no.setOnClickListener(this);
            button_yes.setOnClickListener(this);
        }

        public void setData(String name) {
            item.setText(name);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.item_button_no) {
                listener.showMessage("Юзер " + list.get(getAdapterPosition()).getEmail() + " удалён");
                listener.changeStatus("no", list.get(getAdapterPosition()));
                remove(getAdapterPosition());
            }

            if (v.getId() == R.id.item_button_yes) {
                Log.d(TAG, "onClick:yes " + list.get(getAdapterPosition()));
                listener.showMessage("Юзер " + list.get(getAdapterPosition()).getEmail() + " активирован");
                listener.changeStatus("yes", list.get(getAdapterPosition()));
                remove(getAdapterPosition());
            }


        }
    }


}
