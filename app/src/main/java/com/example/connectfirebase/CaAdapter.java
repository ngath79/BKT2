package com.example.connectfirebase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class CaAdapter extends FirebaseRecyclerAdapter<Model,CaAdapter.myViewHolder> {

    public CaAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CaAdapter.myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Model model) {
        holder.tenKH.setText(model.getTenKH());
        holder.tenThuong.setText(model.getTenThuong());
        holder.mauSac.setText(model.getMauLa());
        holder.dacTinh.setText(model.getDacTinh());

        holder.img.setImageResource(model.getUrl());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_item))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();
                // hien thi du lieu len edt
                View view = dialogPlus.getHolderView();
                EditText tenKH = view.findViewById(R.id.txtTenKH);
                EditText tenThuong = view.findViewById(R.id.txtTenThuong);
                EditText dacTinh = view.findViewById(R.id.txtDacTinh);
                EditText mauSac = view.findViewById(R.id.txtMauLa);
                EditText url = view.findViewById(R.id.txtImageUrl);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                tenKH.setText(model.getTenKH());
                tenThuong.setText(model.getTenThuong());
                dacTinh.setText(model.getDacTinh());
                mauSac.setText(model.getMauLa());
                url.setText(model.getUrl());

                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("tenKH",tenKH.getText().toString());
                        map.put("tenThuong",tenThuong.getText().toString());
                        map.put("dacTinh",dacTinh.getText().toString());
                        map.put("mauLa",mauSac.getText().toString());
                        map.put("url",url.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("1")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.tenKH.getContext(), "Update dữ liệu thành công", Toast.LENGTH_SHORT).show();
                                        // tat dialog
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.tenKH.getContext(), "Update dữ liệu thất bại!", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.tenKH.getContext());
                builder.setTitle("Bạn có chắc chắn muốn xóa không?");
                builder.setMessage("Dữ liệu xóa không thể hoàn tác");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Ca")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Canel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.tenKH.getContext(), "Canlled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }


    @NonNull
    @Override
    public CaAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent,false);
        return new myViewHolder((view));
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tenKH, tenThuong, dacTinh, mauSac;
        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img1);
            tenKH = (TextView) itemView.findViewById(R.id.tenKH);
            tenThuong = (TextView) itemView.findViewById(R.id.tenThuong);
            dacTinh = (TextView) itemView.findViewById(R.id.dacTinh);
            mauSac = (TextView) itemView.findViewById(R.id.mauLa);

            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
        }

    }
}
