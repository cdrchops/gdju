package com.gerbildrop.gc.gauges.pfd.a340;

import com.gerbildrop.gc.gauges.pfd.BaseGauge;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class StatusArea {

    public static void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        if (!BaseGauge.bInited) {
            double vTimeOfDay_Hour = 1;//diom_loadVariable("timeofday.hour");
            double vTimeOfDay_Minute = 0;//diom_loadVariable("timeofday.minute");
            double vGrossWeight = 0;//diom_loadVariable("grossweight");
            double vCenterOfGravity = 0;//diom_loadVariable("centerofgravity");
            double vTemperature_StaticAir = 80;//diom_loadVariable("temperature.static_air");
            double vTemperature_TotalAir = 80;//diom_loadVariable("temperature.total_air");
            BaseGauge.bInited = true;
        }

        int iHour = 1;//diom_getValueInteger(vTimeOfDay_Hour);
        int iMinute = 0;//diom_getValueInteger(vTimeOfDay_Minute);
        int iGrossWeight = 0;//diom_getValueInteger(vGrossWeight);
        float fCenterOfGravity = 0;//diom_getValueFloat(vCenterOfGravity);
        float fStaticAir = 80;//diom_getValueFloat(vTemperature_StaticAir);
        float fTotalAir = 80;//diom_getValueFloat(vTemperature_TotalAir);

        // Paint status area
        gl.glColor3ubv(BaseGauge.CLGrey);

        gl.glLineWidth(3 * 2);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.023f, 0.117f, 0);
        gl.glVertex3f(0.977f, 0.117f, 0);

        gl.glVertex3f(0.335f, 0.023f, 0);
        gl.glVertex3f(0.335f, 0.117f, 0);

        gl.glVertex3f(0.665f, 0.023f, 0);
        gl.glVertex3f(0.665f, 0.117f, 0);
        gl.glEnd();
        gl.glLineWidth(3);

        // Paint status text
        gl.glColor3b((byte) 255, (byte) 255, (byte) 255);
        PFD.fnt_printf(glAutoDrawable, 0.023, 0.057, "TAT " + fTotalAir + " \u00B0C");
        PFD.fnt_printf(glAutoDrawable, 0.023, 0.017, "SAT " + fStaticAir + " \u00B0C");
        PFD.fnt_printf(glAutoDrawable, 0.415, 0.017, iHour + " H " + iMinute);
        PFD.fnt_printf(glAutoDrawable, 0.675, 0.057, "GW " + iGrossWeight + " KG");
        PFD.fnt_printf(glAutoDrawable, 0.675, 0.017, "GWCC " + fCenterOfGravity + "  %%");
    }
}
