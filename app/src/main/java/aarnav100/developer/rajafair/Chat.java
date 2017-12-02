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
import android.os.Handler;

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
        chatlist = (ListView) findViewById(R.id.chat_list);
        tbox = (EditText) findViewById(R.id.tbox);
        send = (Button) findViewById(R.id.send);
        adapter = new ChatAdapter();
        chatlist.setAdapter(adapter);
        chats.add(welcome());
        adapter.notifyDataSetChanged();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tbox.getText().toString() != "") {
                    mine = true;
                    chats.add(tbox.getText().toString());
                    adapter.notifyDataSetChanged();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            chats.add(input(tbox.getText().toString()));
                            adapter.notifyDataSetChanged();
                        }
                    },1000);
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
            if (mine)
                v = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.chat_view2, null);
            else
                v = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.chat_view1, null);

            ((TextView) v.findViewById(R.id.chat)).setText(getItem(i));
            mine = false;
            return v;
        }
    }

    private String welcome(){
        double rand = Math.random();
        rand = rand * 3;
        int num = (int) rand;

        if (num == 1)
            return ("Hi there,what can i help you with?");

        if (num == 2)
            return ("Hello customer,what do you want to do?");

        if (num == 0)
            return ("Hello, What can i do for you?");

        return "Hi";
    }

    public String input(String str) {
        double rand = Math.random();
        rand = rand * 3;
        int num = (int) rand;

        int flag=0;
        if (checkend(str)) {
            flag = 1;
            if (num == 1) {
                return ("Your task is completed.");
            }

            if (num == 2) {
                return ("Okay.Remember me when you have trouble");
            }

            if (num == 0) {
                return ("Want to do something else?");
            }
        } else if (checkhealth(str)) {
            flag = 2;
            return "Medical assisstance is on the way";
        } else if (checkexit(str)) {
            flag = 3;
            return "Charting exit route";
        } else if (checksearch(str)) {
            flag = 4;
            return "Searching requested shop";
        } else if (checkgnrl(str)){
            flag = 5;
            return "Sending info about Event";
        }

        return "Please be more specific";
    }

    private static boolean checkexit(String str){
        str = str.toLowerCase();
        return (str.contains("exit"));
    }

    public static boolean checkgnrl(String str)
    {
        str=str.toLowerCase();
        if(str.contains("about"))
            return true;
        if(str.contains("info"))
            return true;
        return false;
    }

    public static boolean checksearch(String str) {
        str = str.toLowerCase();
        if(str.contains("where")||str.contains("search")||str.contains("find"))
            return true;
        else
            return false;
    }

    public static boolean checkhealth(String str) {
        str = str.toLowerCase();
        if(str.contains("fever")||str.contains("injury")||str.contains("emergency")||str.contains("health")||str.contains("medical"))
            return true;
        else
            return false;
    }


    public static boolean checkend(String str)
    {
        str = str.toLowerCase();
        for(int i=0;i<str.length()-1;i++)
        {
            for(int j=i+1;j<=str.length();j++)
            {
                if(str.substring(i,j).equals("bye"))
                    return true;
                if(str.substring(i,j).equals("thank"))
                    return true;
                if(str.substring(i,j).equals("done"))
                    return true;
            }
        }
        return false;
    }
}
