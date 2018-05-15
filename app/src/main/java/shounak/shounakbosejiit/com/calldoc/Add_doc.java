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

public class Add_doc extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener {
    EditText doc_name,doc_qual;
    Button submit;
    Spinner spinner;
    ArrayAdapter adp;
    String name,type,qual;
    Boolean cname,cqual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doc);
        doc_name=(EditText)findViewById(R.id.doc_name);
        doc_qual=(EditText)findViewById(R.id.doc_qual);
        spinner=(Spinner)findViewById(R.id.spinner);
        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(this);
        db_entry.spin_str= new String[]{"Cardiologists", "Dermatologists", "Neurologists","ENT","Gynaecologist","Ophthalmologists"};
        adp=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,db_entry.spin_str);
        spinner.setAdapter(adp);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        name=doc_name.getText().toString().trim();
        qual=doc_qual.getText().toString().trim();
        if(name.equals("") || qual.equals("")){
            Toast.makeText(this,"Name and qualification cannot be blank",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this,name+" "+type+" "+qual,Toast.LENGTH_SHORT).show();
        else{
            Cloud_Handle handle=new Cloud_Handle(this);
            handle.execute("add_doc",name,type,qual);
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type=db_entry.spin_str[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        type=db_entry.spin_str[0];
    }
}
