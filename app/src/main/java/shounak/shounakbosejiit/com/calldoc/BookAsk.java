package shounak.shounakbosejiit.com.calldoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookAsk extends AppCompatActivity implements View.OnClickListener{
    TextView e_docname,e_doctype,e_timeslot;
    Button bpro,bcan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ask);
        bpro=(Button)findViewById(R.id.bpro);
        bcan=(Button)findViewById(R.id.bcan);
        e_docname=(TextView)findViewById(R.id.e_docname);
        e_doctype=(TextView)findViewById(R.id.e_doctype);
        e_timeslot=(TextView)findViewById(R.id.e_timeslot);
        e_docname.setText(db_entry.sel_doc);
        e_doctype.setText(db_entry.doc_type);
        e_timeslot.setText(db_entry.time_disp);
        bpro.setOnClickListener(this);
        bcan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.bcan){
            startActivity(new Intent(this,Add_pat.class));
        }
        else{
            Cloud_Handle handle=new Cloud_Handle(this);
            handle.execute("book");
        }
    }
}
