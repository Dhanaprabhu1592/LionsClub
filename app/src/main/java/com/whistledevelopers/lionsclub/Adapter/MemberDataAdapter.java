package com.whistledevelopers.lionsclub.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.whistledevelopers.lionsclub.View.MemberDetailsActivity;
import com.whistledevelopers.lionsclub.Model.MemberData;
import com.whistledevelopers.lionsclub.R;

import java.util.ArrayList;
import java.util.List;

public class MemberDataAdapter extends RecyclerView.Adapter<MemberDataAdapter.MemberViewHolder> implements Filterable {
    private Context context;
    private List<MemberData> memberDataList;
    public ArrayList<MemberData> orig;

    public MemberDataAdapter(Context context, List<MemberData> memberDataList) {
        this.context = context;
        this.memberDataList = memberDataList;
    }






    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.member_list,null);


        return new MemberViewHolder(view);
    }




    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<MemberData> results = new ArrayList<MemberData>();
                if (orig == null)
                    orig = (ArrayList<MemberData>) memberDataList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final MemberData g : orig) {
                            if (g.getName().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                memberDataList = (ArrayList<MemberData>) results.values;
                notifyDataSetChanged();

            }
        };

    }





    @Override
    public void onBindViewHolder(@NonNull final MemberViewHolder holder, final int position) {

        MemberData memberData=memberDataList.get(position);

        holder.mjf.setText(memberData.getMjf());
        holder.clubName.setText(memberData.getClubname());
        holder.clubArea.setText("LC "+memberData.getClubarea());
        holder.name.setText("Lion "+memberData.getLastname()+" "+memberData.getName());
       // holder.lastName.setText(memberData.getLastname());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=memberDataList.get(position).getMobileno();
               // Toast.makeText(context, data, Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context,MemberDetailsActivity.class);
                intent.putExtra("mobileNumber",data);
                context.startActivity(intent);
                ((Activity)context).finish();


            }
        });




    }

    @Override
    public int getItemCount() {
        return memberDataList.size();
    }




    class MemberViewHolder extends RecyclerView.ViewHolder
    {

        TextView mjf,clubName,clubArea,name,lastName;

        public MemberViewHolder(View itemView) {
            super(itemView);

            mjf=itemView.findViewById(R.id.text_mjf);
            clubName=itemView.findViewById(R.id.text_clubname);
            clubArea=itemView.findViewById(R.id.text_clubarea);
            name=itemView.findViewById(R.id.text_name);
          //  lastName=itemView.findViewById(R.id.text_lastname);

        }
    }
}
