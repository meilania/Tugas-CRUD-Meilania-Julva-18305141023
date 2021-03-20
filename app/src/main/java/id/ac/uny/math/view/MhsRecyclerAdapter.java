package id.ac.uny.math.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.uny.math.R;
import id.ac.uny.math.data.MhsEntity;

public class MhsRecyclerAdapter extends RecyclerView.Adapter<MhsViewHolder> {

    List<MhsEntity> mhsEntityList = new ArrayList<>();

    void addMhs(MhsEntity mhsEntity){
        this.mhsEntityList.add(mhsEntity);
        notifyDataSetChanged();
    }

    void updateItem(MhsEntity mhsEntity, int position){
        this.mhsEntityList.get(position).setNama(mhsEntity.getNama());
        this.mhsEntityList.get(position).setAlamat(mhsEntity.getAlamat());
        this.mhsEntityList.get(position).setHp(mhsEntity.getHp());
        notifyDataSetChanged();
    }

    void setMhsEntityList(List<MhsEntity> mhsEntityList){
        this.mhsEntityList = mhsEntityList;
        notifyDataSetChanged();
    }

    void removeItem(MhsEntity mhsEntity){
        this.mhsEntityList.remove(mhsEntity);
        notifyDataSetChanged();
    }

    void removeItem(int i){
        this.mhsEntityList.remove(i);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MhsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_mhs, parent, false);
        return new MhsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MhsViewHolder holder, int position) {
        holder.bind(mhsEntityList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mhsEntityList.size();
    }
}
