package com.rw.boosterapp.database.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.rw.boosterapp.R;
import com.rw.boosterapp.database.daos.Answer;
import com.rw.boosterapp.database.daos.Fund;
import com.rw.boosterapp.database.daos.InvestorType;
import com.rw.boosterapp.database.daos.MenuGroup;
import com.rw.boosterapp.database.daos.MenuItem;
import com.rw.boosterapp.database.daos.Question;
import com.rw.boosterapp.database.daos.SelectedAnswer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Rakhita on 4/19/2018.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASENAME = "booster.db";
    private static final int DATABASEVERSION = 1;
    private Context mContext;

    public DBHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Answer.class);
            TableUtils.createTableIfNotExists(connectionSource, Question.class);
            TableUtils.createTableIfNotExists(connectionSource, MenuGroup.class);
            TableUtils.createTableIfNotExists(connectionSource, MenuItem.class);
            TableUtils.createTableIfNotExists(connectionSource, InvestorType.class);
            TableUtils.createTableIfNotExists(connectionSource, Fund.class);
            TableUtils.createTableIfNotExists(connectionSource, SelectedAnswer.class);

            insertInitialData(db);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
    }

    @Override
    public void close() {
        super.close();
    }

    public String convertStreamToString(InputStream inputStream)
            throws IOException {
        Writer writer = new StringWriter();
        char[] buffer = new char[2048];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream,
                    "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            if(inputStream != null)
                inputStream.close();
        }
        String text = writer.toString();
        return text;
    }

    private void insertInitialData(SQLiteDatabase db) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = mContext.getResources().openRawResource(R.raw.db_init);
            String initSql = convertStreamToString(inputStream);
            String[] queries = initSql.split(";");
            for(String query : queries){
                db.execSQL(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream != null)
                inputStream.close();
        }
    }
}
