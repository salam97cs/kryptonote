package ca.yorku.eecs.kryptonote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class KyryptoNoteActivity extends AppCompatActivity
{

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kyryptonote_layout);
    }
    public void encryptClick(View v)
    {
        EditText cView = findViewById(R.id.cryptkey);
        String c = cView.getText().toString();

        EditText nView = findViewById(R.id.note);
        String n = nView.getText().toString();

        Cipher mymodel = new Cipher(c);
        String s = mymodel.encrypt(n);

        ((EditText) findViewById(R.id.note)).setText(s);
    }

    public void decryptClick(View v)
    {
        EditText cView = findViewById(R.id.cryptkey);
        String c = cView.getText().toString();

        EditText nView = findViewById(R.id.note);
        String n = nView.getText().toString();

        Cipher mymodel = new Cipher(c);
        String s = mymodel.decrypt(n);

        ((EditText) findViewById(R.id.note)).setText(s);
    }

    @Override
    public File getFilesDir()
    {
        return super.getFilesDir();
    }

    public void saveClick(View v)
    {
        try
        {
            String name = ((EditText) findViewById(R.id.filename)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText)findViewById(R.id.note)).getText().toString());
            fw.close();
            Toast.makeText(this, "Note Saved", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void loadClick(View v)
    {
        try
        {
            String name = ((EditText) findViewById(R.id.filename)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileReader fr = new FileReader(file);
            String show = "";
            for (int c = fr.read(); c != -1; c = fr.read())
            {
                show += (char) c;
            }
            fr.close();
            ((EditText) findViewById(R.id.note)).setText(show);
        }
        catch (Exception e)
            {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

            }
    }
}
