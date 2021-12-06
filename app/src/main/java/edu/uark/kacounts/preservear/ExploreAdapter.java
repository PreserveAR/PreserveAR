package edu.uark.kacounts.preservear;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.uark.kacounts.preservear.Data.Photo;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder>{

    public void setLocalDataSet(List<Photo> localDataSet) {
        this.localDataSet = localDataSet;
    }

    private List<Photo> localDataSet;

    // want to pass tag!
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle; // holds experience title
        private final TextView tvDescription; // holds first sentence of experience
        private final ImageView image; // holds first image from experience if multiple exist

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            tvTitle = view.findViewById(R.id.tvTitle);
            tvDescription = view.findViewById(R.id.tvDescription);
            image = view.findViewById(R.id.imagePreview);
        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvDescription(){
            return tvDescription;
        }

        public ImageView getImage(){
            return image;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public ExploreAdapter(List<Photo> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.explore_recycle_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.itemView.setTag(String.valueOf(localDataSet.get(position).getId()));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer experience_id = Integer.valueOf((String) view.getTag());
                Intent exploreIntent = new Intent();
                exploreIntent.setClass(view.getContext(), experienceActivity.class); // take over to Julio's experience activity
                exploreIntent.putExtra("experience_id",experience_id);
                view.getContext().startActivity(exploreIntent);

            }
        });
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String experienceTitle = localDataSet.get(position).getTitle();
        // set the title of the experience
        viewHolder.getTvTitle().setText(experienceTitle);
        // set the description of the experience
        String experienceDescription = localDataSet.get(position).getComment();
        // parse to be just 25 characters
        String parsedDescription = experienceDescription.substring(0,25) + "...";
        // ADD LATER!
        viewHolder.getTvDescription().setText(parsedDescription);
        // add the image
//        ImageView experienceImage = localDataSet.get(position).getExperienceImage();
//        viewHolder.getExperienceImage().setImage;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
