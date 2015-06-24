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

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;

	private List<Order> orderlist = null;
	private ArrayList<Order> arraylist;

	public ListViewAdapter(Context context, List<Order> orderlist) {
		this.context = context;
		this.orderlist = orderlist;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<Order>();
		this.arraylist.addAll(orderlist);

	}

	public class ViewHolder {
		TextView name;
		TextView order;
		TextView amount;
		TextView address;
		TextView status;
		TextView orderid;
		TextView resturantid;

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
			view = inflater.inflate(R.layout.listview_item, null);
			// Locate the TextViews in listview_item.xml
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.order = (TextView) view.findViewById(R.id.order);
			holder.amount = (TextView) view.findViewById(R.id.amount);
			holder.address = (TextView) view.findViewById(R.id.address);
			holder.status = (TextView) view.findViewById(R.id.status);
			holder.date = (TextView) view.findViewById(R.id.time);
			holder.resturantid = (TextView) view.findViewById(R.id.resturantid);

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

		// Set the results into ImageView

		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
				Intent intent = new Intent(context, SingleItemView.class);
				// Pass all data rank
				intent.putExtra("name", (orderlist.get(position).getName()));
				// Pass all data country
				intent.putExtra("order", (orderlist.get(position).getOrder()));
				// Pass all data population
				intent.putExtra("amount", (orderlist.get(position).getAmount()));
				intent.putExtra("address",
						(orderlist.get(position).getAddress()));
				intent.putExtra("status", (orderlist.get(position).getStatus()));
				intent.putExtra("date",
						(orderlist.get(position).getDate().toString()));
				intent.putExtra("id",
						(orderlist.get(position).getId().toString()));
				intent.putExtra("resturantid",
						(orderlist.get(position).getResturantid().toString()));
			
				
				// Start SingleItemView Class
				context.startActivity(intent);
			}
		});
		return view;
	}

}
