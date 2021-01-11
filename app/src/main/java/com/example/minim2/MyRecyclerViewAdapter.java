package com.example.minim2;
import android.content.Context;
import android.renderscript.Element;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.minim2.GithubRepoClass;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    List<GithubRepoClass> repositorios = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView repoNombre;
        public TextView lenguaje;
        public View layout;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            layout = itemLayoutView;
            repoNombre = (TextView) itemLayoutView.findViewById(R.id.nombre);
            lenguaje = (TextView) itemLayoutView.findViewById(R.id.lenguaje);
        }
    }

    public MyRecyclerViewAdapter(List<GithubRepoClass> followers) {
        this.repositorios = followers;
    }

    public void add(int position, GithubRepoClass item){
        repositorios.add(position,item);
        notifyItemInserted(position);
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.repoNombre.setText(repositorios.get(position).getName());
        viewHolder.lenguaje.setText(repositorios.get(position).getLanguage());
    }

    @Override
    public int getItemCount() {
        return repositorios.size();
    }


}