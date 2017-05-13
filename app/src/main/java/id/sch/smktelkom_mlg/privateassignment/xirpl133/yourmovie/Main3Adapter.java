package id.sch.smktelkom_mlg.privateassignment.xirpl133.yourmovie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Vira Meliana on 5/13/2017.
 */

public class Main3Adapter extends RecyclerView.Adapter<Main3Adapter.ViewHolder> {

    private List<Main3ListItem> main3ListItems;
    private Context context;

    public Main3Adapter(List<Main3ListItem> main3ListItems, Context context) {
        this.main3ListItems = main3ListItems;
        this.context = context;
    }

    @Override
    public Main3Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main3_list_item, parent, false);
        return new Main3Adapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(Main3Adapter.ViewHolder holder, final int position) {
        final Main3ListItem main3ListItem = main3ListItems.get(position);

        holder.textViewHead.setText(main3ListItem.getHead());
        holder.textViewDesc.setText(main3ListItem.getDesc());

        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w500" + main3ListItem.getImageUrl())
                .into(holder.imageViewOtof);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, main3ListItem.getHead() + " is opened", Toast.LENGTH_LONG).show();
                Intent singleBlogIntent = new Intent(context, DetailActivity3.class);
                singleBlogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                singleBlogIntent.putExtra("blog_id", position);
                context.startActivity(singleBlogIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return main3ListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageViewOtof;
        public LinearLayout linearLayout;
        public TextView textViewReview;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            imageViewOtof = (ImageView) itemView.findViewById(R.id.imageViewOtof);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            textViewReview = (TextView) itemView.findViewById(R.id.textViewReview);

            //textViewHead.setText();
        }
    }
}
