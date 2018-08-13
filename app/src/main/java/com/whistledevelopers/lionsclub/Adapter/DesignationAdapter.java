package com.whistledevelopers.lionsclub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.whistledevelopers.lionsclub.Model.Designation;
import com.whistledevelopers.lionsclub.R;

import java.util.ArrayList;

public class DesignationAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public ArrayList<Designation> designationArrayList;
    public ArrayList<Designation> orig;

    public DesignationAdapter(Context context, ArrayList<Designation> designationArrayList) {
        super();
        this.context = context;
        this.designationArrayList = designationArrayList;
    }


    public class DesignationHolder
    {
        TextView title;

    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Designation> results = new ArrayList<Designation>();
                if (orig == null)
                    orig = designationArrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Designation g : orig) {
                            if (g.getTitle().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                designationArrayList = (ArrayList<Designation>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return designationArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return designationArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DesignationHolder holder;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.designationlist, parent, false);
            holder=new DesignationHolder();
            holder.title=(TextView) convertView.findViewById(R.id.text_title);

            convertView.setTag(holder);
        }
        else
        {
            holder=(DesignationHolder) convertView.getTag();
        }

        holder.title.setText(designationArrayList.get(position).getTitle());

       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=designationArrayList.get(position).getTitle();
                Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,MembersActivity.class);
                context.startActivity(intent);

            }
        });*/

        return convertView;

    }

}