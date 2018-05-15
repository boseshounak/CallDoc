package shounak.shounakbosejiit.com.calldoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_pat extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener{
    String email,user,doc_type;
    EditText eemail,euser;
    Button bsub2;
    Boolean cemail,cname;
    Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pat);
        eemail=(EditText)findViewById(R.id.eemail);
        euser=(EditText)findViewById(R.id.euser);
        bsub2=(Button)findViewById(R.id.bsub2);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        ArrayAdapter adp3=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,db_entry.spin_str);
        spinner2.setAdapter(adp3);
        spinner2.setOnItemSelectedListener(this);
        bsub2.setOnClickListener(this);
        cemail=true;
        cname=true;

    }

    @Override
    public void onClick(View v) {
        db_entry.name=euser.getText().toString().trim();
        db_entry.email=eemail.getText().toString().trim();
        if(db_entry.name.equals("")||db_entry.email.equals("") ){
            Toast.makeText(this,"Name/Email Cannot Be Left Blank",Toast.LENGTH_SHORT).show();
            cname=false;
        }
        else {
            Cloud_Handle handle=new Cloud_Handle(this);
            handle.execute("find_doc",db_entry.doc_type);

        }



    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        db_entry.doc_type= db_entry.spin_str[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        db_entry.doc_type= db_entry.spin_str[0];

    }
    private void callnet(Boolean cname, Boolean cemail) {

    }
}
