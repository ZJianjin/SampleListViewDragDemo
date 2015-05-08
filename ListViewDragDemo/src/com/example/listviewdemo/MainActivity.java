package com.example.listviewdemo;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	private String[] array = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
	private MyListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DragListView lv = (DragListView) getListView();
		adapter = new MyListViewAdapter(this);
		lv.setAdapter(adapter);
		lv.setDragListener(new DragListView.DragListener() {

			//ÍÏ¶¯µ½Ä³¸öitem
			@Override
			public void drag(int from, int to) {
				System.out.println("drag");
				System.out.println("from = " + from + ",to = " + to);
			}
		});
		lv.setDropListener(new DragListView.DropListener() {

			//Í£Ö¹
			@Override
			public void drop(int from, int to) {
				String fromStr = array[from];
				array[from] = array[to];
				array[to] = fromStr;
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class MyListViewAdapter extends BaseAdapter {

		private LayoutInflater li;

		public MyListViewAdapter(Context context) {
			li = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return array.length;
		}

		@Override
		public Object getItem(int position) {
			return array[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = li.inflate(R.layout.listviewitem, null);
			TextView tv = (TextView) view.findViewById(R.id.tv_item);
			tv.setGravity(Gravity.CENTER);
			tv.setText(array[position]);
			tv.setTextSize(30);
			return view;
		}

	}
}
