package ae.ayeaye.jetbrains.plugin.as.smiles;


import com.intellij.ide.DataManager;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ExecuteThoseGuysAction extends AnAction implements DumbAware {
    public ExecuteThoseGuysAction() {
        super();
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);

        Utils.takeCareOfThoseGuys(statusBar);
    }
}