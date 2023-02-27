package clu.tec.webapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GooseAdapter extends BaseAdapter
{

    private final List<Goose> geese;
    private final MainActivity mainActivity;

    public GooseAdapter(List<Goose> geese, MainActivity mainActivity) {
        this.geese = geese;
        this.mainActivity = mainActivity;
    }

    @Override
    public int getCount() {
        return geese.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Goose theGoose = geese.get(position);
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.custom_item, null);

        // Image
        ImageView img = v.findViewById(R.id.imgGoose);
        img.setImageResource(theGoose.getPicture());
        // Name
        TextView name = v.findViewById(R.id.txtName);
        name.setText(theGoose.getName());
        // Description
        TextView desc = v.findViewById(R.id.txtDesc);
        desc.setText(theGoose.getDescription());

        return v;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
