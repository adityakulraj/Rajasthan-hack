package aarnav100.developer.rajafair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {
    private ArrayList<String> chats;
    private ListView chatlist;
    private EditText tbox;
    private Button send;
    private boolean mine = false;
    private ChatAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chats = new ArrayList<>();
        chatlist = (ListView)findViewById(R.id.chat_list);
        tbox = (EditText)findViewById(R.id.tbox);
        send = (Button)findViewById(R.id.send);
        adapter = new ChatAdapter();
        chatlist.setAdapter(adapter);
        chats.add("How can we assisst you ?");
        adapter.notifyDataSetChanged();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tbox.getText().toString()!=""){
                    mine = true;
                    chats.add(tbox.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private class ChatAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return chats.size();
        }

        @Override
        public String getItem(int i) {
            return chats.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v;
            if(mine)
                v=((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.chat_view2,null);
            else
                v=((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.chat_view1,null);

            ((TextView)v.findViewById(R.id.chat)).setText(getItem(i));
            mine = false;
            return v;
        }
    }
}
