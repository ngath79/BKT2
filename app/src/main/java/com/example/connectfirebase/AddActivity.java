package com.example.connectfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    EditText tenKH,tenThuong,dacTinh,mauSac, url;
    Button btnAdd,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tenKH = findViewById(R.id.txtTenKH);
        tenThuong = findViewById(R.id.txtTenThuong);
        dacTinh = findViewById(R.id.txtDacTinh);
        mauSac = findViewById(R.id.txtMauLa);
        url = findViewById(R.id.txtImageUrl);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("tenKH",tenKH.getText().toString());
        map.put("tenThuong",tenThuong.getText().toString());
        map.put("dacTinh",dacTinh.getText().toString());
        map.put("mauSac",mauSac.getText().toString());
        map.put("url",url.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Ca").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Thêm dữ liệu thành công!",Toast.LENGTH_SHORT).show();
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this,"Thêm dữ liệu thất bại!",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void clearAll(){
        tenKH.setText("");
        tenThuong.setText("");
        dacTinh.setText("");
        mauSac.setText("");
        url.setText("");
    }
}