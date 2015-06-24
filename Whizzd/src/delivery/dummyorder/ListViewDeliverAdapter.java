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

public class ListViewDeliverAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;

	private List<Deliver> deliverlist = null;
	private ArrayList<Deliver> arraylist;

	public ListViewDeliverAdapter(Context context, List<Deliver> deliverlist) {
		this.context = context;
		this.deliverlist = deliverlist;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<Deliver>();
		this.arraylist.addAll(deliverlist);

	}

	public class ViewHolder {
		TextView name;
		TextView order;
		TextView amount;
		TextView address;
		TextView status;

		TextView date;

	}

	@Override
	public int getCount() {
		return deliverlist.size();
	}

	@Override
	public Object getItem(int position) {
		return deliverlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_item1, null);
			// Locate the TextViews in listview_item.xml
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.order = (TextView) view.findViewById(R.id.order);
			holder.amount = (TextView) view.findViewById(R.id.amount);
			holder.address = (TextView) view.findViewById(R.id.address);
			holder.status = (TextView) view.findViewById(R.id.status);
			holder.date = (TextView) view.findViewById(R.id.time);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.name.setText(deliverlist.get(position).getName());
		holder.order.setText(deliverlist.get(position).getOrder());
		holder.amount.setText(deliverlist.get(position).getAmount());
		holder.address.setText(deliverlist.get(position).getAddress());
		holder.status.setText(deliverlist.get(position).getStatus());
		holder.date.setText("" + deliverlist.get(position).getDate());
		
		// Set the results into ImageView

		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
				Intent intent = new Intent(context, DeliveredItemView.class);
				// Pass all data rank
				intent.putExtra("name", (deliverlist.get(position).getName()));
				// Pass all data country
				intent.putExtra("order", (deliverlist.get(position).getOrder()));
				// Pass all data population
				intent.putExtra("amount", (deliverlist.get(position).getAmount()));
				intent.putExtra("address",
						(deliverlist.get(position).getAddress()));
				intent.putExtra("status", (deliverlist.get(position).getStatus()));
				intent.putExtra("date", (deliverlist.get(position).getDate().toString()));
				intent.putExtra("id", (deliverlist.get(position).getId().toString()));
				// Start SingleItemView Class
				context.startActivity(intent);
			}
		});
		return view;
	}

}
