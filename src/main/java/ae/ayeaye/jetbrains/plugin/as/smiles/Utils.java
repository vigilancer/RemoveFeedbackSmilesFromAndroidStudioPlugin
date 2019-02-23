package ae.ayeaye.jetbrains.plugin.as.smiles;

import com.intellij.ide.DataManager;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("WeakerAccess")
public class Utils {
    private Utils() {}

    private static final String LOG_TAG = "AS_SMILES";

    private static final String mrPositiveID = "UserSentimentPanelPositive";
    private static final String mrNegativeID = "UserSentimentPanelNegative";

    public static void takeCareOfThoseGuys(@Nonnull StatusBar statusBar) {
        statusBar.removeWidget(mrPositiveID);
        statusBar.removeWidget(mrNegativeID);
    }

    /** that's how I found IDs of widgets to delete
     */
    public static void printAllWidgetIDs(StatusBar statusBar) {
        final String widgetIdsFieldName = "myWidgetMap";
        try {
            Field f = statusBar.getClass().getDeclaredField(widgetIdsFieldName);
            f.setAccessible(true);

            Object widgetsIdsField = f.get(statusBar);
            //noinspection unchecked
            Map<String, Object> widgetIdsMap = (Map<String, Object>) widgetsIdsField;

            for (String key: widgetIdsMap.keySet()) log(key);

        } catch (NoSuchFieldException e) {
            log("field " + widgetIdsFieldName + " not found;");
        } catch (IllegalAccessException e) {
            log("cannot cast " + widgetIdsFieldName + " into Map");
        }
    }

    /** write to Event Log
     */
    public static void log(String message) {
        Notifications.Bus.notify(new Notification(
                /* groupDisplayId: */ LOG_TAG, /* title: */ LOG_TAG,
                /* content: */ message,
                NotificationType.INFORMATION
        ));
    }

    /** in case there are no another way.
     * there are bunch of deprecated methods to achieve this,
     * this one seems to be working.
     */
    public static Project anotherWayToGetProject() {
        Project project = null;
        try {
            DataContext dataContext = DataManager.getInstance().getDataContextFromFocusAsync().blockingGet(200);
            if (dataContext != null) {
                project = dataContext.getData(DataKeys.PROJECT);
            } else {
                Utils.log("cannot get project from DataContext");
            }
        } catch (TimeoutException e) {
            Utils.log("TimeoutException: cannot get project from DataManager");
        } catch (ExecutionException e) {
            Utils.log("ExecutionException: cannot get project from DataManager");
        }
        return project;
    }
}
