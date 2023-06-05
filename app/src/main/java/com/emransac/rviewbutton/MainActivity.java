package com.emransac.rviewbutton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements mAdapter.itemClickListener, mAdapter.TextInputListener{

    private RecyclerView recyclerView;
    private mAdapter madapter;
    private Producto producto;
    public static ArrayList<Producto> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for(int i=0;i<5;i++){
            producto= new Producto(Integer.toString(i),"Canela "+i,"0","0","0","https://sibarita.pe/wp-content/uploads/2021/07/SIB008.jpg");
            productArrayList.add(producto);
        }





        recyclerView = findViewById(R.id.recycler_view);
        madapter = new mAdapter(this,productArrayList,this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(madapter);
    }

    @Override
    public void onItemClick(int position) {
        //Toast.makeText(MainActivity.this,"Item click"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTextInputClicked(String nombre, String inventario, String pedido) {
        Toast.makeText(MainActivity.this,"N: "+nombre+" I: "+inventario+" P: "+pedido,Toast.LENGTH_SHORT).show();
    }
}