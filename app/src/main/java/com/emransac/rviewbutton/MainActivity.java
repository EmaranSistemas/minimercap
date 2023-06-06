package com.emransac.rviewbutton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Adapter.itemClickListener, Adapter.TextInputListener{

    private RecyclerView recyclerView;
    private Adapter adapter;
    private Producto producto;
    public static ArrayList<Producto> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for(int i=0;i<5;i++){
            producto= new Producto(Integer.toString(i),"Canela molida para sobres"+i,"","","","https://sibarita.pe/wp-content/uploads/2021/07/SIB008.jpg");
            productArrayList.add(producto);
        }

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new Adapter(this,productArrayList,this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
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