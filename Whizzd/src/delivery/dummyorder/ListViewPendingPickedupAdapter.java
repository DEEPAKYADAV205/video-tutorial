package delivery.dummyorder;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewPendingPickedupAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;

	private List<Pickedup> orderlist = null;
	private ArrayList<Pickedup> arraylist;

	public ListViewPendingPickedupAdapter(Context context, List<Pickedup> orderlist) {
		this.context = context;
		this.orderlist = orderlist;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<Pickedup>();
		this.arraylist.addAll(orderlist);

	}

	public class ViewHolder {
		TextView name;
		TextView order;
		TextView amount;
		TextView address;
		TextView status;
		TextView resturantid;
		TextView orderid;
		TextView agentid;
		TextView date;

	}

	@Override
	public int getCount() {
		return orderlist.size();
	}

	@Override
	public Object getItem(int position) {
		return orderlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.pendingpickeduplist, null);
			// Locate the TextViews in listview_item.xml
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.order = (TextView) view.findViewById(R.id.order);
			holder.amount = (TextView) view.findViewById(R.id.amount);
			holder.address = (TextView) view.findViewById(R.id.address);
			holder.status = (TextView) view.findViewById(R.id.status);
			holder.resturantid = (TextView) view.findViewById(R.id.resturantid);
			holder.orderid = (TextView) view.findViewById(R.id.orderid);
			holder.agentid = (TextView) view.findViewById(R.id.agentid);
			holder.date = (TextView) view.findViewById(R.id.time);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.name.setText(orderlist.get(position).getName());
		holder.order.setText(orderlist.get(position).getOrder());
		holder.amount.setText(orderlist.get(position).getAmount());
		holder.address.setText(orderlist.get(position).getAddress());
		holder.status.setText(orderlist.get(position).getStatus());
		holder.date.setText("" + orderlist.get(position).getDate());
		holder.resturantid.setText(orderlist.get(position).getResturantid());
		holder.orderid.setText(orderlist.get(position).getId());
		holder.agentid.setText(orderlist.get(position).getAgenttid());
		
		// Set the results into ImageView

		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
				Intent intent = new Intent(context, PendingPickedupItemView.class);
				// Pass all data rank
				intent.putExtra("name", (orderlist.get(position).getName()));
				// Pass all data country
				intent.putExtra("order", (orderlist.get(position).getOrder()));
				// Pass all data population
				intent.putExtra("amount", (orderlist.get(position).getAmount()));
				intent.putExtra("address",
						(orderlist.get(position).getAddress()));
				intent.putExtra("status", (orderlist.get(position).getStatus()));
				intent.putExtra("date", (orderlist.get(position).getDate().toString()));
			
				intent.putExtra("id", (orderlist.get(position).getId().toString()));
				intent.putExtra("resturantid",
						(orderlist.get(position).getResturantid().toString()));
				intent.putExtra("agent",
						(orderlist.get(position).getAgenttid().toString()));
				
				// Start SingleItemView Class
				context.startActivity(intent);
			}
		});
		return view;
	}

}
