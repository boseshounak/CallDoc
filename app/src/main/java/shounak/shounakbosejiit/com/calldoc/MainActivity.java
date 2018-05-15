package shounak.shounakbosejiit.com.calldoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bdoc,bpat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bdoc=(Button)findViewById(R.id.bdoc);
        bpat=(Button)findViewById(R.id.bpat);
        bdoc.setOnClickListener(this);
        bpat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.bdoc){
            startActivity(new Intent(this,Add_doc.class));
        }
        else if(id==R.id.bpat){
            startActivity(new Intent(this,Add_pat.class));
        }
    }
}
