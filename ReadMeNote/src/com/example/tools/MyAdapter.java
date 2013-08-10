package com.example.tools;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.model.Note;
import com.example.readmenote.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter
{

	
		private Activity context;
		private List<Note> list = new ArrayList<Note>();

		public MyAdapter(Activity context, List<Note> list) {
			this.context = context;
			this.list = list;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View itemView = inflater.inflate(R.layout.note_show, null);
			Note note = list.get(position);
			TextView textView = (TextView) itemView.findViewById(R.id.text);
			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.image);
			TextView titleView = (TextView) itemView.findViewById(R.id.title);
			titleView.setText(note.getNote_title());
			if(!note.getPicture_list().isEmpty())
				imageView.setImageBitmap(note.getPicture_list().get(0).getPicture());
			textView.setText(note.getAddnote_details());
			return itemView;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
	

	
}
