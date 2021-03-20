package id.ac.uny.math.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.ac.uny.math.R;
import id.ac.uny.math.data.MhsEntity;
import id.ac.uny.math.data.MhsParcel;

import static id.ac.uny.math.MathApp.mathDatabase;

public class MainActivity extends AppCompatActivity {

    public static int CRUD_REQ = 222;

    RecyclerView rvMain;
    FloatingActionButton btnAdd;
    LinearLayoutManager linearLayoutManager;
    List<MhsEntity> mhsEntityList = new ArrayList<>();
    MhsRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intiviews();
        initViewData();
        initaction();
    }

    void updateView(MhsEntity mhsEntity, int position) {
        adapter.updateItem(mhsEntity, position);
    }

    void addViewData(MhsEntity mhsEntity) {
        adapter.addMhs(mhsEntity);
    }

    void initViewData() {
        if (mathDatabase.getMhsDao().getMhs() == null) return;
        mhsEntityList = mathDatabase.getMhsDao().getMhs();
        adapter.setMhsEntityList(mhsEntityList);
        adapter.notifyDataSetChanged();
    }

    void intiviews() {
        btnAdd = findViewById(R.id.btnAdd);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMain = findViewById(R.id.rvMain);
        rvMain.setLayoutManager(linearLayoutManager);
        adapter = new MhsRecyclerAdapter();
        rvMain.setAdapter(adapter);
    }

    void initaction() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, CRUD_REQ);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CRUD_REQ && resultCode == RESULT_OK) {
            MhsParcel mhsParcel = data.getParcelableExtra("mhsEntity");
            MhsEntity mhsEntity = mhsParcel.toEntity();
            int position = data.getIntExtra("position", 0);
            boolean isNew = data.getBooleanExtra("isNew", false);

            if (isNew) {
                addViewData(mhsEntity);
            } else {
                updateView(mhsEntity, position);
            }
        }
    }
}