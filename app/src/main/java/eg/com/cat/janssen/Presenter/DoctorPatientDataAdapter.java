package eg.com.cat.janssen.Presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eg.com.cat.janssen.R;
import eg.com.cat.janssen.model.AllDoctorsResponseModel;

public class DoctorPatientDataAdapter extends RecyclerView.Adapter<DoctorPatientDataAdapter.PlayerViewHolder> implements Filterable {

    public AllDoctorsResponseModel allDoctorsResponseModel;
    private List<AllDoctorsResponseModel.DataBean> allDoctorsResponseModelFiltered;


    public DoctorPatientDataAdapter(AllDoctorsResponseModel allDoctorsResponseModel) {
        this.allDoctorsResponseModel = allDoctorsResponseModel;
        this.allDoctorsResponseModelFiltered = allDoctorsResponseModel.getData();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    allDoctorsResponseModelFiltered = allDoctorsResponseModel.getData();
                } else {
                    List<AllDoctorsResponseModel.DataBean> filteredList = new ArrayList<>();
                    for (AllDoctorsResponseModel.DataBean row : allDoctorsResponseModel.getData()) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getDoctor().getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    allDoctorsResponseModelFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = allDoctorsResponseModelFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                allDoctorsResponseModelFiltered = (ArrayList<AllDoctorsResponseModel.DataBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };


    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_row, parent, false);

        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.name.setText(allDoctorsResponseModelFiltered.get(position).getDoctor().getName());
        holder.nationality.setText(allDoctorsResponseModelFiltered.get(position).getHospital_name());
        holder.age.setText(allDoctorsResponseModelFiltered.get(position).getPatient_profile());
    }

    @Override
    public int getItemCount() {
        return allDoctorsResponseModelFiltered.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        private TextView name, nationality, age;

        public PlayerViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            nationality = (TextView) view.findViewById(R.id.nationality);
            age = (TextView) view.findViewById(R.id.age);
        }
    }
}
