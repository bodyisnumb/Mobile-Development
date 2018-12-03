package example.com.lab_3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ListActivity extends MainActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        list = findViewById(R.id.usersList);
        displayData();
    }

        private Map<String, String> getUsers() {
        SharedPreferences sharedPref = getSharedPreferences(USERS_INFO_VALUE, Context.MODE_PRIVATE);
        Map<String, String> usersMap = new HashMap<>();

        Set<String> user;
        int count = sharedPref.getAll().size() + 1;
        for (int i = 1; i < count; i++) {
            user = sharedPref.getStringSet(USER_KEY_VALUE + i, null);
            assert user != null; //protect constructor
            ArrayList<String> userInfo = new ArrayList<>(user); //create object list
            String fullName, surname = "", name = "", phone = "";
            int userInfoCount = userInfo.size();
            for (int j = 0; j < userInfoCount; j++) {
                String information = userInfo.get(j);
                if (information.contains(USER_SURNAME)) {
                    surname = information.split(USER_SURNAME)[1];
                } else if (information.contains(USER_NAME)) {
                    name = information.split(USER_NAME)[1];
                } else if (information.contains(USER_PHONE)){
                    phone = information.split(USER_PHONE)[1];
                }

            }
            fullName = name + " " + surname;
            usersMap.put(fullName, phone);
        }
        return usersMap;
    }

    private void displayData() {
        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item,
                new String[]{"First Line", "Second Line"}, new int[]{R.id.full_name, R.id.phone});//create adapter

        Iterator it = getUsers().entrySet().iterator(); //Iterator interface enables you to cycle through a collection
        while (it.hasNext()) { //returns true if iteration has more elements
            HashMap<String, String> resultsMap = new HashMap<>();
            HashMap.Entry pair = (Map.Entry) it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }
        list.setAdapter(adapter);//assign adapter to the list
    }
}
