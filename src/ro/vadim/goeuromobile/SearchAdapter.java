package ro.vadim.goeuromobile;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

public class SearchAdapter extends ArrayAdapter<String> implements Filterable{

	private static final int DEFAULT_RESOURCE_ID = R.layout.listview_item_row;	
    
	Context context; 
    int layoutResourceId = DEFAULT_RESOURCE_ID;
    String data[] = null;
    
    public SearchAdapter(Context context, String[] data) {    	
        super(context, DEFAULT_RESOURCE_ID, data);
        this.context = context;
        this.data = data;
    }
    
    public SearchAdapter(Context context, ArrayList<String> data) {    	
        super(context, DEFAULT_RESOURCE_ID, data);
        this.context = context;
        this.data = (String[])data.toArray();
    }
    
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemHolder holder = null;
        
        if(row == null)
        {               
            LayoutInflater inflater = MainActivity.getCurrentFragment().getActivity().getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            
            holder = new ItemHolder();            
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            
            row.setTag(holder);
        }
        else
        {
            holder = (ItemHolder)row.getTag();
        }
        
        
        String item = data[position];
        if(item != null){               
        	holder.txtTitle.setText(item);                
        }
        
        return row;
    }
    
    static class ItemHolder
    {        
        TextView txtTitle;
    }
}