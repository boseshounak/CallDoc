package shounak.shounakbosejiit.com.calldoc;

import android.content.ClipData;
import android.content.Context;
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

public class Custom_adp2 extends ArrayAdapter {


        public Custom_adp2(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }



        public Custom_adp2(@NonNull Context context, @LayoutRes int resource, String[] str) {
            super(context, resource, str);
        }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.custom_layout, null);
            }
                if (getItem(position) != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.t_docname);
                TextView tt3 = (TextView) v.findViewById(R.id.t_doctype);

                if (tt1 != null) {
                    tt1.setText(db_entry.avail_doc_name[position]);
                }
                if (tt3 != null) {
                    tt3.setText(db_entry.doc_type);
                }
            }

            return v;
        }


}
