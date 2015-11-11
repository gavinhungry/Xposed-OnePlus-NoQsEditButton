package io.gavinhungry.xposed.oneplus.noqseditbutton;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

import android.view.View;
import android.widget.ImageView;

public class NoQsEditButton implements IXposedHookLoadPackage {
  public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
    if (lpparam.packageName.equals("com.android.systemui")) {
      try {

        XposedHelpers.findAndHookMethod("com.android.systemui.statusbar.phone.StatusBarHeaderView", lpparam.classLoader,
          "updateVisibilities", new XC_MethodHook() {

          @Override
          protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            ImageView mEditModeButton = (ImageView) XposedHelpers.getObjectField(param.thisObject, "mEditModeButton");
            mEditModeButton.setVisibility(View.INVISIBLE);
          }
        });

      } catch(Throwable t) {
        XposedBridge.log(t);
      }
    }
  }
}
