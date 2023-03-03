package dk.tec.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private Context context;
    private List<Person> data;

    public MyListAdapter(Context context, List<Person> data) {
        this.context = context;
        this.data = data;
    }

    public void updateDataGetAll(List<Person> newData) {
        if (newData == null) {
            return;
        }
        // Update the data and notify the adapter that the data has changed
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public void updateDataGetID(Person newData) {
        if (newData == null) {
            return;
        }
        // Update the data and notify the adapter that the data has changed
        data.clear();
        data.add(newData);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the list item layout if it hasn't been inflated already
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        }

        // Get references to the TextViews in the list item layout
        TextView nameTextView = convertView.findViewById(R.id.tvName);
        TextView addressTextView = convertView.findViewById(R.id.tvAddress);
        TextView phoneTextView = convertView.findViewById(R.id.tvPhone);
        TextView hairTextView = convertView.findViewById(R.id.tvHair);
        CheckBox favoriteCheckBox = convertView.findViewById(R.id.tvFavorite);
        TextView int1TextView = convertView.findViewById(R.id.tvInt1);
        TextView int2TextView = convertView.findViewById(R.id.tvInt2);
        TextView int3TextView = convertView.findViewById(R.id.tvInt3);
        TextView int4TextView = convertView.findViewById(R.id.tvInt4);

        // Get the Person object for this position in the data list
        Person person = data.get(position);

        // Set the text of the TextViews to the values from the Person object
        nameTextView.setText("Name: " + person.getPerName());
        addressTextView.setText("Address: " +person.getPerAddress());
        phoneTextView.setText("Phone: " +person.getPerPhone());
        hairTextView.setText("Hair Color: " +person.getPerHair());
        favoriteCheckBox.setChecked(person.favorite);
        int1TextView.setText(person.getInterest1());
        int2TextView.setText(person.getInterest2());
        int3TextView.setText(person.getInterest3());
        int4TextView.setText(person.getInterest4());

        // Return the inflated and updated list item layout
        return convertView;
    }
}