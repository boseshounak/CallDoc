package shounak.shounakbosejiit.com.calldoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class select_time extends AppCompatActivity implements ListView.OnItemClickListener{
    ListView list_time;
    String time[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);
        list_time=(ListView)findViewById(R.id.list_time);
        time=new String[]{"9:00 - 10:00","10:00 - 11:00","11:00 - 12:00","12:00 - 13:00","13:00 - 14:00","14:00 - 15:00",
        "15:00 - 16:00","16:00 - 17:00"};
        Custom_adp3 adtime=new Custom_adp3(this,R.layout.cutom_layout2,time);

        list_time.setAdapter(adtime);
        list_time.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(db_entry.slot_avail[position].equals("0")){
            //Toast.makeText(this,"Avail",Toast.LENGTH_SHORT).show();
            db_entry.time_slot=db_entry.time_code[position];
            db_entry.time_disp=db_entry.time_code_arr[position];
            startActivity(new Intent(this,BookAsk.class));

        }

        else
            Toast.makeText(this,"Slot filled choose another slot",Toast.LENGTH_SHORT).show();
    }
}
