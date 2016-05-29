package info.krushik.android.student9;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View v) {
        mDialog = new ProgressDialog(this);//подождите
        mDialog.setMessage("Wait...");//сообщение
        mDialog.setCancelable(false); //чтоб пользователь не мог проигнорировать и закрыть
        mDialog.show(); //показать



        switch (v.getId()) {
            case R.id.button:

                break;
            case R.id.button2:

                break;
            case R.id.button3:

                break;
            case R.id.button4:

                break;
            case R.id.button5:

                break;
            case R.id.button6:

                break;
            case R.id.button7:
                MyIntentService.saveGroup(this, new Group("Group1"));
                break;
            case R.id.button8:
                MyIntentService.getGroup(this, 1);
                break;
            case R.id.button9:

                break;
            case R.id.button10:

                break;
            case R.id.button11:

                break;
            case R.id.button12:

                break;
            case R.id.button13:

                break;
            case R.id.button14:

                break;
            case R.id.button15:
                MyIntentService.saveStudent(this, new Student("Ivan", "Ivanov", 22));
                break;
            case R.id.button16:
                MyIntentService.getStudent(this, 1);
                break;
            case R.id.button17:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//обработка результата
        if (resultCode == RESULT_OK){

            switch (requestCode){
                case MyIntentService.REQUEST_CODE_SAVE_STUDENT:
                    long id = data.getLongExtra(MyIntentService.EXTRA_ID, 0);// возвращается id
                    Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                    break;
                case MyIntentService.REQUEST_CODE_GET_STUDENT:
                    Student student = data.getParcelableExtra(MyIntentService.EXTRA_STUDENT); //возвращается студент
                    Toast.makeText(this, student.FirstName, Toast.LENGTH_SHORT).show();
                    break;
                case MyIntentService.REQUEST_CODE_SAVE_GROUP:
                    long idGroup = data.getLongExtra(MyIntentService.EXTRA_ID_GROUP, 0);// возвращается id
                    Toast.makeText(this, String.valueOf(idGroup), Toast.LENGTH_SHORT).show();
                    break;
                case MyIntentService.REQUEST_CODE_GET_GROUP:
                    Group group = data.getParcelableExtra(MyIntentService.EXTRA_GROUP); //возвращается группу
                    Toast.makeText(this, group.Number, Toast.LENGTH_SHORT).show();
                    break;
            }

        }
        if (mDialog != null){
            mDialog.dismiss();//закрывает mDialog
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu1:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
