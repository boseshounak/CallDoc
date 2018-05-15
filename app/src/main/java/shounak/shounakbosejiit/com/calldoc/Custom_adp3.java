package shounak.shounakbosejiit.com.calldoc;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shounak on 3/10/2018.
 */

public class Custom_adp3 extends ArrayAdapter {


    public Custom_adp3(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }



    public Custom_adp3(@NonNull Context context, @LayoutRes int resource, String[] str) {
        super(context, resource, str);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cutom_layout2, null);
        }
        if (getItem(position) != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.tt1);
            TextView tt3 = (TextView) v.findViewById(R.id.tt3);

            if (tt3 != null) {
                if(db_entry.slot_avail[position].equals("1")){
                    tt3.setBackgroundColor(Color.parseColor("#e26858"));
                    tt1.setBackgroundColor(Color.parseColor("#e26858"));
                }

                else if(db_entry.slot_avail[position].equals("0")){
                    tt3.setBackgroundColor(Color.parseColor("#89e557"));
                    tt1.setBackgroundColor(Color.parseColor("#89e557"));
                }

                tt3.setText(db_entry.time_code_arr[position]);
            }
            if (tt1 != null) {
                tt1.setText("Time Slot- ");
            }
        }

        return v;
    }


}
