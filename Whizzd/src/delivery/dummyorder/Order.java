package delivery.dummyorder;

import java.util.Date;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class Order {
	private String name;
	private String order;
	private String amount;
	private String address;
	private String status;
	private String id;
	private String resturantid;
	private LatLng location;
	private String agenttid;


	private Date date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LatLng getLocation() {
		return location;
	}

	public void setLocation(LatLng location) {
		this.location = location;
	}

	public String getResturantid() {
		return resturantid;
	}

	public void setResturantid(String resturantid) {
		this.resturantid = resturantid;
	}

	public String getAgenttid() {
		return agenttid;
	}

	public void setAgenttid(String agenttid) {
		this.agenttid = agenttid;
	}


	

	

}