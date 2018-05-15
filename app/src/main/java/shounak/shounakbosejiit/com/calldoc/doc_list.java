package shounak.shounakbosejiit.com.calldoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class doc_list extends AppCompatActivity implements ListView.OnItemClickListener{
    ListView list_doc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_list);
        list_doc=(ListView)findViewById(R.id.list_doc);
        Custom_adp2 list_doc_adp=new Custom_adp2(this,R.layout.custom_layout,db_entry.avail_doc_name);
        list_doc.setAdapter(list_doc_adp);
        list_doc.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this,db_entry.avail_doc_name[position],Toast.LENGTH_SHORT).show();
        db_entry.sel_doc=db_entry.avail_doc_name[position];
        Cloud_Handle handle=new Cloud_Handle(this);
        handle.execute("find_time_slot", db_entry.sel_doc,db_entry.doc_type);
    }
}
