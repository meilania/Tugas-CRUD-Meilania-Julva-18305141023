package id.ac.uny.math.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.uny.math.R;
import id.ac.uny.math.data.MhsEntity;

public class MhsViewHolder extends RecyclerView.ViewHolder {
    View view;
    TextView txtHP;
    TextView txtNama;
    TextView txtAlamat;
    Button btnEdit;
    MhsEntity mhsEntity;

    public MhsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        initview(itemView);
    }

    void initview(View view){
        txtNama = view.findViewById(R.id.txtNama);
        txtHP = view.findViewById(R.id.txtHP);
        txtAlamat = view.findViewById(R.id.txtAlamat);
        btnEdit = view.findViewById(R.id.btnEdit);
    }

    void bind(MhsEntity mhsEntity, int index){
        this.mhsEntity = mhsEntity;
        txtNama.setText(mhsEntity.getNama());
        txtAlamat.setText(mhsEntity.getAlamat());
        txtHP.setText(mhsEntity.getHp());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), EditActivity.class);
                intent.putExtra("mhsEntity", mhsEntity.toParcel());
                intent.putExtra("index", index);
                ((Activity) view.getContext()).startActivityForResult(intent, MainActivity.CRUD_REQ);
            }
        });
    }
}
