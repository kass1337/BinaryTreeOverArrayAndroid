package ru.avtf.rgr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import ru.avtf.rgr.structure.BinaryTreeArray;
import  ru.avtf.rgr.comparator.Comparator;
import  ru.avtf.rgr.factory.FactoryType;
import  ru.avtf.rgr.usertype.prototype.ProtoType;

public class MainActivity extends AppCompatActivity {
    FactoryType factoryType;
    ProtoType protoType;
    BinaryTreeArray bts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        factoryType = new FactoryType();
        ArrayList<String> typesArrayList = FactoryType.getTypeNameList();
        String[] types = new String[typesArrayList.size()];
        for(int i = 0; i < typesArrayList.size(); i++) {
            types[i] = typesArrayList.get(i);
        }
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        Spinner spTypes = (Spinner) findViewById(R.id.comboBox);
        spTypes.setAdapter(typesAdapter);
        spTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
               protoType = FactoryType.getBuilderByName(parent.getSelectedItem().toString());
               assert protoType != null;
               bts = new BinaryTreeArray(protoType.getTypeComparator());
               TextView treeString = (TextView) findViewById(R.id.textArea);
               treeString.setText("");
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
    }

    public void clickAdd(View view) {
        bts.addValue(protoType.create());
        TextView treeString = (TextView) findViewById(R.id.textArea);
        treeString.setText(bts.printTreeString());
    }

    public void balanceClick(View view) {
        bts = bts.balance();
        TextView treeString = (TextView) findViewById(R.id.textArea);
        treeString.setText(bts.printTreeString());
    }

    public void removeByIdClick(View view) {
        EditText idToRemove = (EditText) findViewById(R.id.idToRemove);
        TextView treeString = (TextView) findViewById(R.id.textArea);
        if(!idToRemove.getText().toString().equals("")) {
            if(bts.getDataAtIndexBool(Integer.parseInt(idToRemove.getText().toString()))) {
                bts.removeNodeByIndex(Integer.parseInt(idToRemove.getText().toString()));
                treeString.setText(bts.printTreeString());
            }
            else {
                Toast.makeText(getBaseContext(), "Нужно ввести правильный индекс", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getBaseContext(), "Введите индекс для удаление в поле", Toast.LENGTH_LONG).show();
        }
    }

    public void findByIdClicked(View view) {
        EditText idToFind = (EditText) findViewById(R.id.indexToFind);
        if(!idToFind.getText().toString().equals("")) {
            if(bts.getDataAtIndexBool(Integer.parseInt(idToFind.getText().toString()))) {
                TextView foundField = (TextView) findViewById(R.id.foundValue);
                foundField.setText(bts.getDataAtIndex(Integer.parseInt(idToFind.getText().toString())).toString());
            }
            else {
                Toast.makeText(getBaseContext(), "Нужно ввести правильный индекс", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getBaseContext(), "Введите индекс для поиска в поле", Toast.LENGTH_LONG).show();
        }
    }

    public void saveClicked(View view) throws IOException {
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter((openFileOutput("saved.txt", MODE_PRIVATE))));
        br.write(protoType.typeName() + "\n");
        bts.forEach(el -> {
            try {
                br.write(el.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        br.close();
    }

    public void loadClicked(View view) throws Exception {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader((openFileInput("saved.txt"))));
        }
        catch (Exception ex) {
            Toast.makeText(getBaseContext(), "Ошибка при чтении файла", Toast.LENGTH_LONG).show();
            return;
        }
        String line;
        line = br.readLine();
        if (line == null) {
            Toast.makeText(getBaseContext(), "Ошибка при чтении файла", Toast.LENGTH_LONG).show();
            return;
        }
        if (!protoType.typeName().equals(line)) {
            Toast.makeText(getBaseContext(), "Неправильный формат файла", Toast.LENGTH_LONG).show();
            return;
        }
        bts = new BinaryTreeArray(protoType.getTypeComparator());

        while ((line = br.readLine()) != null) {
            try {
                bts.addValue(protoType.parseValue(line));
            }
            catch (Exception ex) {
                Toast.makeText(getBaseContext(), "Ошибка при чтении файла, возможно в нем хранится другая структура данных", Toast.LENGTH_LONG).show();
                return;
            }
            }
        br.close();
        TextView treeString = (TextView) findViewById(R.id.textArea);
        treeString.setText(bts.printTreeString());
    }
}