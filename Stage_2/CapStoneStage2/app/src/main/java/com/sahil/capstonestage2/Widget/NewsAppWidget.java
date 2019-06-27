package com.sahil.capstonestage2.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.sahil.capstonestage2.MainActivity;
import com.sahil.capstonestage2.R;

/**
 * Implementation of App Widget functionality.
 */
public class NewsAppWidget extends AppWidgetProvider {

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("NEWS_APP_SHARED_PREFERNCES",0);
        /*String text = sharedPreferences.getString("SP_TEXT_INGRED",context.getString(R.string.data_not_found_message));*/
        String title = sharedPreferences.getString("NEWS_Title",context.getString(R.string.title_failed_load));
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.news_app_widget);
        views.setTextViewText(R.id.app_widget_title, title);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        views.setOnClickPendingIntent(R.id.widget_host,pendingIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

// --Commented out by Inspection START (27-06-2019 06:39):
//    @Override
//    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
//    {
//        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds)
//        {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
//        }
//    }
// --Commented out by Inspection STOP (27-06-2019 06:39)
}

