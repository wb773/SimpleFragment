package com.example.wb773.simplefragment;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private RecyclerView mRecyclerView;
        private MyAdapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;

        private ArrayList<MyAdapter.ViewItem> myDataset;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);

            //テストデータ
            myDataset = new ArrayList<>();
            myDataset.add(new MyAdapter.ViewItem("タイトル１", "内容１"));
            myDataset.add(new MyAdapter.ViewItem("タイトル２", "内容２"));


            // specify an adapter (see also next example)
            mAdapter = new MyAdapter(myDataset);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
            //mRecyclerView.setClickable(true);

            Button addButton = (Button)rootView.findViewById(R.id.add_button);
            addButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // カスタムビューを設定
                    LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(
                            LAYOUT_INFLATER_SERVICE);
                    final View layout = inflater.inflate(R.layout.input_dialog,
                            (ViewGroup)rootView.findViewById(R.id.input_dialog_root));

                    // アラーとダイアログ を生成
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("ダイアログタイトル");
                    builder.setView(layout);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // OK ボタンクリック処理
                            // ID と PASSWORD を取得
                            EditText id
                                    = (EditText) layout.findViewById(R.id.customDlg_id);
                            EditText pass
                                    = (EditText) layout.findViewById(R.id.customDlg_pass);
                            mAdapter.addItem(0,id.getText().toString(), pass.getText().toString());

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Cancel ボタンクリック処理
                        }
                    });

                    // 表示
                    builder.create().show();
                }
            });

            return rootView;
        }
    }
}
