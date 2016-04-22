package com.app.njl.subject.hotel.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.njl.common.NotObfuscateInterface;

public class Fruit extends BaseEntity implements Parcelable, NotObfuscateInterface {
	private int id;
	private String name;
	private String content;
	private String url;
	private String price;
	private String star;
	private String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.name);
		dest.writeString(this.content);
		dest.writeString(this.url);
		dest.writeString(this.price);
		dest.writeString(this.star);
		dest.writeString(this.type);
	}

	public Fruit() {
	}

	protected Fruit(Parcel in) {
		this.id = in.readInt();
		this.name = in.readString();
		this.content = in.readString();
		this.url = in.readString();
		this.price = in.readString();
		this.star = in.readString();
		this.type = in.readString();
	}

	public static final Parcelable.Creator<Fruit> CREATOR = new Parcelable.Creator<Fruit>() {
		public Fruit createFromParcel(Parcel source) {
			return new Fruit(source);
		}

		public Fruit[] newArray(int size) {
			return new Fruit[size];
		}
	};
}
