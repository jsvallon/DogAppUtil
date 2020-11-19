package com.example.dogapputil.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dogapputil.R;
import com.example.dogapputil.model.BreedResult;
import com.example.dogapputil.model.DogBreedModel;
import com.example.dogapputil.util.Util;
import java.util.List;



public class DogListAdapter extends RecyclerView.Adapter<DogListAdapter.DogViewHolder>{

   private List<DogBreedModel> dogBreedList;
   private List<BreedResult> dogWithImageList;

    private boolean isBreedWithImage;

   public DogListAdapter(List<DogBreedModel> dogBreedList, boolean isBreedWithImage) {
       this.dogBreedList = dogBreedList;
       this.isBreedWithImage = isBreedWithImage;
   }

    public DogListAdapter(List<BreedResult> dogWithImageList) {
        this.dogWithImageList = dogWithImageList;
        this.isBreedWithImage = true;
    }

   public void updateDogList(List<DogBreedModel> newDogsBreedList) {
       dogBreedList.clear();
       dogBreedList.addAll(newDogsBreedList);
       notifyDataSetChanged();
   }

    public void updateDogListImage(List<BreedResult> newDogsBreedList) {
        dogWithImageList.clear();
        dogWithImageList.addAll(newDogsBreedList);
        notifyDataSetChanged();
    }

   class DogViewHolder extends RecyclerView.ViewHolder {
       private View listDogsItemView;
       public DogViewHolder(@NonNull View itemView) {
           super(itemView);
           this.listDogsItemView = itemView;
       }
   }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_dog_item, parent,false);
       return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        TextView dog_name = holder.listDogsItemView.findViewById(R.id.dog_name);
        TextView dog_breed = holder.listDogsItemView.findViewById(R.id.dog_breed);
        ImageView dogImage = holder.listDogsItemView.findViewById(R.id.dogImage);
        if(isBreedWithImage) {
            dogImage.setVisibility(View.VISIBLE);
            Util.showImage(dogImage, dogWithImageList.get(position).getUrl(),Util.getCircularProgressDrawable(dogImage.getContext()));
        }
        dog_breed.setText((dogBreedList !=null) ? dogBreedList.get(position).getBred_for(): dogWithImageList.get(position).getBreeds().get(position).getBred_for());
        dog_name.setText((dogBreedList !=null) ? dogBreedList.get(position).getName(): dogWithImageList.get(position).getBreeds().get(position).getName());
    }


    @Override
    public int getItemCount() {
      return (dogBreedList !=null) ? dogBreedList.size() : dogWithImageList.size();
    }
}
