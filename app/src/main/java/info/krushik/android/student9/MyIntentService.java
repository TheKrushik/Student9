package info.krushik.android.student9;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class MyIntentService extends IntentService {//IntentService работает сразу во втором потоке
    private static final String ACTION_SAVE_STUDENT = "info.krushik.android.student9.action.SAVE_STUDENT";
    private static final String ACTION_GET_STUDENT = "info.krushik.android.student9.action.GET_STUDENT";
    private static final String ACTION_SAVE_GROUP = "info.krushik.android.student9.action.SAVE_GROUP";
    private static final String ACTION_GET_GROUP = "info.krushik.android.student9.action.GET_GROUP";

    public static final String EXTRA_PENDING_INTENT = "info.krushik.android.student9.extra.PENDING_INTENT";
    public static final String EXTRA_PENDING_INTENT_GROUP = "info.krushik.android.student9.extra.PENDING_INTENT_GROUP";
    public static final String EXTRA_STUDENT = "info.krushik.android.student9.extra.STUDENT";
    public static final String EXTRA_ID = "info.krushik.android.student9.extra.ID";
    public static final String EXTRA_GROUP = "info.krushik.android.student9.extra.GROUP";
    public static final String EXTRA_ID_GROUP = "info.krushik.android.student9.extra.ID_GROUP";

    public static final int REQUEST_CODE_SAVE_STUDENT = 1;
    public static final int REQUEST_CODE_GET_STUDENT = 2;
    public static final int REQUEST_CODE_SAVE_GROUP = 3;
    public static final int REQUEST_CODE_GET_GROUP = 4;

    public MyIntentService() {//конструктор обезателен
        super("MyIntentService");
    }


    public static void saveStudent(Context context, Student student) {// сохранить студента
        Intent intent = new Intent(context, MyIntentService.class);

        PendingIntent pendingIntent = ((AppCompatActivity) context).createPendingResult(REQUEST_CODE_SAVE_STUDENT, intent, 0);
        intent.putExtra(EXTRA_PENDING_INTENT, pendingIntent);//пендинг первым ложить

        intent.setAction(ACTION_SAVE_STUDENT);
        intent.putExtra(EXTRA_STUDENT, student);

        context.startService(intent);
    }

    public static void getStudent(Context context, long id) {//прочитать студента
        Intent intent = new Intent(context, MyIntentService.class);

        PendingIntent pendingIntent = ((AppCompatActivity) context).createPendingResult(REQUEST_CODE_GET_STUDENT, intent, 0);
        intent.putExtra(EXTRA_PENDING_INTENT, pendingIntent);//пендинг первым ложить

        intent.setAction(ACTION_GET_STUDENT);
        intent.putExtra(EXTRA_ID, id);

        context.startService(intent);
    }

    public static void saveGroup(Context context, Group group) {//сохранить группу
        Intent intent = new Intent(context, MyIntentService.class);

        PendingIntent pendingIntent = ((AppCompatActivity) context).createPendingResult(REQUEST_CODE_SAVE_GROUP, intent, 0);
        intent.putExtra(EXTRA_PENDING_INTENT_GROUP, pendingIntent);//пендинг первым ложить

        intent.setAction(ACTION_SAVE_GROUP);
        intent.putExtra(EXTRA_GROUP, group);

        context.startService(intent);
    }
    public static void getGroup(Context context, long id) {//прочитать группу
        Intent intent = new Intent(context, MyIntentService.class);

        PendingIntent pendingIntent = ((AppCompatActivity) context).createPendingResult(REQUEST_CODE_GET_GROUP, intent, 0);
        intent.putExtra(EXTRA_PENDING_INTENT_GROUP, pendingIntent);//пендинг первым ложить

        intent.setAction(ACTION_GET_GROUP);
        intent.putExtra(EXTRA_ID_GROUP, id);

        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {//сюда приходит Intent, и метод должен сделать какую-то работу

//        try {
//            TimeUnit.SECONDS.sleep(5);//засыпает текущий поток на 5сек.
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if (intent != null) {
            DataBaseHelper helper = new DataBaseHelper(this);

            String action = intent.getAction();//получаем action
            PendingIntent pendingIntent = intent.getParcelableExtra(EXTRA_PENDING_INTENT);//вынимаем pendingIntent
            Intent resultIntent = new Intent();//делаем resultIntent в который будем ложить ответ

            if (ACTION_SAVE_STUDENT.equals(action)) {//смотрим что это за action
                Student student = intent.getParcelableExtra(EXTRA_STUDENT);//вынимаем studentа
                long id = helper.saveStudent(student);

                resultIntent.putExtra(EXTRA_ID, id);
            } else if (ACTION_GET_STUDENT.equals(action)) {//смотрим что это за action
                long id = intent.getLongExtra(EXTRA_ID, 0);
                Student student = helper.getStudent(id);
                resultIntent.putExtra(EXTRA_STUDENT, student);
            } else if (ACTION_SAVE_GROUP.equals(action)) {//смотрим что это за action
                Group group = intent.getParcelableExtra(EXTRA_GROUP);//вынимаем group
                long id = helper.saveGroup(group);

                resultIntent.putExtra(EXTRA_ID_GROUP, id);
            } else if (ACTION_GET_GROUP.equals(action)) {//смотрим что это за action
                long id = intent.getLongExtra(EXTRA_ID_GROUP, 0);
                Group group = helper.getGroup(id);
                resultIntent.putExtra(EXTRA_GROUP, group);
            }

            try {
                pendingIntent.send(this, Activity.RESULT_OK, resultIntent);//отправляем ответ
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }
}